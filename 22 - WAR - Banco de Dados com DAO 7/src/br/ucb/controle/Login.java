package br.ucb.controle;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ucb.modelo.DAO.UsuarioDAO;
import br.ucb.modelo.negocio.UsuarioBean;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		HttpSession session = request.getSession(true);
		String acao=request.getParameter("acao"), pagina=null;
		UsuarioBean usuario = new UsuarioBean();
		
		try {
			if (acao == null) {
				usuario = (new UsuarioDAO()).consultar(request.getParameter("usuario"));
				if ((usuario != null) && (usuario.validarSenha(request.getParameter("senha")))) {
					session.setAttribute("usuarioLogado", usuario);
					request.setAttribute("mensagem", "Usuario logado");
					pagina = "/index.jsp";
				}
				else {
					request.setAttribute("erro", "Usuario ou senha incorretos");
					pagina = "/login.jsp";
				}
			}
			if ((acao != null) && (acao.equals("logoff"))) {
				session.removeAttribute("usuarioLogado");
				request.setAttribute("mensagem", "Usuario deslogado");
				pagina = "/index.jsp";
			}			
		} catch (SQLException e) {
			request.setAttribute("erro", "Erro de banco de dados ("+ e.getMessage() +")");
			pagina = "/login.jsp";
		}
		dispatcher = request.getRequestDispatcher(pagina);
		dispatcher.forward(request, response);
	}

}