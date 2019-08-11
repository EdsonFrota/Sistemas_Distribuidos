package banco;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Movimentacao implements Serializable {
	private Integer conta;
	private Integer contaDestino;
	private Double valor;
	private TipoOperacao tipoOperacao;
	private String tipo;
	private Date data;
	
	public enum TipoOperacao {
		SAQUE("s"),
		DEPOSITO("d"),
		TRANSFERENCIA("t");
		
		private String c;
		
		private TipoOperacao(String c) {
			this.c = c;
		}
		
		public String getValue() {
			return this.c;
		}
		
		public static TipoOperacao getEnum(String c) {
			switch(c) {
				case "s":
					return SAQUE;
				case "d":
					return DEPOSITO;
				case "t":
					return TRANSFERENCIA;
					
			}
			return null;
		}
	}
	
	public Movimentacao(Integer conta, Double valor, TipoOperacao tipoOperacao) {
		super();
		this.conta = conta;
		this.valor = valor;
		this.tipoOperacao = tipoOperacao;
	}

	public Integer getConta() {
		return conta;
	}

	public Double getValor() {
		return valor;
	}

	public TipoOperacao getTipoOperacao() {
		return tipoOperacao;
	}

	public Integer getContaDestino() {
		return contaDestino;
	}
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setContaDestino(Integer contaDestino) {
		this.contaDestino = contaDestino;
	}

	
	
}
