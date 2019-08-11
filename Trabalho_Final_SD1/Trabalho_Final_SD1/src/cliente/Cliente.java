package cliente;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import banco.Caixa;
import banco.Conta;
import banco.Movimentacao;
import banco.Movimentacao.TipoOperacao;


public class Cliente {
	private Conta conta;
	private Caixa caixa;
	
	public static void main(String[] args) {
		
		Cliente cliente = new Cliente();
		cliente.setCaixa(null);
		
		try {
			Registry registry = LocateRegistry.getRegistry("172.16.32.105", 6565);
			cliente.setCaixa((Caixa) registry.lookup("caixa"));
			
			if(cliente.login()) {
				System.out.println("Logado com sucesso");
				Cliente.menu(cliente);
			} else {
				System.out.println("Login incorreto");
			}
			
			//System.out.println(cliente.conta.getNomeCliente());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	private static void menu(Cliente cliente) throws RemoteException {
		Scanner input = new Scanner(System.in);
		int i = 0;
		do {
			System.out.println("Conta: " + cliente.conta.getNumeroConta());
			System.out.println("Nome: " + cliente.conta.getNomeCliente());
			System.out.println("Saldo: " + cliente.conta.getSaldo() + "\n");
			
			System.out.println("Escolha a operação: \n");
			System.out.println("\t1) Consultar Extrato");
			System.out.println("\t2) Realizar saque");
			System.out.println("\t3) Realizar depósito");
			System.out.println("\t4) Realizar transferência");
			System.out.println("\t5) Logout");
			
			Integer op = input.nextInt();
			
			switch (op) {
			case 1:
				cliente.consultarExtrato();
				break;
			case 2:
				cliente.realizarSaque();
				break;
			case 3:
				cliente.realizarDeposito();
				break;
			case 4:
				cliente.realizarTransferencia();
				break;
			case 5:
				System.out.println("Foi um prazer atende-lo");
				i=1;
				break;
			default:
				System.out.println("Operacao invalida");
				break;
			}

		}while (i != 1);
			}
	
	
	private Boolean login() {
		System.out.println("Entre com o numero da conta: ");
		Scanner input = new Scanner(System.in);
		Integer numeroConta = input.nextInt();
		
		System.out.println("Entre com a senha: ");
		String senha = input.next();
		
		this.conta = new Conta(numeroConta, null, senha);
		try {
			this.conta = this.caixa.login(this.conta);
			if(this.conta != null) {
				return true;
			}
			
			return false;
		} catch (RemoteException e) {
			return false;
		}
		
	}
	
	private Boolean realizarSaque() {
		Double valor;
		Scanner input = new Scanner(System.in);
		
		System.out.println("Insira o valor do saque a ser realizado: ");
		valor = input.nextDouble();
		
		Movimentacao saque = new Movimentacao(this.conta.getNumeroConta(), valor, TipoOperacao.SAQUE);
		saque.setTipo("d");
		
		if(valor > this.conta.getSaldo()) {
			System.out.println("Saldo indisponivel");
			return false;
		}
		
		try {
			this.caixa.realizarOperacao(conta, saque);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	

	private Boolean realizarDeposito() {
		Double valor;
		Scanner input = new Scanner(System.in);
		
		System.out.println("Insira o valor do depósito a ser realizado: ");
		valor = input.nextDouble();
		
		Movimentacao deposito = new Movimentacao(this.conta.getNumeroConta(), valor, TipoOperacao.DEPOSITO);
		deposito.setTipo("c");
		
		try {
			this.caixa.realizarOperacao(conta, deposito);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	

	private Boolean realizarTransferencia() {
		Double valor;
		Integer contaDestino;
		Scanner input = new Scanner(System.in);
		
		System.out.println("Insira o valor da transferência a ser realizada: ");
		valor = input.nextDouble();
		
		System.out.println("Insira a conta de destino: ");
		contaDestino = input.nextInt();
		
		Movimentacao transferencia = new Movimentacao(this.conta.getNumeroConta(), valor, TipoOperacao.TRANSFERENCIA);
		transferencia.setTipo("d");
		transferencia.setContaDestino(contaDestino);
		
		if(valor > this.conta.getSaldo()) {
			System.out.println("Saldo indisponivel");
			return false;
		}
		
		try {
			this.caixa.realizarOperacao(conta, transferencia);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	private void consultarExtrato(){
		ArrayList<Movimentacao> extrato;
		try {
			extrato = (ArrayList<Movimentacao>) this.caixa.consultarExtrato(this.conta);

			System.out.println("================== Extrato ==================");
			for(Movimentacao mov : extrato) {
				String sign = mov.getTipo().equals("c") ? "+" : "-";
				String valor = String.format("%1s%.2f", sign, mov.getValor());
				System.out.printf("%15s\t%15s\t\t%15s\n", mov.getData().toLocaleString(), mov.getTipoOperacao().name(), valor);
			}
						
			System.out.printf("\n\t\t\t\tSaldo Final: %10.2f", this.conta.getSaldo());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}
}
