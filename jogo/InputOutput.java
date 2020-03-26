/**
 * Classe que recebe todo tipo de input do Jogador (Dados cadastrais e valores para o jogo)
 */

package com.centuri123.jogo;

import java.util.Scanner;

public class InputOutput {
	private String nome;
	private int RA;
	private int senha;
	private char letra;

	public void setValoresUsuario() {  //Falta melhoria para utilizar o Cadastro do Jogador - Em Andamento.
		Scanner scanNome = new Scanner(System.in);
		Scanner scanRA = new Scanner(System.in);
		Scanner scanSenha = new Scanner(System.in);
	
		System.out.println("Digite seu nome: ");
		this.setNome(scanNome.next());	
		System.out.println("Digite seu RA: ");
		this.setRA(scanRA.nextInt());
		System.out.println("Digite sua senha: ");
		this.setSenha(scanSenha.nextInt());
		
		//this.destructScan(scanNome);
		//this.destructScan(scanRA);
		//this.destructScan(scanSenha);
	}
	
	public void setLetras() {
		Scanner scanLetra = new Scanner(System.in);
		System.out.println("Digite uma Letra: ");
		this.letra = scanLetra.next().charAt(0);
	}
	
	public void exibeBoneco(Boneco boneco){
		int i = 0, j = 0;
		for(i=0;i<4;i++){
			for(j=0;j<3;j++){
				System.out.printf("%c", boneco.desenhoBoneco[i][j]);
			}
			System.out.printf("\n");
		}
	}
	
	public boolean validaLetraRepetida(String letrasUtilizadas) {
		if(letrasUtilizadas.indexOf(this.getLetra()) != -1) {
			return true;
		}
		return false;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getRA() {
		return this.RA;
	}

	public void setRA(int RA) {
		this.RA = RA;
	}

	public int getSenha() {
		return this.senha;
	}

	public void setSenha(int senha) {
		this.senha = senha;
	}
	
	public char getLetra() {
		return this.letra;
	}
	
	private void destructScan(Scanner scan) {
		scan.close();
	}
	
}
