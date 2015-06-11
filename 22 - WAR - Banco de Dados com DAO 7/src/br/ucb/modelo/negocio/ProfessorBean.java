package br.ucb.modelo.negocio;

import java.util.ArrayList;
import java.util.List;

public class ProfessorBean extends PessoaBean {
	private static final long serialVersionUID = 1L;
	private List<CursoBean> cursos;

	public ProfessorBean() {
		this.cursos = new ArrayList<CursoBean>();
	}

	public List<CursoBean> getCursos() {
		return cursos;
	}

	public void setCursos(List<CursoBean> cursos) {
		this.cursos = cursos;
	}

}