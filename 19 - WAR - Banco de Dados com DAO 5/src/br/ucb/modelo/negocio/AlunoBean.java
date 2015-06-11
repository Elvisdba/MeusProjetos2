package br.ucb.modelo.negocio;

import java.io.Serializable;

public class AlunoBean extends PessoaBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private int matricula;
	private boolean ativo;
	private CursoBean curso;
	
	public AlunoBean() {
		this.curso = new CursoBean();
	}
	
	public final int getMatricula() {
		return matricula;
	}
	
	public final void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	
	public final boolean getAtivo() {
		return ativo;
	}
	
	public final void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public void setCurso(CursoBean curso) {
		this.curso = curso;
	}

	public CursoBean getCurso() {
		return curso;
	}
	
}