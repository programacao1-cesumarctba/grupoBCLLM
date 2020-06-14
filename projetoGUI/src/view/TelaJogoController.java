package view;

import com.centuri123.jogo.Boneco;
import com.centuri123.jogo.InputOutput;
import com.centuri123.jogo.Jogador;
import com.centuri123.jogo.Jogo;
import com.centuri123.jogo.Palavra;
import com.centuri123.jogo.Placar;

import application.Alerta;
import application.Main;
import application.OnChangePage;
import application.Pagina;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class TelaJogoController {

	protected Jogo 	      jogo;
	protected InputOutput inOut;
	protected Jogador     jogador;
	protected Placar      placar;
	protected Boneco      boneco;
	protected Palavra     palavra;
	
	protected StringBuilder sbPalavra;
	protected StringBuilder palavraEscondida;
	
	@FXML protected Label     acertos;
	@FXML protected Label     erros;
	@FXML protected Label     pontuacao;
	
	@FXML protected Text      dicaPalavraSorteada;
	@FXML protected Text      palavraSorteada;
	
	@FXML protected TextField letraEscolhida;
	
	@FXML protected ImageView cabecaBoneco;
	@FXML protected ImageView troncoBoneco;
	@FXML protected ImageView bracoEsqBoneco;
	@FXML protected ImageView bracoDirBoneco;
	@FXML protected ImageView pernaEsqBoneco;
	@FXML protected ImageView pernaDirBoneco;
	
	@FXML 
	protected void initialize() {
		Main.addListener(new OnChangePage() {

			@Override
			public void onPageChanged(Pagina pagina, Object dados) {
				if(pagina.equals(Pagina.TELA_JOGO) && jogador == null) {
					jogo  = new Jogo(true);
					inOut = new InputOutput();
					placar = new Placar();
					palavra = new Palavra();
					boneco = new Boneco();
					sbPalavra = new StringBuilder();
					palavraEscondida = new StringBuilder();
					jogador = (Jogador) dados;
					
					palavra.sorteiaPalavra();
					sbPalavra.append(palavra.getPalavra());
					placar.inicializaPontuacao(jogador.getRA());

					
					for(int i = 0;i<sbPalavra.length();i++) {
						palavraEscondida.append(" _");
					}
					
					/**
					 * Valores do Placar 
					 */
					acertos.setText(String.format("%s", placar.getQuantAcerto()));
					erros.setText(String.format("%s", placar.getQuantErro()));
					pontuacao.setText(String.format("%s", placar.getPontuacao()));
					letraEscolhida.requestFocus();
					//------------------------------
					
					/**
					 * Inicializa view do boneco escondido
					 */
					cabecaBoneco.setVisible(false);
					troncoBoneco.setVisible(false);
					bracoEsqBoneco.setVisible(false);
					bracoDirBoneco.setVisible(false);
					pernaEsqBoneco.setVisible(false);
					pernaDirBoneco.setVisible(false);
					//-------------------------------
					
					dicaPalavraSorteada.setText(palavra.getDica());
					palavraSorteada.setText(palavraEscondida.toString());

				}else if(pagina.equals(Pagina.TELA_JOGO)){
					if(!(letraEscolhida.getText().isBlank())) {
						for(int i = 0; i < sbPalavra.length(); i++) {
							if(placar.getLetrasUtilizadas().isBlank()) {
								if(sbPalavra.charAt(i) == letraEscolhida.getText().toUpperCase().charAt(0)) {
									palavraEscondida.setCharAt((i*2)+1, sbPalavra.charAt(i));
									placar.addQuantAcerto();
									placar.addPontuacao();
									jogo.setFlagErro(true);
								}else {
									if((i == (sbPalavra.length()-1)) && !(jogo.getFlagErro())){
										if(!inOut.validaLetraRepetida(placar.getLetrasUtilizadas())) {
											placar.addQuantErro();
											placar.remPontuacao();
											boneco.removeVida();
											if(placar.getQuantErro() == 1) {
												cabecaBoneco.setVisible(true);
											}else if(placar.getQuantErro() == 2) {
												troncoBoneco.setVisible(true);
											}else if(placar.getQuantErro() == 3) {
												bracoEsqBoneco.setVisible(true);
											}else if(placar.getQuantErro() == 4) {
												bracoDirBoneco.setVisible(true);
											}else if(placar.getQuantErro() == 5) {
												pernaEsqBoneco.setVisible(true);
											}else if(placar.getQuantErro() == 6){
												pernaDirBoneco.setVisible(true);
											}
										}
									}
								}
							}else {
								if(!inOut.validaLetraRepetida(placar.getLetrasUtilizadas(),letraEscolhida.getText().charAt(0))) {
									if(sbPalavra.charAt(i) == letraEscolhida.getText().toUpperCase().charAt(0)) {
										palavraEscondida.setCharAt((i*2)+1, sbPalavra.charAt(i));
										placar.addQuantAcerto();
										placar.addPontuacao();
										jogo.setFlagErro(true);
									}else {
										if((i == (sbPalavra.length()-1)) && !(jogo.getFlagErro())){
											if(!inOut.validaLetraRepetida(placar.getLetrasUtilizadas())) {
												placar.addQuantErro();
												placar.remPontuacao();
												boneco.removeVida();
												if(placar.getQuantErro() == 1) {
													cabecaBoneco.setVisible(true);
												}else if(placar.getQuantErro() == 2) {
													troncoBoneco.setVisible(true);
												}else if(placar.getQuantErro() == 3) {
													bracoEsqBoneco.setVisible(true);
												}else if(placar.getQuantErro() == 4) {
													bracoDirBoneco.setVisible(true);
												}else if(placar.getQuantErro() == 5) {
													pernaEsqBoneco.setVisible(true);
												}else if(placar.getQuantErro() == 6){
													pernaDirBoneco.setVisible(true);
												}
											}
										}
										
									}
								}
							}
						}
						jogo.setFlagErro(false);
						

						
						if(!inOut.validaLetraRepetida(placar.getLetrasUtilizadas(),letraEscolhida.getText().charAt(0))) {
							placar.setLetrasUtilizadas(letraEscolhida.getText().charAt(0));
						}
					}
					
					letraEscolhida.setText("");
					letraEscolhida.requestFocus();
//					System.out.println("letra utilizada: " + placar.getLetrasUtilizadas());
//					System.out.println(palavraEscondida);
//					System.out.println(placar.getPontuacao());
//					System.out.println(placar.getQuantAcerto());
//					System.out.println(placar.getQuantErro());
//					System.out.println(boneco.getVida());

					acertos.setText(String.format("%s", placar.getQuantAcerto()));
					erros.setText(String.format("%s", placar.getQuantErro()));
					pontuacao.setText(String.format("%s", placar.getPontuacao()));
					palavraSorteada.setText(palavraEscondida.toString());
					
					if(placar.getQuantAcerto() == sbPalavra.length()) {
						Alerta alerta = new Alerta(AlertType.INFORMATION, "VITÓRIA", null, "Parabéns, Você Venceu!!");
						alerta.showAndWait();

						placar.addVit();
						placar.salvaPontuacao(jogador);
						
						Alerta alertaJogar = new Alerta(AlertType.CONFIRMATION, "JOGAR NOVAMENTE", null, "Deseja jogar novamente?");
						
						if(!alertaJogar.validaJogarNov()) {
							jogador = null;
							Main.changePage(Pagina.TELA_MENU);
						}else {
							Jogador jogadorAnterior = jogador;
							jogador = null;
							Main.changePage(Pagina.TELA_JOGO,jogadorAnterior);
						}
						
					}else if(boneco.getVida() == 0){
						Alerta alerta = new Alerta(AlertType.INFORMATION, "DERROTA", null, "Não foi dessa vez...");
						alerta.showAndWait();
						
						placar.addDer();
						placar.salvaPontuacao(jogador);
						
						Alerta alertaJogar = new Alerta(AlertType.CONFIRMATION, "JOGAR NOVAMENTE", "Deseja jogar novamente?", null);
						
						if(!alertaJogar.validaJogarNov()) {
							jogador = null;
							Main.changePage(Pagina.TELA_MENU);
						}else {
							Jogador jogadorAnterior = jogador;
							jogador = null;
							Main.changePage(Pagina.TELA_JOGO,jogadorAnterior);
						}
					}
				}
				
			}
		});
	}
	
	@FXML
	protected void atualizaJogo() {
		if(!(this.letraEscolhida.getText().isBlank())) {
			Main.changePage(Pagina.TELA_JOGO);	
		}else {
			this.letraEscolhida.requestFocus();
		}
	}
}
