package com.hospital.medico.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "medico")
public class Medico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idmedico")
	private Integer idMedico;

	@Column(name = "nome", length = 150, nullable = false)
	private String nome;

	@Column(name = "crm",  nullable = false)
	private String crm;

	@Column(name = "telefone",  nullable = false)
	private String telefone;

	@Column(name = "tipo", length = 500, nullable = false)
	private String tipo;

	public Medico() {
		// TODO Auto-generated constructor stub
	}

	public Medico(Integer idMedico, String nome, String crm, String telefone, String tipo) {
		super();
		this.idMedico = idMedico;
		this.nome = nome;
		this.crm = crm;
		this.telefone = telefone;
		this.tipo = tipo;
	}

	public Integer getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(Integer idMedico) {
		this.idMedico = idMedico;
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
		return "Medico [idMedico=" + idMedico + ", nome=" + nome + ", crm=" + crm + ", Telefone=" + telefone
				+ ", tipo=" + tipo + "]";
	}

}
