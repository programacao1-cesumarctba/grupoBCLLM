package com.centuri123.jogo;

import com.centuri123.DAO.DAO;
public class Palavra {
	private char[] palavra;
	private String dica;
	private int idSorteadoAnt;
	private int idSorteadoNovo;
	
	public Palavra() {
		this.idSorteadoAnt = 0;
	}
	
	public char[] getPalavra() {
		return this.palavra;
	}
	
	public String getDica() {
		return this.dica;
	}

	public void setPalavra(char[] palavra) {
		this.palavra = palavra;
	}
	
	public void sorteiaPalavra(){
		DAO dao = new DAO();
		int i = 0;
		try {
			if(idSorteadoAnt == 0) {
				this.idSorteadoAnt = (int)(Math.random()*dao.getCountPalavras())+1;
				this.idSorteadoNovo = this.idSorteadoAnt;
				this.palavra = dao.selectPalavra(idSorteadoNovo);
				this.dica = dao.selectDica(idSorteadoNovo);
//				System.out.println("Num sorteado: " + this.idSorteadoAnt);
			}else{
				this.idSorteadoNovo = (int)(Math.random()*dao.getCountPalavras())+1;
				while(this.idSorteadoNovo == this.idSorteadoAnt) {
					this.idSorteadoNovo = (int)(Math.random()*dao.getCountPalavras())+1;
				}
				this.idSorteadoAnt = this.idSorteadoNovo;
				this.palavra = dao.selectPalavra(idSorteadoNovo);
				this.dica = dao.selectDica(idSorteadoNovo);
//				System.out.println("Num sorteado: " + this.idSorteadoNovo);
			}
		
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} 
		//Testes para pegar do banco as palavras e dicas
		//try {		
		//		this.dica = dao.selectDica(1);
		//		this.palavra = dao.selectPalavra(1);
		//		
		//	}catch (ClassNotFoundException e) {
		//		e.printStackTrace();
		//	 }
		
	}
}
