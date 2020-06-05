package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Main extends Application{

	private static Class classe;
	
	private static Stage stage;

	private static Scene sceneTelaInicial;
	private static Scene sceneTelaMenu;
	private static Scene sceneTelaCadastro;
	private static Scene sceneTelaLogin;
	private static Scene sceneTelaRanking;
	
	public Main() {
		classe = getClass();
	}
	
	@Override
	public void start(Stage stagePrimario) throws Exception {

		Parent fxTelaInicial  = FXMLLoader.load(classe.getResource("/view/telaInicial.fxml"));
		Parent fxTelaMenu     = FXMLLoader.load(classe.getResource("/view/TelaMenu.fxml"));
		Parent fxTelaCadastro = FXMLLoader.load(getClass().getResource("/view/telaCadastro.fxml"));
		Parent fxTelaLogin    = FXMLLoader.load(getClass().getResource("/view/telaLogin.fxml"));
		Parent fxTelaRanking    = FXMLLoader.load(getClass().getResource("/view/telaRanking.fxml"));
//		System.out.println(getClass().getResource("/view/telaCadastro.fxml"));
//		System.out.println(getClass().getResource("/view/telaInicial.fxml"));

		sceneTelaInicial      = new Scene(fxTelaInicial);
		sceneTelaMenu         = new Scene(fxTelaMenu);
		sceneTelaCadastro     = new Scene(fxTelaCadastro);
		sceneTelaLogin     	  = new Scene(fxTelaLogin);
		sceneTelaRanking      = new Scene(fxTelaRanking);
		
		stage = stagePrimario;
		stage.setTitle("JOGO DA FORCA");
		stage.setScene(sceneTelaMenu);
		stage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public static void changePage(Pagina pagina) {
		switch(pagina) {
		case TELA_INICIAL:
    		stage.setScene(sceneTelaInicial);
    		stage.show();
			break;
		case TELA_MENU:
			stage.setScene(sceneTelaMenu);
			stage.show();
			break;
		case TELA_CADASTRO:
			stage.setScene(sceneTelaCadastro);
			stage.show();
			break;
		case TELA_LOGIN:
			stage.setScene(sceneTelaLogin);
			stage.show();
			break;
		case TELA_RANKING:
			stage.setScene(sceneTelaRanking);
			stage.show();
			break;
		default:
			break;
		}
	}
}
