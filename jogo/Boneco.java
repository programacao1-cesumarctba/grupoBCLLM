/**
 * Classe que cuida da vida e inicializa o Boneco do Jogo.
 */

package com.centuri123.jogo;

public class Boneco {
	private int vida = 6;
	public final char[][] desenhoBoneco = new char[4][3];
	
	public Boneco() {
		this.iniDesenhoBoneco();
	}
	
	private void iniDesenhoBoneco(){
		int i = 0, j = 0;
		for(i=0;i<4;i++){
			for(j=0;j<3;j++){
				if(i == 0 && j == 0){
					this.desenhoBoneco[i][j] = 95;
				}
				this.desenhoBoneco[i][j] = ' ';
			}
		}
	}
	
	public void removeVida(){
		this.setVida(1);
	}
	
	public int getVida(){
		return this.vida;
	}

	private void setVida(int num){
		this.vida -= num;
	}
	
}
