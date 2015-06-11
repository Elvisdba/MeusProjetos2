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

@WebServlet("/cursoListar")
public class CursoListar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    RequestDispatcher dispatcher;
	    CursoDAO cursoDao;
	    List <Curso> cursos;
	    try {
	      cursoDao = new CursoDAO();
	      cursos = cursoDao.listar();
	      request.setAttribute("cursos", cursos);
	    } catch (SQLException e) {
	      request.setAttribute("erro", "Erro de banco de dados");
	    }
	    dispatcher = request.getRequestDispatcher("cursoLista.jsp");
	    dispatcher.forward(request, response);
	}

}
