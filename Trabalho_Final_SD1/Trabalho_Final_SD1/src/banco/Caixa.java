package banco;

import java.math.BigDecimal;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Caixa extends Remote {
	Conta login(Conta contaLogin) throws RemoteException;
	Conta realizarOperacao(Conta contaLogada, Movimentacao movimentacao) throws RemoteException;
	List<Movimentacao> consultarExtrato(Conta contaLogada) throws RemoteException;
}
