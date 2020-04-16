/**
 * Classe que efetua a contagem e exibe o Placar do Jogo.
 */

package com.centuri123.jogo;

import java.util.List;

import com.centuri123.DAO.DAO;

public class Placar {
	private int quantErro;
	private int	quantAcerto;
	private int vit = 0;
	private int der = 0;
	private int pontuacao = 0;
	private List<Integer> valores;
	private char[] palavraAux = new char[50];
	private String letrasUtilizadas;
	
	public Placar() {
		int i;
		
		this.setQuantAcerto();
		this.setQuantErro();
		this.limpaLetrasUtilizadas();
		
		for(i=0;i<50;i++) {
			this.palavraAux[i] = '-';
		}
	}
	
	public void inicializaPontuacao() {
		DAO dao = new DAO();
		try {
			this.valores = dao.selectPontuacao(14545);
			this.setVit();
			this.setDer();
			this.setPontuacao();
			
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		
	}	
	
	public void exibePlacar(Boneco boneco, char[] palavraSorteada, InputOutput inOut, Jogador jogador, Palavra objPalavra) {
		int i;
		if(!inOut.validaLetraRepetida(this.getLetrasUtilizadas())) {
			this.setLetrasUtilizadas(inOut.getLetra());
		}
		System.out.print("\n");
		for(i=0;i<palavraSorteada.length;i++) {
			if(palavraSorteada[i] == Character.toUpperCase(inOut.getLetra())) {
				this.palavraAux[i] = palavraSorteada[i];
				System.out.printf("%c", this.palavraAux[i]);
			}else {
				if(this.palavraAux[i] != '-') {
					System.out.printf("%c", this.palavraAux[i]);	
				}else {
					System.out.printf("%c", this.palavraAux[i]);	
				}
			}
		}
		
		System.out.printf("\n\nJogador: %s    Pontuação Atual: %d     Vida Rest.: %d    ", jogador.getNome(), this.getPontuacao(), boneco.getVida());
		System.out.printf("\n\nDica da Palavra: %s", objPalavra.getDica());
		System.out.printf("\n\nLetras Utilizadas: %s \n", this.getLetrasUtilizadas());
		//System.out.printf("\nQuantidade Acertos: %d\n", this.getQuantAcerto());
		//System.out.printf("Vidas Restantes: %d\n", boneco.getVida());
		//System.out.printf("Quantidade Erros: %d\n", this.getQuantErro());
	}
	
	public void exibePlacar(Boneco boneco, char[] palavraSorteada, Jogador jogador, Palavra objPalavra) { //Sobrecarga
		int i = 0;
		System.out.print("\n");
		for(i=0;i<palavraSorteada.length;i++) {
			System.out.printf("%c", this.palavraAux[i]);
		}
		System.out.printf("\n\nJogador: %s    Pontuação Atual: %d     Vida Rest.: %d    ", jogador.getNome(), this.getPontuacao(), boneco.getVida());
		System.out.printf("\n\nDica da Palavra: %s", objPalavra.getDica());
		System.out.printf("\n\nLetras Utilizadas: %s \n\n", this.getLetrasUtilizadas());		
	}
	
	public void exibeVitoria() {
		System.out.println("Você venceu");
	}
	
	public void exibeDerrota() {
		System.out.println("Você Perdeu");
	}
	
	public void salvaPontuacao(Jogador jogador) {
		DAO dao = new DAO();
		
		try {
			dao.insertPontuacao(jogador.getRA(), this.vit, this.der, this.pontuacao);
			
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		
	}	

	public int getQuantErro() {
		return quantErro;
	}
	
	private void setQuantErro() {
		this.quantErro = 0;
	}
	
	private void setQuantAcerto() {
		this.quantAcerto = 0;
	}
	
	public void addQuantErro() {
		this.quantErro++;
	}
	
	public int getQuantAcerto() {
		return quantAcerto;
	}
	
	public void addQuantAcerto() {
		this.quantAcerto++;
	}
	
	public int getPontuacao() {
		return this.pontuacao;
	}
	
	public void setVit() {
		this.vit = this.valores.get(0);
	}

	public void setDer() {
		this.der = this.valores.get(1);
	}
	
	public void setPontuacao() {
		this.pontuacao = this.valores.get(2);
	}
	
	public void addVit() {
		this.vit++;
	}
	
	public void addDer() {
		this.der++;
	}
	
	public void addPontuacao() {
		this.pontuacao += 10;
	}
	
	public void remPontuacao() {
		if(this.pontuacao >= 5) {
			this.pontuacao -= 5;
		}
	}
	
	public void setLetrasUtilizadas(char letra) {
		this.letrasUtilizadas += Character.toUpperCase(letra);
	}
	
	public String getLetrasUtilizadas() {
		return this.letrasUtilizadas;
	}
	

	
	private void limpaLetrasUtilizadas() {
		this.letrasUtilizadas = " ";
	}	
}
