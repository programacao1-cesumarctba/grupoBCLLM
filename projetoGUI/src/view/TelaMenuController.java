package view;

import java.util.ArrayList;

import java.util.List;

import com.centuri123.DAO.DAO;

import application.Main;
import application.Pagina;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class TelaMenuController{
	
	@FXML private TextField lista;
	
	@FXML protected void initialize() {}
	
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
		Main.changePage(Pagina.TELA_RANKING, iniDadosRanking());
	}

	public String iniDadosRanking() {
		List<Object> listaDados = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		DAO dao = new DAO();
		int count = 1;
		
		try {
			listaDados = dao.selectJogador();
			if(listaDados.isEmpty() || listaDados == null) {
				return null;
			}else {
				for(Object jogador : listaDados) {
					if(count == 1) {
						if(jogador.toString().length() < 8) {
							sb.append(String.format("%-22s", jogador.toString().toLowerCase()));
						}else {
							sb.append(String.format("%-20.8s", jogador.toString().toLowerCase()));
						}
					}else if(count == 2) {
						if(jogador.toString().length() < 3) {
							sb.append(String.format("%4.4s", jogador));
						}else {

							sb.append(String.format("%-15s", jogador));
						}
					}else if(count == 3) {
						if(jogador.toString().length() < 3) {
							sb.append(String.format("%18.4s", jogador));
						}else {
							if(jogador.toString().length() == 2) {
								sb.append(String.format("%-18s", jogador));
							}else{
								sb.append(String.format("%-13s", jogador));
							}
						}
					}else if(count == 4) {
						if(jogador.toString().length() == 1) {
							sb.append(String.format("%15.4s\n", jogador));
						}else if(jogador.toString().length() == 2){
							sb.append(String.format("%5s\n", jogador));
						}else {
							sb.append(String.format("%13s\n", jogador));
						}
						count = 0;
					}
					count++;
				}
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
