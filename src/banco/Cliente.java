package banco;

import java.io.Serializable;

public class Cliente implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3922927079725575913L;
	
	private String nomeCliente;
	private String cpfCnpj;
	private String endereco;
	private String fone;
	
	public Cliente(String nomeCliente, String cpfCnpj, String endereco, String fone) {
		super();
		this.nomeCliente = nomeCliente;
		this.cpfCnpj = cpfCnpj;
		this.endereco = endereco;
		this.fone = fone;
	}
	
	//TODO: Implements other methods

	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getFone() {
		return fone;
	}
	public void setFone(String fone) {
		this.fone = fone;
	}
	
	

}
