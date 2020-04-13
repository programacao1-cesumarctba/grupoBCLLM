package com.centuri123.jogo;

import com.centuri123.DAO.DAO;
public class Palavra {
	private char[] palavra;
	private int idSorteadoAnt;
	private int idSorteadoNovo;
	
	public Palavra() {
		this.idSorteadoAnt = 0;
	}
	
	public char[] getPalavra() {
		return this.palavra;
	}

	public void setPalavra(char[] palavra) {
		this.palavra = palavra;
	}
	
	public void sorteiaPalavra(){ //Em andamento.
		System.out.println("Entrou aqui");
		DAO dao = new DAO();
		try {
			if(idSorteadoAnt == 0) {
				this.idSorteadoAnt = (int)(Math.random()*dao.getCountPalavras())+1;
				this.idSorteadoNovo = this.idSorteadoAnt;
				System.out.println("Num sorteado: " + this.idSorteadoAnt);
			}else{
				this.idSorteadoNovo = (int)(Math.random()*dao.getCountPalavras())+1;
				while(this.idSorteadoNovo == this.idSorteadoAnt) {
					this.idSorteadoNovo = (int)(Math.random()*dao.getCountPalavras())+1;
				}
				System.out.println("Num sorteado: " + this.idSorteadoNovo);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 	
	}
}
