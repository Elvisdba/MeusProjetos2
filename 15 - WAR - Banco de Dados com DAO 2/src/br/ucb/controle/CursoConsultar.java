package br.ucb.controle;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ucb.modelo.dao.CursoDAO;
import br.ucb.modelo.negocio.Curso;

@WebServlet("/cursoConsultar")
public class CursoConsultar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		Curso curso;
		CursoDAO cursoDao;
		try {
			cursoDao = new CursoDAO();
			curso = cursoDao.consultar(Long.parseLong(request.getParameter("id")));
			request.setAttribute("curso", curso);
		} catch (SQLException e) {
			request.setAttribute("erro", "Erro de banco de dados");
		}
		dispatcher = request.getRequestDispatcher("cursoAltera.jsp");
		dispatcher.forward(request, response);
	}

}
