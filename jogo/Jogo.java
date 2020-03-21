/**
 * Classe que efetua o processamento dos dados e executa as operações necessárias para rodar o Jogo.
 */

package com.centuri123.jogo;

public class Jogo {
	private boolean flagJogo = true;
	private boolean flagVence = true; 
	private boolean flagErro = false;
	
	public Jogo() {
		InputOutput inOut = new InputOutput();
		Boneco boneco = new Boneco();
		Placar placar = new Placar();
		inOut.setLetras();
		this.rodaJogo(inOut, placar, boneco);
	}
	
	private void rodaJogo(InputOutput inOut, Placar placar, Boneco boneco) {
		int i = 0;
		char[] palavra = {'T','E','S','T','E'}; //Em andamento classe que recebe do BD as palavras sorteadas.
		while(this.getFlagJogo()){
			for(i=0;i<palavra.length;i++){
				if(palavra[i] == inOut.getLetra()){
					this.setFlagErro(true);
					placar.addQuantAcerto();
				}else{
					if((i == (palavra.length-1)) && !(this.getFlagErro())){
						placar.addQuantErro();
						boneco.removeVida();
						if(placar.getQuantErro() == 1){
							boneco.desenhoBoneco[0][1] = 79;
						}else if(placar.getQuantErro() == 2){
							boneco.desenhoBoneco[1][0] = 47;
						}else if(placar.getQuantErro() == 3){
							boneco.desenhoBoneco[1][1] = 105;
						}else if(placar.getQuantErro() == 4){
							boneco.desenhoBoneco[1][2] = 92;
						}else if(placar.getQuantErro() == 5){
							boneco.desenhoBoneco[2][0] = 47;
						}else if(placar.getQuantErro() == 6){
							boneco.desenhoBoneco[2][2] = 92;
						}
					}
				}
			}
			this.setFlagErro(false);
			if(placar.getQuantErro() > 0){
				inOut.exibeBoneco(boneco);
			}
			if(placar.getQuantAcerto() == palavra.length){
				break;
			}else if(boneco.getVida() == 0){
				this.setFlagVence(false);
				break;
			}
			System.out.printf("\n");
			placar.exibePlacar(boneco);
			inOut.setLetras();
		}
		if(this.getFlagVence()){
			placar.exibeVitoria();
		}else{
			placar.exibeDerrota();
		}
	}

	
	public boolean getFlagJogo() {
		return this.flagJogo;
	}
	
	public void setFlagjogo(boolean flagJogo) {
		this.flagJogo = flagJogo;
	}
	
	public boolean getFlagErro() {
		return this.flagErro;
	}
	
	public void setFlagErro(boolean flagErro) {
		this.flagErro = flagErro;
	}
	
	public boolean getFlagVence() {
		return this.flagVence;
	}
	
	public void setFlagVence(boolean flagVence) {
		this.flagVence = flagVence;
	}
}
