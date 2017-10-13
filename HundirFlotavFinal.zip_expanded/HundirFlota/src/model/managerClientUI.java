package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class managerClientUI {
	
	//Client Connection
	static Socket client;
	static String serverIP;
    static int serverPort;
        		
    static OutputStream outStream;
    static OutputStreamWriter out;
    static InputStreamReader in;
	/*
	 */
		
	@FXML
	private GridPane gridPlayer2;
	@FXML
	private Text lastJugada;
		
	final public Button[][] matrizBotones=new Button[10][10];
	
	@FXML
	protected void initialize() throws IOException{
		
		addButtonsGrids();
		allButtonsToListen();
				
	}
	
	static void setConfigurationServer(String host, int port) throws SocketException, UnknownHostException{
		
		 try {
			serverIP = host;
			serverPort = port;
			client= new Socket(serverIP, port);
			outStream=  client.getOutputStream();
			out= new OutputStreamWriter(outStream);
			in = new InputStreamReader(client.getInputStream());
		    System.out.println(" conectado a "+host);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addButtonsGrids(){
		
		for (int i=0; i<10; i++){
			
			for (int j=0; j<10; j++){

				matrizBotones[i][j]= new Button();
				matrizBotones[i][j].setId(i+","+j);
				matrizBotones[i][j].setPrefSize(50, 50);
				gridPlayer2.add(matrizBotones[i][j], i, j);
				
			}
			
		}
	}
	
	public void allButtonsToListen() throws IOException{
				
		for (int i=0; i<10; i++){
			
			for (int j=0; j<10; j++){
				
				final int fila= i;
				final int columna= j;
				matrizBotones[i][j].setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {

						System.out.println(matrizBotones[fila][columna].getId());
							
						new Thread( new Runnable() {
						    @Override
						    public void run() {
						    	
						    	try {
						    		
						    		System.out.println("Try envio");
						    		
						    		BufferedReader bf= new BufferedReader(in);
						    		out.write(matrizBotones[fila][columna].getId()+"\n");
									out.flush();
															    		
									String msgRecived= bf.readLine();
									
									System.out.println("MENSAJE RECIBIDO");
									
									if (msgRecived!=null){
										
										System.out.println("RECIBIDO " +msgRecived);
										
										if (msgRecived.equalsIgnoreCase("TOCADO")){
											
											Platform.runLater(new Runnable(){

												@Override
												public void run() {
													
													String image = getClass().getResource("../barco.png").toExternalForm();
													System.out.println(image);
													final ImageView imageView = new ImageView(
														      new Image(image)
														    );
													lastJugada.setText("Tocó agua..");
													matrizBotones[fila][columna].setGraphic(imageView);
													matrizBotones[fila][columna].setStyle("-fx-background-color:F50000;"
															+ " -fx-border: 5px solid;");
													matrizBotones[fila][columna].setDisable(true);
												}
												
											});
											

											
										}else if(msgRecived.equalsIgnoreCase("AGUA")){
											
											lastJugada.setText("¡¡Tocaste un barco!!");
											matrizBotones[fila][columna].setStyle("-fx-background-color:#1E90FF;"
													+ " -fx-border: 5px solid;");
											matrizBotones[fila][columna].setDisable(true);
										}
										
									}
						    										
						    		
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
						    }
						}).start();
					}
				});;
			}
		}
		
	}
		
}
