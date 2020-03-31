/**
 * Classe que recebe todo tipo de input do Jogador (Dados cadastrais e valores para o jogo)
 */

package com.centuri123.jogo;

import java.io.IOException;
import java.util.Scanner;
import com.centuri123.DAO.DAO;

public class InputOutput {
	private char letra;
	
	public void cadastraJogador() { // Nova melhoria para Tela Cadastral
		Scanner scanNome = new Scanner(System.in);
		Scanner scanRA = new Scanner(System.in);
		Scanner scanSenha = new Scanner(System.in);
		String nome;
		int RA, senha;
		DAO dao = new DAO();

		this.carregaBordas(1);
		System.out.println("+          Digite seu nome:           +");
		nome = scanNome.next();
		System.out.println("+          Digite seu RA:             +");
		RA = scanRA.nextInt();
		System.out.println("+          Digite sua senha:          +");
		senha = scanSenha.nextInt();
		this.carregaBordas(2);
		Jogador jogador = new Jogador(RA, nome, senha);
		
		try {
			dao.insertJogador(jogador);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		this.destructScan(scanNome);
		this.destructScan(scanRA);
		this.destructScan(scanSenha);
	}
	
	public void exibeTelaInicial() {
		this.carregaBordas(1);
		System.out.println("+            JOGO DA FORCA            +");
		this.carregaBordas(2);
		limparTela();
	
	}
	
	public void exibeTelaMenu() {
		Scanner scanOpc = new Scanner(System.in);
		int opc = 0;
		boolean opcaoCorreta = true;
		
		this.carregaBordas(1);
		System.out.println("+       1 - Possui Cadastro;          +");
		System.out.println("+       2 - Novo jogador;             +");
		this.carregaBordas(2);	

		while(opcaoCorreta) {
			opc = scanOpc.nextInt();
			if(opc == 1 || opc == 2) {
				opcaoCorreta = false;
			}
		}
		if(opc == 1) {

		}else {
			limparTela();
			this.cadastraJogador();
			limparTela();
			this.exibeCadastrado();
		}
	}
	
	/*public void exibeTelaLogin() { -- Em andamento
		Scanner scanRA = new Scanner(System.in);
		Scanner scanSenha = new Scanner(System.in);
		int RA, senha;
		
		this.carregaBordas(1);
		System.out.println("+      RA:                      +");
		RA = scanRA.nextInt();
		System.out.println("+      Senha:                   +");
		senha = scanSenha.nextInt();
		
		this.validaContaJogador(RA, senha);
		this.carregaBordas(2);
	}
	
	public void validaContaJogador(int RA, int senha) {
		DAO dao = new DAO();
		
		
	}*/
	
	public void carregaBordas(int tipoBorda) {
		int i;
		if(tipoBorda == 1) {
			System.out.println("+=====================================+");
			for(i=0;i<3;i++) {
				System.out.println("+                                     +");
			}	
		}else {
			for(i=0;i<3;i++) {
				System.out.println("+                                     +");
			}	
			System.out.println("+=====================================+");
		}	
	}
	
	public void exibeCadastrado() {
		
		this.carregaBordas(1);
		System.out.println("+    Usuário cadastrado com sucesso   +");
		this.carregaBordas(2);
		limparTela();
	}
	
	public static void limparTela(){
		try {
			Thread.sleep(2000);
			if (System.getProperty("os.name").contains("Windows")) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			}
		    else {
				Runtime.getRuntime().exec("clear");
		   }				
	   }catch (InterruptedException | IOException ex) {
			ex.printStackTrace();
		}
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
	
	public char getLetra() {
		return this.letra;
	}
	
	private void destructScan(Scanner scan) {
		scan.close();
	}
	
}
