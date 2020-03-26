/**
 * Classe que efetua a contagem e exibe o Placar do Jogo.
 */

package com.centuri123.jogo;

public class Placar {
	private int quantErro = 0;
	private int	quantAcerto = 0;
	private char[] palavraAux = new char[20];
	private String letrasUtilizadas = " ";
	
	public Placar() {
		int i;
		
		for(i=0;i<20;i++) {
			this.palavraAux[i] = '-';
		}
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
		this.letrasUtilizadas += letra;
	}
	
	public String getLetrasUtilizadas() {
		return this.letrasUtilizadas;
	}
	
	public void exibePlacar(Boneco boneco, char[] palavra, InputOutput inOut) {
		int i;
		if(!inOut.validaLetraRepetida(this.getLetrasUtilizadas())) {
			this.setLetrasUtilizadas(inOut.getLetra());
		}
		for(i=0;i<palavra.length;i++) {
			if(palavra[i] == inOut.getLetra()) {
				this.palavraAux[i] = palavra[i];
				System.out.printf("%c", this.palavraAux[i]);
			}else {
				if(this.palavraAux[i] != '-') {
					System.out.printf("%c", this.palavraAux[i]);	
				}else {
					System.out.printf("%c", this.palavraAux[i]);	
				}
			}
		}
		
		System.out.printf("\n\nLetras Utilizadas: %s", this.getLetrasUtilizadas());
		System.out.printf("\nQuantidade Acertos: %d\n", this.getQuantAcerto());
		System.out.printf("Vidas Restantes: %d\n", boneco.getVida());
		System.out.printf("Quantidade Erros: %d\n", this.getQuantErro());
	}
	
	public void exibeVitoria() {
		System.out.println("Você venceu");
	}
	
	public void exibeDerrota() {
		System.out.println("Você Perdeu");
	}

}
