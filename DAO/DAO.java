/**
 * Classe que efetua o CRUD com os dados do Jogador cadastrado no Banco de Dados.
 */

package com.centuri123.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.centuri123.connection.ConnectionFactory;
import com.centuri123.jogo.Jogador;


public class DAO {
	
	public List<Object> selectJogador() throws ClassNotFoundException{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<Object> listaJogadores = new ArrayList<Object>();

		String query =  "SELECT j.nome, r.qtd_vit, r.qtd_der, r.pontuacao FROM jogador j, ranking r ";
			   query += "WHERE j.RA = r.RA_aluno AND j.RA IS NOT NULL AND r.RA_aluno IS NOT NULL";
		
		con = ConnectionFactory.getConnection();
		try {
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();
			while(rs.next()) {
				listaJogadores.add(rs.getString("nome"));
				listaJogadores.add(rs.getString("qtd_vit"));
				listaJogadores.add(rs.getString("qtd_der"));
				listaJogadores.add(rs.getString("pontuacao"));
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection(con, stmt);
		}
		
		return listaJogadores;
	}
	
	public Jogador selectJogador(int raAluno) throws ClassNotFoundException{
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Jogador jogador = new Jogador();
		

		String  query =  "SELECT * FROM jogador ";
				query += "WHERE RA = ?";
		
		con = ConnectionFactory.getConnection();
		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, raAluno);
			rs = stmt.executeQuery();
			if(rs.next()){
				jogador.setRA(rs.getInt(1));
				jogador.setNome(rs.getString(2));
				jogador.setSenha(rs.getInt(3));
			}
			else {
				jogador = null;
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection(con, stmt);
		}
		return jogador;
	}
	
	public void insertJogador(Jogador jogador) throws ClassNotFoundException{
		
		Connection con = null;
		PreparedStatement stmt = null;

		String query =  "INSERT INTO jogador(RA, nome, senha) ";
			   query += "VALUES(?,?,?)";
		
		con = ConnectionFactory.getConnection();
		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1,jogador.getRA());
			stmt.setString(2, jogador.getNome());
			stmt.setInt(3, jogador.getSenha());
			stmt.execute();
			
		}catch (SQLException ex) {
			ex.printStackTrace();
			
		}finally{
			ConnectionFactory.closeConnection(con,stmt);
		}
	}
	
	public void createRankingJogador(int raAluno) throws ClassNotFoundException {
		Connection con = null;
		PreparedStatement stmt = null;
		
		String query =  "INSERT INTO ranking(qtd_vit, qtd_der, pontuacao, RA_aluno) ";
			   query += "VALUES(?,?,?,?)";
		
		con = ConnectionFactory.getConnection();
		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, 0);
			stmt.setInt(2, 0);
			stmt.setInt(3, 0);
			stmt.setInt(4, raAluno);
			stmt.execute();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	private void deleteJogador(Jogador jogador) throws ClassNotFoundException{
		
		Connection con = null;
		PreparedStatement stmt = null;
		
		String query =  "DELETE FROM jogador ";
		       query += "WHERE RA = ?";
		
		con = ConnectionFactory.getConnection();
		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, jogador.getRA());
			stmt.execute();
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	private void updateJogador(Jogador jogador, int senha) throws ClassNotFoundException{
		
		Connection con = null;
		PreparedStatement stmt = null;
				
		String query = "UPDATE jogador ";
			   query += "SET senha = ? ";
		       query += "WHERE RA = ?";
		
		con = ConnectionFactory.getConnection();
		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, senha);
			stmt.setInt(2, jogador.getRA());
			stmt.execute();
	
		}catch(SQLException ex) {
			ex.printStackTrace();
			
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public int getCountPalavras() throws ClassNotFoundException{
		
		int countRegistros = 0;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;	
		
		String query =  "SELECT count(id) as qtd ";
			   query += "FROM palavras";
		
		con = ConnectionFactory.getConnection();
		try {
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();
			if(rs.next()) {
				countRegistros = rs.getInt("qtd");
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		
		
		return countRegistros;
	}
	
	public char[] selectPalavra(int idSorteado) throws ClassNotFoundException {
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		char[] palavra = null;
		String aux = "";

		String query = "SELECT palavra FROM palavras ";
		       query += "WHERE id = ? AND id IS NOT NULL ";
		
		con = ConnectionFactory.getConnection();
		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1,idSorteado);
			rs = stmt.executeQuery();
			if(rs.next()) {
				aux = rs.getString(1);
				palavra = aux.toCharArray();
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		return palavra;
	}

	public String selectDica(int idSorteado) throws ClassNotFoundException {
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String dica = "";
		 
		String query =  "SELECT dica FROM dica_palavras ";
		       query += "WHERE id_palavra = ? AND id_palavra IS NOT NULL ";
		
		con = ConnectionFactory.getConnection();
		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1,idSorteado);
			rs = stmt.executeQuery();
			if(rs.next()) {
				dica = rs.getString(1);
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		return dica;
	}
	
	public List<Integer> selectPontuacao(int raAluno) throws ClassNotFoundException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Integer> valores = new ArrayList<Integer>();
		
		String query =  "SELECT qtd_vit, qtd_der, pontuacao FROM ranking ";
			   query += "WHERE RA_aluno = ? AND RA_aluno IS NOT NULL";
		
		con = ConnectionFactory.getConnection();
		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, raAluno);
			rs = stmt.executeQuery();
			while(rs.next()) {
				valores.add(0, rs.getInt(1));
				valores.add(1, rs.getInt(2));
				valores.add(2, rs.getInt(3));
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection(con, stmt);
		}
		return valores;
	}
	
	public void insertPontuacao(int raAluno, int vit, int der, int pontuacao) throws ClassNotFoundException {
		Connection con = null;
		PreparedStatement stmt = null;
		
		String query  = "UPDATE ranking ";
			   query += "SET qtd_vit = ?, qtd_der = ?, pontuacao = ? ";
			   query += "WHERE RA_aluno = ?";
	
	   con = ConnectionFactory.getConnection();
	   try {
		   stmt = con.prepareStatement(query);
		   stmt.setInt(1, vit);
		   stmt.setInt(2, der);
		   stmt.setInt(3, pontuacao);
		   stmt.setInt(4, raAluno);
		   stmt.execute();
	   }catch(SQLException ex) {
		   ex.printStackTrace();
	   }finally {
		   ConnectionFactory.closeConnection(con, stmt);
	   }
	}
	
}

