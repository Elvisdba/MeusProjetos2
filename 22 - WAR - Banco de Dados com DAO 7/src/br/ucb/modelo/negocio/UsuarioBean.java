package br.ucb.modelo.negocio;

import java.io.Serializable;

import br.ucb.modelo.enumerador.Perfil;

public class UsuarioBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String nome;
	private Perfil perfil;
	private String usuario;
	private String senha;
	
	public UsuarioBean() {
	}
	
	public boolean validarSenha(String senha) {
		return this.senha.equals(senha);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSenha() {
		return senha;
	}

}