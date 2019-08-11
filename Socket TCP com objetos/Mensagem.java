package tcp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Mensagem implements Serializable {
	public enum TipoOperacao {
		SOMA, MULTIPLICACAO, DIVISAO, SUBTRACAO, REQUISICOES_ATENDIDAS
	}
	private TipoOperacao tipoOperacao;
	private ArrayList<BigDecimal> numeros;
	public Mensagem(TipoOperacao tipoOperacao, ArrayList<BigDecimal> numeros) {
		super();
		this.tipoOperacao = tipoOperacao;
		this.numeros = numeros;
	}
	public Mensagem() {
		super();
	}
	public ArrayList<BigDecimal> getNumeros() {
		return numeros;
	}
	public void setNumeros(ArrayList<BigDecimal> numeros) {
		this.numeros = numeros;
	}
	public TipoOperacao getTipoOperacao() {
		return tipoOperacao;
	}
	public void setTipoOperacao(TipoOperacao tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}
}
