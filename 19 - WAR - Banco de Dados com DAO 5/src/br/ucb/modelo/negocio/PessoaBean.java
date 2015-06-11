package br.ucb.modelo.negocio;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.ucb.modelo.enumerador.EstadoCivil;

public class PessoaBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String nome;
	private EstadoCivil estadoCivil;
	private String email;
	private Date dtaNasc;
	
	public PessoaBean() {
	}
	
	public final long getId() {
		return id;
	}

	public final void setId(long id) {
		this.id = id;
	}

	public final String getNome() {
		return nome;
	}
	
	public final void setNome(String nome) {
		this.nome = nome;
	}
	
	public final EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public final void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public final String getEmail() {
		return email;
	}

	public final void setEmail(String email) {
		this.email = email;
	}

	public Date getDtaNasc() {
		return dtaNasc;
	}
	
	public String getFmtDtaNasc() {
		if (this.dtaNasc == null)
			return null;
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(this.dtaNasc);
	}
	
	public final void setDtaNasc(Date dtaNasc) {
		this.dtaNasc = dtaNasc;
	}

}