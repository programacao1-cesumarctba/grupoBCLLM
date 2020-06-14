package application;

import java.util.ArrayList;

import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	private static Class classe;
	
	private static Stage stage;

	private static Scene sceneTelaMenu;
	private static Scene sceneTelaCadastro;
	private static Scene sceneTelaLogin;
	private static Scene sceneTelaRanking;
	private static Scene sceneTelaJogo;
	
	private static List<OnChangePage> listeners = new ArrayList<>();
	
	public Main() {
		classe = getClass();
	}
	
	@Override
	public void start(Stage stagePrimario) throws Exception {

		Parent fxTelaMenu     = FXMLLoader.load(classe.getResource("/view/TelaMenu.fxml"));
		Parent fxTelaCadastro = FXMLLoader.load(classe.getResource("/view/telaCadastro.fxml"));
		Parent fxTelaLogin    = FXMLLoader.load(classe.getResource("/view/telaLogin.fxml"));
		Parent fxTelaRanking  = FXMLLoader.load(classe.getResource("/view/telaRanking.fxml"));
		Parent fxTelaJogo     = FXMLLoader.load(classe.getResource("/view/telaJogo.fxml"));

		sceneTelaMenu         = new Scene(fxTelaMenu);
		sceneTelaCadastro     = new Scene(fxTelaCadastro);
		sceneTelaLogin     	  = new Scene(fxTelaLogin);
		sceneTelaRanking      = new Scene(fxTelaRanking);
		sceneTelaJogo         = new Scene(fxTelaJogo);
		
		stage = stagePrimario;
		stage.setTitle("JOGO DA FORCA");
		stage.setScene(sceneTelaMenu);
		stage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public static void changePage(Pagina pagina) {
		changePage(pagina, null);
	}
	

	
	public static void changePage(Pagina pagina, Object dados) {
		switch(pagina) {
		case TELA_MENU:
			stage.setScene(sceneTelaMenu);
			notifyAllListeners(Pagina.TELA_MENU, dados);
			break;
		case TELA_CADASTRO:
			stage.setScene(sceneTelaCadastro);
			notifyAllListeners(Pagina.TELA_CADASTRO, dados);
			break;
		case TELA_LOGIN:
			stage.setScene(sceneTelaLogin);
			notifyAllListeners(Pagina.TELA_LOGIN, dados);
			break;
		case TELA_RANKING:
			stage.setScene(sceneTelaRanking);
			notifyAllListeners(Pagina.TELA_RANKING, dados);
			break;
		case TELA_JOGO:
			stage.setScene(sceneTelaJogo);
			notifyAllListeners(Pagina.TELA_JOGO, dados);
			break;
		default:
			break;
		}
	}
	
	public static void addListener(OnChangePage novoListener) {
		listeners.add(novoListener);
	}
	
	private static void notifyAllListeners(Pagina pagina, Object dados) {
		for(OnChangePage listener : listeners) {
			listener.onPageChanged(pagina, dados);
		}
	}
}
