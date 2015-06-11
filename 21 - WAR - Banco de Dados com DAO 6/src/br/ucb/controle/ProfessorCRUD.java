package br.ucb.controle;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ucb.modelo.DAO.CursoDAO;
import br.ucb.modelo.DAO.ProfessorDAO;
import br.ucb.modelo.enumerador.EstadoCivil;
import br.ucb.modelo.negocio.CursoBean;
import br.ucb.modelo.negocio.ProfessorBean;

@WebServlet("/professorcrud")
public class ProfessorCRUD extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		String acao=request.getParameter("acao"), pagina=null;
		ProfessorBean professor = new ProfessorBean();
		ProfessorDAO professorDao;
		CursoDAO cursoDao;
		List <ProfessorBean> professores;
		List <CursoBean> cursos;
		
		try {
			professorDao = new ProfessorDAO();
			cursoDao = new CursoDAO();
			if ((acao == null) || (acao.equals("listar"))) {
				professores = professorDao.listar();
				request.setAttribute("professores", professores);
				pagina = "/entidades/professorLista.jsp";
			}
			if ((acao != null) && (acao.equals("incluir"))) {
				cursos = cursoDao.listar();
				request.setAttribute("professor", professor);
				request.setAttribute("cursos", cursos);
				request.setAttribute("estadosCivis", EstadoCivil.SEPARADO);
				pagina = "/entidades/professorEntrada.jsp";		
			}
			if ((acao != null) && (acao.equals("alterar"))) {
				professor = professorDao.consultar(Long.valueOf(request.getParameter("id")));
				if (professor == null)
					request.setAttribute("erro", "Erro ao localizar para alteração");
				else {
					cursos = cursoDao.listar();
					request.setAttribute("professor", professor);
					request.setAttribute("cursos", cursos);
					request.setAttribute("estadosCivis", EstadoCivil.CASADO);
					pagina = "/entidades/professorEntrada.jsp";		
				}
			}
			if ((acao != null) && (acao.equals("salvar"))) {
				boolean erro=false;
				try {
					// Recebe valores do formulario
					if (request.getParameter("nome") != null)
						professor.setNome(request.getParameter("nome"));
					if (request.getParameter("email") != null)
						professor.setEmail(request.getParameter("email"));
					if (request.getParameter("estCivil") != null)
						professor.setEstadoCivil(EstadoCivil.valueOf(request.getParameter("estCivil")));
					if (request.getParameter("dtaNasc") != null) {
						DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
						df.setLenient(false);
						Date data = df.parse(request.getParameter("dtaNasc"));
						professor.setDtaNasc(data);
					}
					if (request.getParameter("cursos") != null) {
						// Recebe um vetor de String
						String[] strIdCursos = request.getParameterValues("cursos");
						for (String s : strIdCursos)    
							professor.getCursos().add(cursoDao.consultar(Long.parseLong(s)));
					}
					// Salva: inclui ou altera
					if (request.getParameter("id").equals("0")) { // Incluir
						if (professorDao.incluir(professor) > 0)
							request.setAttribute("mensagem", "Incluído com sucesso");
						else
							request.setAttribute("erro", "Erro de inclusão");
					}
					else { // Alterar
						professor.setId(Long.valueOf(request.getParameter("id")));
						if (professorDao.alterar(professor) > 0)
							request.setAttribute("mensagem", "Alterado com sucesso");
						else
							request.setAttribute("erro", "Erro de alteração");
					}
					professores = professorDao.listar();
					request.setAttribute("professores", professores);
					pagina = "/entidades/professorLista.jsp";
				}
				catch (NumberFormatException e) {
					erro = true;
					request.setAttribute("erro", "Erro de conversao de numero");
				}
				catch (ParseException e) {
					erro = true;
					request.setAttribute("erro", "Erro de conversao de data");
				}
				if (erro) {
					cursos = cursoDao.listar();
					request.setAttribute("cursos", cursos);
					request.setAttribute("estadosCivis", EstadoCivil.SEPARADO);
					pagina = "/entidades/professorEntrada.jsp";		
				}
			}
			if ((acao != null) && (acao.equals("excluir"))) {
				professor = professorDao.consultar(Long.valueOf(request.getParameter("id")));
				if (professorDao.excluir(professor) > 0)
					request.setAttribute("mensagem", "Excluído com sucesso");
				else
					request.setAttribute("erro", "Erro de exclusão");
				professores = professorDao.listar();
				request.setAttribute("professores", professores);
				pagina = "/entidades/professorLista.jsp";
			}
		} catch (SQLException e) {
			request.setAttribute("erro", "Erro de banco de dados");
			pagina = "/entidades/professorLista.jsp";
		}
		dispatcher = request.getRequestDispatcher(pagina);
		dispatcher.forward(request, response);
	}

}