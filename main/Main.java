/**
 * Classe Main que Inicializa o Jogo e todos os outros componentes contidos nele.
 */

package com.centuri123.main;

import java.io.IOException;

//import java.io.IOException;
//import com.centuri123.DAO.DAO;
import com.centuri123.jogo.*;


public class Main {

	public static void main(String[] args) { //throws InterruptedException, IOException

		/**
		 * Resta ser feito:
		 * 
		 *  - Implementar a Tela do Jogo para a parte Cadastral do Jogador; - Em execução.
		 *  - Implementar o Menu do Jogo;
		 *  - Melhorar Tela do Jogo com _ _ _ _ _ _ da palavra sorteada - Feito para o caso Estático;
		 *  - Implementar a criptografia dos dados cadastrais;
		 *  - Implementar a Classe que recebe do BD as pré selecionadas que serão sorteadas.(Atualmente, só funciona com 1 palavra 
		 *    pré-setada na Classe Jogo);
		 *  - Implementar Ranking dos Jogadores utilizando a Tabela do BD.
		 */
		

			Jogo jogo = new Jogo();
	
	
		/*
		 * Para testar o CRUD, basta retirar esse comentário e o do import e criar o BD de acordo com as tabelas necessárias.
		 *
		 * PS: 
		 *   Na nova melhoria, somente é instanciado pela classe InputOutPut
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

