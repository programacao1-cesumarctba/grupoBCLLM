/**
 * Classe que efetua o CRUD com os dados do Jogador cadastrado no Banco de Dados.
 */

package com.centuri123.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.centuri123.connection.ConnectionFactory;
import com.centuri123.jogo.Jogador;


public class DAO {
	
	/*public int selectJogador(int RA) throws ClassNotFoundException{ //Em andamento, vou utilizar ArrayList nesse select.
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int resultado = 0;
		String query = "";
		
		query += "SELECT * FROM jogador ";
		query += "WHERE RA = ?";
		
		con = ConnectionFactory.getConnection();
		try {
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();
			rs.next();
			resultado = rs.getInt(RA);
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection(con, stmt);
		}
		return resultado;
	}*/
	
	public void insertJogador(Jogador jogador) throws ClassNotFoundException{
		Connection con = null;
		PreparedStatement stmt = null;

		String query = "INSERT INTO jogador(RA,nome,senha)" + " VALUES(?,?,?)";
		
		con = ConnectionFactory.getConnection();
		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1,jogador.getRA());
			stmt.setString(2, jogador.getNome());
			stmt.setInt(3, jogador.getSenha());
			stmt.execute();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			
		} finally{
			ConnectionFactory.closeConnection(con,stmt);
		}
	}
	
	public void deleteJogador(Jogador jogador) throws ClassNotFoundException{
		Connection con = null;
		PreparedStatement stmt = null;
		
		String query = "";
		      
		query += "DELETE FROM jogador ";
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
	
	public void updateJogador(Jogador jogador, int senha) throws ClassNotFoundException{
		Connection con = null;
		PreparedStatement stmt = null;
		
		String query = ""; 
				
		query += "UPDATE jogador ";
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
		String query = ""; 
				
		query += "SELECT count(id) as qtd FROM palavras ";
		
		con = ConnectionFactory.getConnection();
		try {
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();
			rs.next();
			countRegistros = rs.getInt("qtd");
		}catch(SQLException ex) {
			ex.printStackTrace();
			
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}		
		return countRegistros;
	}
}

