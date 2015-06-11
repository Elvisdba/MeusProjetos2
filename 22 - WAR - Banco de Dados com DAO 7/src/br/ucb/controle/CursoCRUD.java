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
import javax.servlet.http.HttpSession;

import br.ucb.modelo.DAO.CursoDAO;
import br.ucb.modelo.negocio.CursoBean;

@WebServlet("/cursocrud")
public class CursoCRUD extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		HttpSession session = request.getSession(true);
		String acao=request.getParameter("acao"), pagina=null;
		CursoBean curso = new CursoBean();
		CursoDAO cursoDao;
		List <CursoBean> cursos = null;
		
		if (session.getAttribute("usuarioLogado") == null)
			pagina = "/login.jsp";
		else {
			try {
				cursoDao = new CursoDAO();
				if ((acao == null) || (acao.equals("listar"))) {
					cursos = cursoDao.listar();
					request.setAttribute("cursos", cursos);
					pagina = "/entidades/cursoLista.jsp";
				}
				if ((acao != null) && (acao.equals("filtrar"))) {
					cursos = cursoDao.listar(request.getParameter("nomeFiltro"));
					request.setAttribute("cursos", cursos);
					pagina = "/entidades/cursoLista.jsp";
				}
				if ((acao != null) && (acao.equals("incluir"))) {
					request.setAttribute("curso", curso);
					pagina = "/entidades/cursoEntrada.jsp";		
				}
				if ((acao != null) && (acao.equals("alterar"))) {
					curso = cursoDao.consultar(Long.valueOf(request.getParameter("id")));
					request.setAttribute("curso", curso);
					pagina = "/entidades/cursoEntrada.jsp";		
				}
				if ((acao != null) && (acao.equals("salvar"))) {
					try {
						// Recebe valores do formulario
						if (request.getParameter("nome") != null)
							curso.setNome(request.getParameter("nome"));
						if (request.getParameter("semestres") != null)
							curso.setSemestres(Integer.parseInt(request.getParameter("semestres")));
						if (request.getParameter("valor") != null)
							curso.setValor(Float.parseFloat(request.getParameter("valor").replace(',', '.')));
						// Salva: inclui ou altera
						if (request.getParameter("id").equals("0")) { // Incluir
							if (cursoDao.incluir(curso) > 0)
								request.setAttribute("mensagem", "Incluído com sucesso");
							else
								request.setAttribute("erro", "Erro de inclusão");
						}
						else { // Alterar
							curso.setId(Long.valueOf(request.getParameter("id")));
							if (cursoDao.alterar(curso) > 0)
								request.setAttribute("mensagem", "Alterado com sucesso");
							else
								request.setAttribute("erro", "Erro de alteração");
						}
						cursos = cursoDao.listar();
						request.setAttribute("cursos", cursos);
						pagina = "/entidades/cursoLista.jsp";
					}
					catch (NumberFormatException e) {
						request.setAttribute("erro", "Erro de conversao de numero");
						pagina = "/entidades/cursoEntrada.jsp";		
					}
				}
				if ((acao != null) && (acao.equals("excluir"))) {
					curso = cursoDao.consultar(Long.valueOf(request.getParameter("id")));
					if (cursoDao.excluir(curso) > 0)
						request.setAttribute("mensagem", "Excluído com sucesso");
					else
						request.setAttribute("erro", "Erro de exclusão");
					cursos = cursoDao.listar();
					request.setAttribute("cursos", cursos);
					pagina = "/entidades/cursoLista.jsp";
				}
			} catch (SQLException e) {
				request.setAttribute("erro", "Erro de banco de dados ("+ e.getMessage() +")");
				pagina = "/entidades/cursoLista.jsp";
			}
		}
		dispatcher = request.getRequestDispatcher(pagina);
		dispatcher.forward(request, response);
	}

}