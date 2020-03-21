package com.centuri123.jogo;

import com.centuri123.DAO.DAO;
public class Palavra {
	private char[] palavra;
	private int idSorteado;
	
	public char[] getPalavra() {
		return this.palavra;
	}

	public void setPalavra(char[] palavra) {
		this.palavra = palavra;
	}
	
	public void sorteiaPalavra(){ //Em andamento.
			
		DAO dao = new DAO();
		try {
			this.idSorteado = (int)(Math.random()*dao.getCountPalavras())+1;
			System.out.println(idSorteado);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 	
	}
}
