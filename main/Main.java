/**
 * Classe Main que Inicializa o Jogo e todos os outros componentes contidos nele.
 */

package com.centuri123.main;

//import com.centuri123.DAO.DAO;
//import com.centuri123.jogo.*;


public class Main {

	public static void main(String[] args){

		/**
		 * Resta ser feito:
		 * 
		 *  - Implementar a Tela do Jogo para a parte Cadastral do Jogador;
		 *  - Implementar o Menu do Jogo;
		 *  - Melhorar Tela do Jogo com _ _ _ _ _ _ da palavra sorteada;
		 *  - Implementar a criptografia dos dados cadastrais;
		 *  - Implementar a Classe que recebe do BD as pr� selecionadas que ser�o sorteadas.(Atualmente, s� funciona com 1 palavra 
		 *    pr�-setada na Classe Jogo);
		 *  - Implementar Ranking dos Jogadores utilizando a Tabela do BD.
		 */
		
		//Jogo jogo = new Jogo(); Para testar o Jogo basta retirar esse coment�rio e executar.
		
		
		/*
		 * Para testar o CRUD, basta retirar esse coment�rio e o do import e criar o BD de acordo com as tabelas necess�rias.
		 *
		 *
			//Jogador jogador = new Jogador(1500,"Carlos",5485787);
	        DAO dao = new DAO();
	        try {
				dao.insert(jogador);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		*/
		
		 //Palavra palavra = new Palavra(); Testes para pegar idSorteado do BD.
		 //palavra.sorteiaPalavra();


	}
}
