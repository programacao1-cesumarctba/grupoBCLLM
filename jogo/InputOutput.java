/**
 * Classe que recebe todo tipo de input do Jogador (Dados cadastrais e valores para o jogo)
 */

package com.centuri123.jogo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
		RA = this.validaInteiro();
		if(RA == 0) {
			this.exibeTelaErro(3);
		}
		System.out.println("+          Digite sua senha:          +");
		senha = this.validaInteiro();
		if(senha == 0) {
			this.exibeTelaErro(3);
		}
		this.carregaBordas(2);
		Jogador jogador = new Jogador(RA, nome, senha);
		
		try {
			dao.insertJogador(jogador);
			dao.createRankingJogador(RA);
			
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
	
	public void exibeTelaInicial() {
		this.carregaBordas(1);
		System.out.println("+            JOGO DA FORCA            +");
		this.carregaBordas(2);
		limparTela(2000);
	
	}
	
	public void exibeTelaMenu() {
		Scanner scanOpc = new Scanner(System.in);
		int opc = 0;
		boolean opcaoCorreta = true;
		
		this.carregaBordas(1);
		System.out.println("+       1 - Possui Cadastro;          +");
		System.out.println("+       2 - Novo jogador;             +");
		System.out.println("+       3 - Ranking Jogadores;        +");
		this.carregaBordas(2);	

		while(opcaoCorreta) {
			opc = scanOpc.nextInt();
			if(opc == 1 || opc == 2 || opc == 3 || opc == 4) {
				opcaoCorreta = false;
			}
		}
		
		switch(opc) {
			case 1:
				limparTela(2000);
				this.exibeTelaLogin();
				break;
			case 2: 
				limparTela(2000);
				this.cadastraJogador();
				limparTela(2000);
				this.exibeCadastrado();
				break;
			case 3:
				limparTela(2000);
				this.exibeTelaRanking();
				break;
			case 4:
				Palavra palavra = new Palavra();
				palavra.sorteiaPalavra();
				break;
		}
	}
	
	public void exibeTelaRanking() {
		List<Object> listaDados = new ArrayList<Object>();
		
		int count = 0;
		
		
		DAO dao = new DAO();
		this.carregaBordas(3);
		try {
			listaDados = dao.selectJogador();
			if(listaDados != null && !listaDados.isEmpty()) {
				System.out.println("+ Nome                 Vitórias                Derrótas               Pontuação   +");
				for(Object x : listaDados) {
					if(count != 4) {
						System.out.printf("  %-22.20s", x.toString());//
						//System.out.print("    " + x + "\t\t");
						count++;
					}else {
						System.out.printf("\n  %-22.20s", x.toString());
						count = 0;
					}
				}
				System.out.printf("\n");
				this.carregaBordas(4);
				System.out.printf("                              1 - Voltar Menu Principal;                        \n");
				Scanner scanVoltar = new Scanner(System.in);
				char opcVoltar;
				opcVoltar = scanVoltar.next().charAt(0);
				while(true){
					if(opcVoltar != '1') {
						opcVoltar = scanVoltar.next().charAt(0);
					}else {
						break;
					}
				}
				limparTela(200);
				this.exibeTelaMenu();
			}else {
				System.out.println("+---------------------------------------------------------------------------------+");
				this.carregaBordas(4);
				System.out.printf("                              1 - Voltar Menu Principal;                        \n");
				Scanner scanVoltar = new Scanner(System.in);
				char opcVoltar;
				opcVoltar = scanVoltar.next().charAt(0);
				while(true){
					if(opcVoltar != '1') {
						opcVoltar = scanVoltar.next().charAt(0);
					}else {
						break;
					}
				}
				limparTela(200);
				this.exibeTelaMenu();
			}

		}catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
	
	public void exibeTelaLogin() {
		Scanner scanRA = new Scanner(System.in);
		Scanner scanSenha = new Scanner(System.in);
		int RA, senha;
		Jogador jogador = new Jogador();
		DAO dao = new DAO();
		
		this.carregaBordas(1);
		System.out.println("+    Digite seu RA:                   +");
		RA = this.validaInteiro();
		if(RA == 0) {
			this.exibeTelaErro(3);
		}

		try {
			jogador = dao.selectJogador(RA);
			if(jogador != null ){ //&& !jogador.isEmpty(){
				System.out.println("+    Digite sua Senha:                +");
				senha = this.validaInteiro();
				if(senha == 0) {
					this.exibeTelaErro(3);
				}
				if(jogador.getSenha() == senha) {
					Jogo continuacaoJogo = new Jogo(jogador);
					
				}else {
					limparTela(2000);
					this.exibeTelaErro(2);
					limparTela(2000);
					this.exibeTelaLogin();
				}

			}else {
				limparTela(2000);
				this.exibeTelaErro(1);
				limparTela(2000);
				this.exibeTelaLogin();
			}
			
		} catch (ClassNotFoundException | InterruptedException | IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void exibeTelaErro(int opc) {	
		switch(opc) {
			case 1:
				this.carregaBordas(1);
				System.out.println("+             RA inválido             +");
				this.carregaBordas(2);
				break;
				
			case 2:
				this.carregaBordas(1);
				System.out.println("+            Senha inválida           +");
				this.carregaBordas(2);
				break;
			default:
				limparTela(500);
				this.carregaBordas(1);
				System.out.println("+  ERRO! Somente números são aceitos  +");
				this.carregaBordas(2);
				limparTela(500);
				System.exit(0);
				break;
		}
	}

	public void carregaBordas(int tipoBorda) {
		int i;
		switch(tipoBorda){
			case 1:
				System.out.println("+=====================================+");
				for(i=0;i<3;i++) {
					System.out.println("+                                     +");
				}	
				break;
			case 2:
				for(i=0;i<3;i++) {
					System.out.println("+                                     +");
				}	
				System.out.println("+=====================================+");		
				break;
			case 3:
				System.out.println("+=================================================================================+");
				System.out.println("+                                 Ranking Jogadores                               +");
				System.out.println("+=================================================================================+");
				break;
			case 4:
				System.out.println("+=================================================================================+");
				break;
			default:
				break;
		}
	}
	
	public void exibeCadastrado() {
		this.carregaBordas(1);
		System.out.println("+    Usuário cadastrado com sucesso   +");
		this.carregaBordas(2);
		limparTela(2000);
	}
	
	public static void limparTela(int tempo){
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
	
	public void limpaLetra() {
		this.letra = ' ';
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
	
	public char verificaJogarNovamente() {
		System.out.println("Deseja jogar novamente?: 1 para Reiniciar");
		Scanner scanJogarNov = new Scanner(System.in);
		char jogarNov = scanJogarNov.next().charAt(0);
		
		return jogarNov;
	}
	
	public void notificaSaida() {
		System.out.println("Obrigado por jogar !!");
		System.exit(0);
	}
	
	public int validaInteiro() {
		Scanner scanInt = new Scanner(System.in);
		int valor;
		if(scanInt.hasNextInt()) {
			valor = scanInt.nextInt();
			return valor;
		}else {
			return 0;
		}
		
	}
	
	public boolean validaLetraRepetida(String letrasUtilizadas) {
		if(letrasUtilizadas.indexOf(Character.toUpperCase(this.getLetra())) != -1) {
			return true;
		}
		return false;
	}
	
	public char getLetra() {
		return this.letra;
	}
	
	/**
	 * Foi pesquisado que ao dar um close no obj da classe Scanner, é fechado o System.in que por sinal é a Entrada padrão para o console
	 * e assim é lançada uma Exception.
	 */
	//private void destructScan(Scanner scan) {   
	//	scan.close();
	//}
	
}
