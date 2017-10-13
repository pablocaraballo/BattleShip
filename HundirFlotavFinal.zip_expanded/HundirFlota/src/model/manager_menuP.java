package model;

import java.io.IOException;
import java.util.logging.Handler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class manager_menuP {

	@FXML
	private Button startButton;
	@FXML
	private Button clientButton;
	@FXML 
	private TextField ipServer;
	@FXML 
	private TextField portServer;
	/*@FXML 
	private TextField name;*/
	
	@FXML
	protected void initialize(){


	}
		
	
	public void onclickStart() throws IOException{
		
		Parent root;
		Stage stage= (Stage) startButton.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("../views/serverMode.fxml"));
		Scene scene= new Scene(root);
		stage.setScene(scene);
		stage.show();
	
		new Thread( new Runnable() {
		    @Override
		    public void run() {
		    	
		    	try {
					serverMode server= new serverMode();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		}).start();
		
	}
	
	public void onclickClientStart() throws IOException{
		
		Parent root;
		Stage stage= (Stage) startButton.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("../views/ServerUI.fxml"));
		Scene scene= new Scene(root);
		stage.setScene(scene);
		stage.show();
		
		
		if (ipServer.getText() != null && portServer.getText() != null ){
			
			managerClientUI.setConfigurationServer(ipServer.getText(),Integer.parseInt(portServer.getText()));
			//serverMode.serverLabels(ipServer.getText(), name.getText());
		}
		
		
	}
}
