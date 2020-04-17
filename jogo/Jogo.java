/**
 * Classe que efetua o processamento dos dados e executa as operações necessárias para rodar o Jogo.
 */

package com.centuri123.jogo;

import java.io.IOException;

public class Jogo {
	private boolean flagJogo = true;
	private boolean flagVence = true; 
	private boolean flagErro = false;
	
	public Jogo(){
	
		InputOutput inOut = new InputOutput();
		inOut.exibeTelaInicial();
		inOut.exibeTelaMenu();
	}
	
	public Jogo(Jogador jogador) throws InterruptedException, IOException {
		
		InputOutput inOut = new InputOutput();
		Boneco boneco = new Boneco();
		Placar placar = new Placar();
		Palavra palavras = new Palavra();
		InputOutput.limparTela(1000);
		this.rodaJogo(inOut, placar, boneco, jogador, palavras);
	}
	
	private void rodaJogo(InputOutput inOut, Placar placar, Boneco boneco, Jogador jogador, Palavra palavras) throws InterruptedException, IOException {
		
		int i = 0;
		char[] palavraSorteada;
		
		palavras.sorteiaPalavra();
		palavraSorteada = palavras.getPalavra();
		placar.exibePlacar(boneco, palavraSorteada, jogador, palavras);
		placar.inicializaPontuacao(jogador.getRA());
		inOut.setLetras();
		InputOutput.limparTela(100);
		//char[] palavra = {'T','E','S','T','E'}; //Substituido por palavra dinâmica sorteada com o ID do BD
		while(this.getFlagJogo()){
			for(i=0;i<palavraSorteada.length;i++){
				if(palavraSorteada[i] == inOut.getLetra() || palavraSorteada[i] == Character.toUpperCase(inOut.getLetra())){ 
					if(!inOut.validaLetraRepetida(placar.getLetrasUtilizadas())) {
						this.setFlagErro(true);
						placar.addQuantAcerto();
						placar.addPontuacao();
					}
					
				}else{
					if((i == (palavraSorteada.length-1)) && !(this.getFlagErro())){
						if(!inOut.validaLetraRepetida(placar.getLetrasUtilizadas())) {
							placar.addQuantErro();
							placar.remPontuacao();
							boneco.removeVida();
						}
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
			if(placar.getQuantAcerto() == palavraSorteada.length){
				break;
			}else if(boneco.getVida() == 0){
				this.setFlagVence(false);
				break;
			}
			/*Limpa cmd com timeout de 1000 milisegundos - Implementado na Classe InputOutput no método static - LimparTela(tempo).
			 Thread.sleep(1000);
			if (System.getProperty("os.name").contains("Windows")) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			}
	        else {
	        	Runtime.getRuntime().exec("clear");
	        //}*/
	            
			placar.exibePlacar(boneco, palavraSorteada, inOut, jogador, palavras);
			System.out.printf("\n");
			inOut.setLetras();
			InputOutput.limparTela(100);
		}
		placar.exibePlacar(boneco, palavraSorteada, inOut, jogador, palavras);
		if(this.getFlagVence()){
			placar.addVit();
			placar.exibeVitoria();
			InputOutput.limparTela(3500);
		}else{
			placar.addDer();
			placar.exibeDerrota();
			InputOutput.limparTela(3500);
		}
		char jogarNov = inOut.verificaJogarNovamente();
		if(jogarNov == '1') {
			placar.salvaPontuacao(jogador);
			Placar novoPlacar = new Placar();
			Boneco novoBoneco = new Boneco();
			inOut.limpaLetra();
			InputOutput.limparTela(500);
			this.rodaJogo(inOut, novoPlacar, novoBoneco, jogador, palavras);
		}else {
			placar.salvaPontuacao(jogador);
			InputOutput.limparTela(500);
			inOut.notificaSaida();
		}	

	}

	
	public boolean getFlagJogo() {
		return this.flagJogo;
	}
	
	private void setFlagjogo(boolean flagJogo) {
		this.flagJogo = flagJogo;
	}
	
	public boolean getFlagErro() {
		return this.flagErro;
	}
	
	private void setFlagErro(boolean flagErro) {
		this.flagErro = flagErro;
	}
	
	public boolean getFlagVence() {
		return this.flagVence;
	}
	
	private void setFlagVence(boolean flagVence) {
		this.flagVence = flagVence;
	}
}
