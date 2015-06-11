package br.ucb.modelo.enumerador;

public enum Perfil {
	ADMINISTRADOR,
	USUARIO;
	
	public Perfil[] getValores() {
		return Perfil.values();
	}
	
}