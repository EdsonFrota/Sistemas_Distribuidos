package banco;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import banco.Movimentacao.TipoOperacao;

public class CaixaImpl extends UnicastRemoteObject implements Caixa {
	private ConnFactory db = new ConnFactory();
	private Connection conn;
	
	protected CaixaImpl() throws RemoteException {
		super();
		try {
			this.conn = db.getConnection();
			this.conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Conta login(Conta contaLogin) throws RemoteException {
		try {
			if(this.conn.isClosed()) {
				this.conn = this.db.getConnection();
			}
					
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT senha, saldo, nome FROM banco.conta ");
			sql.append(" WHERE numero_conta = ? ");
			
			System.out.println("DANIEL LOGIN");
			
			PreparedStatement ps = this.conn.prepareStatement(sql.toString());
			ps.setInt(1, contaLogin.getNumeroConta());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				if(!contaLogin.getSenha().equals(rs.getString("senha").trim())) {
					return null;
				}
				
				contaLogin.setNomeCliente(rs.getString("nome").trim());
				contaLogin.setSaldo(rs.getDouble("saldo"));
			}
			
			return contaLogin;			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Conta realizarOperacao(Conta contaLogada, Movimentacao movimentacao) throws RemoteException {
		contaLogada = this.login(contaLogada);
		
		if(contaLogada == null) {
			return null;
		}
		
		switch (movimentacao.getTipoOperacao()) {
		case TRANSFERENCIA:
			realizarTransferencia(contaLogada, movimentacao);
			break;
		default:
			this.salvarMovimentacao(contaLogada, movimentacao);
			break;
		}
		
		return null;
	}

	@Override
	public List<Movimentacao> consultarExtrato(Conta contaLogada) throws RemoteException {
		
		try {

			if(this.conn.isClosed()) {
				this.conn = this.db.getConnection();
			}
			
			ArrayList<Movimentacao> extrato = new ArrayList<>();
			
						
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT id_transacao, numero_conta, data_transacao, valor, tipo_transacao, tipo, conta_destino FROM banco.movimentacao ");
			sql.append(" WHERE numero_conta = ? ");
			sql.append(" ORDER BY data_transacao DESC ");
			
			PreparedStatement ps = this.conn.prepareStatement(sql.toString());
			ps.setInt(1, contaLogada.getNumeroConta());
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Movimentacao mov = new Movimentacao(contaLogada.getNumeroConta(), rs.getDouble("valor"), TipoOperacao.getEnum(rs.getString("tipo_transacao").trim()));
				mov.setTipo(rs.getString("tipo"));
				mov.setData(rs.getDate("data_transacao"));
				extrato.add(mov);
			}
			
			return extrato;			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private Double consultarSaldo(Integer conta) {
		Double saldo = null;
		
		try {
			if(this.conn.isClosed()) {
				this.conn = this.db.getConnection();
			}
					
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT saldo FROM banco.conta ");
			sql.append(" WHERE numero_conta = ? ");
			
			PreparedStatement ps = this.conn.prepareStatement(sql.toString());
			ps.setInt(1, conta);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
			
				saldo = rs.getDouble("saldo");
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return saldo;
	}
	
	private Boolean realizarTransferencia(Conta conta, Movimentacao movimentacao) {
		this.salvarMovimentacao(conta, movimentacao);
		
		Movimentacao receberTransferencia = new Movimentacao(movimentacao.getContaDestino(), movimentacao.getValor(), TipoOperacao.TRANSFERENCIA);
		receberTransferencia.setContaDestino(conta.getNumeroConta());
		receberTransferencia.setTipo("c");
		
		this.salvarMovimentacao(conta, receberTransferencia);
		
		return false;
	}
	
	private Boolean salvarMovimentacao(Conta contaLogada, Movimentacao movimentacao) {
		Boolean exec = false;
		try {
						
			Integer conta = movimentacao.getConta();
			
			long time = System.currentTimeMillis();
			Timestamp timestamp = new Timestamp(time);
			
			String tipo = movimentacao.getTipo();
			Double saldo;
			if(movimentacao.getTipoOperacao() == TipoOperacao.TRANSFERENCIA && tipo.equals("c")) {
				saldo = this.consultarSaldo(conta);
			} else {
				saldo = contaLogada.getSaldo();
			}
			
			if(tipo.equals("d")) {
				if((saldo - movimentacao.getValor()) < 0) {
					return false;
				}
				saldo -= movimentacao.getValor();
			} else if (tipo.equals("c")) {
				saldo += movimentacao.getValor();
			}
			

			if(this.conn.isClosed()) {
				this.conn = this.db.getConnection();
			}
						
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO movimentacao (numero_conta, data_transacao, valor, tipo_transacao, tipo ");
			if(movimentacao.getTipoOperacao() == TipoOperacao.TRANSFERENCIA) {
				sql.append(" , conta_destino ");				
			}
			sql.append(") VALUES ( ");
			sql.append(movimentacao.getConta() + ", ?, ?, '" + movimentacao.getTipoOperacao().getValue() + "', '" + tipo + "' ");
			if(movimentacao.getTipoOperacao() == TipoOperacao.TRANSFERENCIA) {
				sql.append(" , " + movimentacao.getContaDestino());
			}
			sql.append(" ) ");
			
			PreparedStatement ps = this.conn.prepareStatement(sql.toString());
			ps.setTimestamp(1, timestamp);
			ps.setDouble(2, movimentacao.getValor());
			ps.execute();
			
			this.conn.close();
			this.conn = this.db.getConnection();
			
			StringBuilder sqlUpdateSaldo = new StringBuilder();
			sqlUpdateSaldo.append(" UPDATE conta SET saldo = ? WHERE numero_conta = ? ");
			ps = this.conn.prepareStatement(sqlUpdateSaldo.toString());
			ps.setDouble(1, saldo);
			ps.setInt(2, conta);
			
			ps.executeUpdate();
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return exec;
	}

}
