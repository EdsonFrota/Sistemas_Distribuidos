package balanceamento;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import banco.Caixa;
import banco.CaixaImpl;
import banco.Conta;
import banco.Movimentacao;

public class LoadServer extends UnicastRemoteObject implements Caixa {
	private static Integer connCounter = 0;
	private Caixa caixa1, caixa2;
	
	protected LoadServer() throws RemoteException, NotBoundException {
		super();
		connCounter++;
		Registry registry1, registry2;
		registry1 = LocateRegistry.getRegistry("localhost", 8989);
		registry2 = LocateRegistry.getRegistry("172.16.13.126", 8989);
		this.caixa1 = (Caixa) registry1.lookup("caixa");
		this.caixa2 = (Caixa) registry2.lookup("caixa");
	}
	
	public static void main(String[] args) {
		try {
			LoadServer load = new LoadServer();
			Registry registry = LocateRegistry.createRegistry(6565);
			registry.rebind("caixa", load);
			System.out.println("Servidor carregado no registry");
		} catch (Exception e) {
			System.out.println("Caixa erro: " + e.getMessage());
		}

	}

	@Override
	public Conta login(Conta contaLogin) throws RemoteException {
		connCounter++;
		return (connCounter%2 == 0) ? this.caixa1.login(contaLogin) : this.caixa2.login(contaLogin);
	}

	@Override
	public Conta realizarOperacao(Conta contaLogada, Movimentacao movimentacao) throws RemoteException {
		//connCounter++;
		return (connCounter%2 == 0) ? this.caixa1.realizarOperacao(contaLogada, movimentacao) : this.caixa2.realizarOperacao(contaLogada, movimentacao);
	}

	@Override
	public List<Movimentacao> consultarExtrato(Conta contaLogada) throws RemoteException {
		//connCounter++;
		return (connCounter%2 == 0) ? this.caixa1.consultarExtrato(contaLogada) : this.caixa2.consultarExtrato(contaLogada);
	}
}