/**
 * Classe que efetua a contagem e exibe o Placar do Jogo.
 */

package com.centuri123.jogo;

public class Placar {
	private int quantErro = 0;
	private int	quantAcerto = 0;
	
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
	
	public void exibePlacar(Boneco boneco) {
		System.out.printf("Quantidade Acertos: %d\n", this.getQuantAcerto());
		System.out.printf("Vida boneco: %d\n", boneco.getVida());
		System.out.printf("Quantidade Erros: %d\n", this.getQuantErro());
	}
	
	public void exibeVitoria() {
		System.out.println("Você venceu");
	}
	
	public void exibeDerrota() {
		System.out.println("Você Perdeu");
	}

}
