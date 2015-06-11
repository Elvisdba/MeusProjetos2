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

@WebServlet("/cursoExcluir")
public class CursoExcluir extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		CursoDAO cursoDao;
		try {
			cursoDao = new CursoDAO();
			if (cursoDao.excluir(Long.parseLong(request.getParameter("id"))) > 0)
				request.setAttribute("mensagem", "Excluído com sucesso");
			else
				request.setAttribute("erro", "Erro de exclusão");
		} catch (SQLException e) {
			request.setAttribute("erro", "Erro de banco de dados");
		}
		dispatcher = request.getRequestDispatcher("index.html");
		dispatcher.forward(request, response);
	}

}
