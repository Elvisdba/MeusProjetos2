package br.ucb.modelo.DAO;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.ucb.modelo.negocio.CursoBean;

public class CursoDAO {
	private Connection con;

	public CursoDAO() throws SQLException {
		this.con = ConnectionFactory.getConnection();
	}

	public int incluir(CursoBean curso) throws SQLException {
		if (curso == null)
			return 0;
		String sql="INSERT INTO curso (nome, semestres, valor) values (?, ?, ?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, curso.getNome());
		stmt.setInt(2, curso.getSemestres());
		stmt.setFloat(3, curso.getValor());
		int retorno = stmt.executeUpdate();
		stmt.close();
		return retorno;
	}

	public List<CursoBean> listar() throws SQLException {
		String sql = "SELECT * FROM curso";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		List<CursoBean> cursos = new ArrayList<CursoBean>();
		while (rs.next()) {
			CursoBean curso = new CursoBean();
			curso.setId(rs.getLong("id"));
			curso.setNome(rs.getString("nome"));
			curso.setSemestres(rs.getInt("semestres"));
			curso.setValor(rs.getFloat("valor"));
			cursos.add(curso);
		}
		rs.close();
		stmt.close();
		return cursos;	
	}

	public List<CursoBean> listar(String filtro) throws SQLException {
		String sql = "SELECT * FROM curso WHERE nome LIKE ?";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setString(1, filtro + "%");
		ResultSet rs = stmt.executeQuery();
		List<CursoBean> cursos = new ArrayList<CursoBean>();
		while (rs.next()) {
			CursoBean curso = new CursoBean();
			curso.setId(rs.getLong("id"));
			curso.setNome(rs.getString("nome"));
			curso.setSemestres(rs.getInt("semestres"));
			curso.setValor(rs.getFloat("valor"));
			cursos.add(curso);
		}
		rs.close();
		stmt.close();
		return cursos;	
	}

	public CursoBean consultar(Long id) throws SQLException {
		String sql = "SELECT * FROM curso WHERE id=?";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setLong(1, id);
		ResultSet rs = stmt.executeQuery();
		CursoBean curso = null;
		if (rs.first()) {
			curso = new CursoBean();
			curso.setId(rs.getLong("id"));
			curso.setNome(rs.getString("nome"));
			curso.setSemestres(rs.getInt("semestres"));
			curso.setValor(rs.getFloat("valor"));
		}
		rs.close();
		stmt.close();
		return curso;	
	}

	public int alterar(CursoBean curso) throws SQLException {
		if (curso == null)
			return 0;
		String sql="UPDATE curso SET nome=?, semestres=?, valor=? WHERE id=?";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setString(1, curso.getNome());
		stmt.setInt(2, curso.getSemestres());
		stmt.setFloat(3, curso.getValor());
		stmt.setLong(4, curso.getId());
		int retorno = stmt.executeUpdate();
		stmt.close();
		return retorno;
	}

	public int excluir(CursoBean curso) throws SQLException {
		if (curso == null)
			return 0;
		String sql = "DELETE FROM curso WHERE id=?";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setLong(1, curso.getId());
		int retorno = stmt.executeUpdate();		
		stmt.close();
		return retorno;
	}

}
