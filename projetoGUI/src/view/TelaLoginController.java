package view;

import com.centuri123.DAO.DAO;

import com.centuri123.jogo.Jogador;

import application.Alerta;
import application.Main;
import application.Pagina;
import application.OnChangePage;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;


public class TelaLoginController{
	
	private Jogador jogador;
	
	@FXML private TextField raLogin;
	@FXML private TextField senhaLogin;
	
	@FXML protected void initialize() {
		Main.addListener(new OnChangePage() {
			@Override
			public void onPageChanged(Pagina pagina, Object dados) {
				if(pagina.equals(Pagina.TELA_LOGIN)) {
					raLogin.requestFocus();
				}
			}
		});
	}
	
	@FXML
	protected void btnLogin() {
		/**
		 * Validação que bloqueia entrada de dados para senha ou RA com letras
		 */
		if(((!this.raLogin.getText().isBlank()) && this.raLogin.getText().matches("[0-9]*")) && ((!this.senhaLogin.getText().isBlank()) && this.senhaLogin.getText().matches("[0-9]*"))) {
			DAO dao = new DAO();
			try {
				this.jogador = dao.selectJogador(Integer.parseInt(this.raLogin.getText()));//Integer.parseInt(this.raLogin.getText()), Integer.parseInt(this.senhaLogin.getText())
				if(this.jogador != null && this.jogador.getSenha() == Integer.parseInt(this.senhaLogin.getText())) {
					this.raLogin.setText("");
					this.senhaLogin.setText("");
					Main.changePage(Pagina.TELA_JOGO, jogador);
				}

			} catch (NumberFormatException | ClassNotFoundException e) {
				e.printStackTrace();
			} 

		}else {
			Alerta alerta = new Alerta(AlertType.ERROR, "ERRO", null, "RA E SENHA DEVEM CONTER SOMENTE NÚMEROS!");
			alerta.showAndWait();
		}
	}
	
	@FXML
	protected void btnVoltar() {
		Main.changePage(Pagina.TELA_MENU);
	}
	
	public Jogador getJogador() {
		return this.jogador;
	}
	
}
