package view;

import application.Main;
import application.Pagina;
import javafx.fxml.FXML;

public class TelaLoginController {
	@FXML
	protected void initialize() {}
	@FXML
	protected void btnVoltar() {
		Main.changePage(Pagina.TELA_MENU);
	}
	
}