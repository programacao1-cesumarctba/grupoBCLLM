/**
 * Classe que efetua a contagem e exibe o Placar do Jogo.
 */

package com.centuri123.jogo;

public class Placar {
	private int quantErro;
	private int	quantAcerto;
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
		
		System.out.printf("\n\nJogador: %s    Acertos: %d     Vida Rest.: %d    ", jogador.getNome(), this.getQuantAcerto(), boneco.getVida());
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
		System.out.printf("\n\nJogador: %s    Acertos: %d     Vida Rest.: %d    ", jogador.getNome(), this.getQuantAcerto(), boneco.getVida());
		System.out.printf("\n\nDica da Palavra: %s", objPalavra.getDica());
		System.out.printf("\n\nLetras Utilizadas: %s \n\n", this.getLetrasUtilizadas());		
	}
	
	public void exibeVitoria() {
		System.out.println("Você venceu");
	}
	
	public void exibeDerrota() {
		System.out.println("Você Perdeu");
	}

	public int getQuantErro() {
		return quantErro;
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
	
	public void setLetrasUtilizadas(char letra) {
		this.letrasUtilizadas += Character.toUpperCase(letra);
	}
	
	public String getLetrasUtilizadas() {
		return this.letrasUtilizadas;
	}
	
	private void setQuantErro() {
		this.quantErro = 0;
	}
	
	private void setQuantAcerto() {
		this.quantAcerto = 0;
	}
	
	private void limpaLetrasUtilizadas() {
		this.letrasUtilizadas = " ";
	}	
}
