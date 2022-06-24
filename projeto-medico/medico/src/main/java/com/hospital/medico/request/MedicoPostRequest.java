package com.hospital.medico.request;


//essa class pega as inf da tela
public class MedicoPostRequest {
	private String nome;
	private String crm;
	private String telefone;
	private String tipo;

	public MedicoPostRequest() {
		// TODO Auto-generated constructor stub
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "MedicoPostRequest [nome=" + nome + ", crm=" + crm + ", telefone=" + telefone + ", tipo=" + tipo + "]";
	}
	
	


}
