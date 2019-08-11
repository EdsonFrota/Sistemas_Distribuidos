package banco;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
	public static void main(String[] args) {
		try {
			CaixaImpl caixa = new CaixaImpl();
			Registry registry = LocateRegistry.createRegistry(8989);
			registry.rebind("caixa", caixa);
			System.out.println("Servidor carregado no registry");
		} catch (Exception e) {
			System.out.println("Caixa erro: " + e.getMessage());
		}

	}
}
