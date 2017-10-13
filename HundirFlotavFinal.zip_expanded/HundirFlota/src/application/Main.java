package application;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	 private Stage primaryStage;
	 private BorderPane rootLayout;

	    @Override
	    public void start(Stage primaryStage) {
	        this.primaryStage = primaryStage;
	        this.primaryStage.setTitle("HUNDIR LA FLOTA");

	        initRootLayout();

	    }
	    
	    /**
	     * Initializes the root layout.
	     */
	    public void initRootLayout() {
	        try {
	            // Load root layout from fxml file.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(Main.class.getResource("../views/MenuPrincipal.fxml"));
	            rootLayout = (BorderPane) loader.load();

	            // Show the scene containing the root layout.
	            Scene scene = new Scene(rootLayout);
	            primaryStage.setScene(scene);
	            primaryStage.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		launch(args);
	}
	
}
