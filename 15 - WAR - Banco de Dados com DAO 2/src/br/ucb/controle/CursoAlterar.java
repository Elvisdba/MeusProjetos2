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

@WebServlet("/cursoAlterar")
public class CursoAlterar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    RequestDispatcher dispatcher;
	    Curso curso = new Curso();
	    CursoDAO cursoDao;
	    try {
	      cursoDao = new CursoDAO();
	      try {
    	      curso.setId(Long.parseLong(request.getParameter("id")));
    	      curso.setNome(request.getParameter("nome"));
    	      curso.setSemestres(Integer.parseInt(request.getParameter("semestres")));
    	      curso.setValor(Float.parseFloat(request.getParameter("valor")));
    	      if (cursoDao.alterar(curso) > 0)
    	    	  request.setAttribute("mensagem", "Alterado com sucesso");
    	      else
    	    	  request.setAttribute("erro", "Erro de alteração");
	    	  }
    	  catch (NumberFormatException e) {
    	    request.setAttribute("erro", "Erro de conversao");
    	  }
	    } catch (SQLException e) {
	      request.setAttribute("erro", "Erro de banco de dados");
	    }
	    dispatcher = request.getRequestDispatcher("cursoAltera.jsp");
	    dispatcher.forward(request, response);
	}

}
