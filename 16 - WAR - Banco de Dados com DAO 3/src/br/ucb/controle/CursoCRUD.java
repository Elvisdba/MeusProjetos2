package br.ucb.controle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ucb.modelo.dao.CursoDAO;
import br.ucb.modelo.negocio.Curso;

@WebServlet("/cursoCRUD")
public class CursoCRUD extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pagina=null, acao=request.getParameter("acao");
		RequestDispatcher dispatcher;
		Curso curso;
		CursoDAO cursoDao;
		List <Curso> cursos;

		try {
			cursoDao = new CursoDAO();
			if ((acao == null) || (acao.equals("listar"))) {
				cursos = cursoDao.listar();
				request.setAttribute("cursos", cursos);
				pagina = "cursoLista.jsp";
			}
			else if (acao.equals("alterar")) {
				cursoDao = new CursoDAO();
				curso = cursoDao.consultar(Long.parseLong(request.getParameter("id")));
				request.setAttribute("curso", curso);
				pagina = "cursoFormulario.jsp";
			}
			else if (acao.equals("salvar")) {
				try {
					curso = new Curso();
					if (!request.getParameter("id").equals(""))
						curso.setId(Long.parseLong(request.getParameter("id")));
					curso.setNome(request.getParameter("nome"));
					curso.setSemestres(Integer.parseInt(request.getParameter("semestres")));
					curso.setValor(Float.parseFloat(request.getParameter("valor")));
					if (curso.getId() == 0) { // Inclusão
						if (cursoDao.incluir(curso) > 0) {
							request.setAttribute("mensagem", "Incluído com sucesso");
							cursos = cursoDao.listar();
							request.setAttribute("cursos", cursos);
							pagina = "cursoLista.jsp";
						}
						else {
							request.setAttribute("erro", "Erro de inclusão");
							pagina = "cursoFormulario.jsp";
						}
					}
					else { // Altração
						if (cursoDao.alterar(curso) > 0) {
							request.setAttribute("mensagem", "Alterado com sucesso");
							cursos = cursoDao.listar();
							request.setAttribute("cursos", cursos);
							pagina = "cursoLista.jsp";
						}
						else {
							request.setAttribute("erro", "Erro de alteração");
							pagina = "cursoFormulario.jsp";
						}
					}
				}
				catch (NumberFormatException e) {
					request.setAttribute("erro", "Erro de conversao");
					cursos = cursoDao.listar();
					request.setAttribute("cursos", cursos);
					pagina = "cursoLista.jsp";
				}
			}
			else if (acao.equals("excluir")) {  
				cursoDao = new CursoDAO();
				if (cursoDao.excluir(Long.parseLong(request.getParameter("id"))) > 0)
					request.setAttribute("mensagem", "Excluído com sucesso");
				else
					request.setAttribute("erro", "Erro de exclusão");
				cursos = cursoDao.listar();
				request.setAttribute("cursos", cursos);
				pagina = "cursoLista.jsp";
			}
		} catch (SQLException e) {
			request.setAttribute("erro", "Erro de banco de dados");
			pagina = "cursoLista.jsp";
		}
		dispatcher = request.getRequestDispatcher(pagina);
		dispatcher.forward(request, response);
	}

}
