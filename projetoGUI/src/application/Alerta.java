package application;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

public class Alerta extends Alert{
	
	public Alerta(AlertType tipo, String titulo, String cabecalho, String conteudo) {
		super(tipo);
		setTitle(titulo);
		setHeaderText(cabecalho);
		setContentText(conteudo);
	}
	
	public boolean validaJogarNov() {
		ButtonType btnSim  = new ButtonType("Sim", ButtonData.YES);
		ButtonType btnSair = new ButtonType("Não", ButtonData.NO); 
		
		this.getButtonTypes().setAll(btnSim,btnSair);
		
		Optional<ButtonType> confirm = this.showAndWait();
		if(confirm.get() == btnSim) {
			return true;
		}
		return false;
	}
}
