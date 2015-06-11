package br.ucb.modelo.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ucb.modelo.negocio.Curso;

public class CursoDAO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Connection con;

	public CursoDAO() throws SQLException {
		this.con = ConnectionFactory.getConnection();
	}

	public int incluir(Curso curso) throws SQLException {
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

	public List<Curso> listar() throws SQLException {
		String sql = "SELECT * FROM curso";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		List<Curso> cursos = new ArrayList<Curso>();
		while (rs.next()) {
			Curso curso = new Curso();
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

}
