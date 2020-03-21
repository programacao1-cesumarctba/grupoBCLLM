/**
 * Classe que cria e fecha a conex�o com o Banco de Dados.
 */

package com.centuri123.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.sql.SQLException;

public class ConnectionFactory {
	private final static String DRIVE = "com.mysql.cj.jdbc.Driver";
	
	public static Connection getConnection() throws ClassNotFoundException{
		try {
			Class.forName(DRIVE);
			return DriverManager.getConnection("jdbc:mysql://localhost/projeto?user=root&useTimezone=true&serverTimezone=UTC");
			
		} catch (SQLException e) {
			throw new RuntimeException("Erro na conex�o com o BD: ",e);
		}
		
	}
	
	public static void closeConnection(Connection con) {
		
		try {
			if(con != null) {
				con.close();
			}
		} catch (SQLException e) {
			
			throw new RuntimeException("Erro no Fechamento da Conex�o: ", e);
		}
		
	}

	public static void closeConnection(Connection con, PreparedStatement stmt) { //Sobrecarga do M�todo closeConnection()
		closeConnection(con);
		try {
			
			if(stmt != null) {
				stmt.close();
			}
			
		} catch (SQLException e) {
			
			throw new RuntimeException("Erro no Fechamento da Conex�o: ", e);
				
		}
	}
}
