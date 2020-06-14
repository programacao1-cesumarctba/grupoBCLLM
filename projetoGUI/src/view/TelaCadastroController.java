package view;

import com.centuri123.DAO.DAO;
import com.centuri123.jogo.Jogador;

import application.Alerta;
import application.Main;
import application.Pagina;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class TelaCadastroController {
	
	@FXML private TextField inpNome; 
	@FXML private TextField inpRA;
	@FXML private TextField inpSenha;
	
	@FXML
	public void initialize() {}
	
	@FXML
	protected void btnCadastrar() {
		DAO dao = new DAO();
		
		/**
		 * Validação que bloqueia entrada de dados para senha ou RA com letras
		 */
		if(((!this.inpRA.getText().isBlank()) && this.inpRA.getText().matches("[0-9]*")) && ((!this.inpSenha.getText().isBlank()) && this.inpSenha.getText().matches("[0-9]*"))) {
			if(!this.inpNome.getText().isBlank()) {
				Jogador jogador = new Jogador(Integer.parseInt(this.inpRA.getText()), this.inpNome.getText(), Integer.parseInt(this.inpSenha.getText()));
				try {
					dao.insertJogador(jogador);
					dao.createRankingJogador(jogador.getRA());
					Alerta alerta = new Alerta(AlertType.INFORMATION, "SUCESSO", null, "CADASTRADO COM SUCESSO");
					alerta.showAndWait();
					Main.changePage(Pagina.TELA_MENU);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}	
			}else {
				Alerta alerta = new Alerta(AlertType.ERROR,"ERRO", null, "FAVOR PREENCHER O CAMPO NOME!");
				alerta.showAndWait();
			}
			
		}else {
			Alerta alerta = new Alerta(AlertType.ERROR, "ERRO", null, "RA E SENHA DEVEM CONTER SOMENTE NÚMEROS!");
			alerta.showAndWait();
		}
	}
	
}
