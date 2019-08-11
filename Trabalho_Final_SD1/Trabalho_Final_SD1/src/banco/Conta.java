package banco;

import java.io.Serializable;

public class Conta implements Serializable {
	private String nomeCliente;
	private Integer numeroConta;
	private Short agencia;
	private String senha; //MUDA PLMDDS
	private Double saldo;
	
	public Conta(Integer numeroConta, Short agencia, String senha) {
		super();
		this.numeroConta = numeroConta;
		this.agencia = agencia;
		this.senha = senha;
		
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Integer getNumeroConta() {
		return numeroConta;
	}

	public Short getAgencia() {
		return agencia;
	}

	public String getSenha() {
		return senha;
	}


	
}
