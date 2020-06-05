package view;

import application.Main;
import application.Pagina;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;



public class TelaMenuController{

	@FXML
	protected void btnCadastro(ActionEvent event) {
		Main.changePage(Pagina.TELA_CADASTRO);
	}

	@FXML
	protected void btnLogin(ActionEvent event) {
		Main.changePage(Pagina.TELA_LOGIN);
	}
	
	@FXML
	protected void btnRanking(ActionEvent event) {
		Main.changePage(Pagina.TELA_RANKING);
	}
	//public static void inicializa() {
	//	Main.changePage(Pagina.TELA_MENU);
	//}

}
