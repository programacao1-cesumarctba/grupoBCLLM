package view;

import application.Main;

import application.Pagina;
import application.OnChangePage;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;


public class TelaRankingController{
	
	@FXML protected ScrollPane scrollPane;
	@FXML public Text listaJogadores;
	
	@FXML protected void initialize() {
		Main.addListener(new OnChangePage() {
			@Override
			public void onPageChanged(Pagina pagina, Object dados) {
				if(pagina.equals(Pagina.TELA_RANKING)) {
					if(dados != null) {
						listaJogadores.setText(dados.toString());
					}else {
						listaJogadores.setText("---------- Sem Jogadores no Ranking -----------");
					}
				}
			}
		});
	}
	
	@FXML
	protected void btnVoltar() {
		Main.changePage(Pagina.TELA_MENU);
	}

}
