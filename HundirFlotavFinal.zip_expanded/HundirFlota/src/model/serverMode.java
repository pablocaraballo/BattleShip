package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class serverMode {

	final int PUERTO= 5555;
	
	//Tablero de juego
	tableroJuego tablero;
	
	//Connection
	ServerSocket socketServer;
	Socket clientSocket;
    int clientPort;
	InputStreamReader in;
	OutputStream outStream;
	OutputStreamWriter out;
	
	/*//Grid del server
		@FXML
		private GridPane gridServer;
		
		//Matriz de botones del grid
		final public Button[][] matrizBotonesServer=new Button[10][10];
		
		@FXML
		private  Label playerName;
		@FXML
		private   Label playerIP;
		@FXML
		private Label numBoats;
		@FXML
		private Label putYourBoats;
		@FXML
		private Label boatLeft;
		@FXML
		private Label playtime;*/

   /*@FXML
    protected void initialize(){
    	
    	playtime.setVisible(false);
		numBoats.setText(String.valueOf(tablero.barcos));
		addButtonsGridServer();
		allButtonsToListen();
		//serverLabels();
    	
    }*/
		
		
	public serverMode() throws IOException{
		
		tablero= new tableroJuego();
		init(PUERTO);
		System.out.println("Servidor iniciado");
		
	}
	
	//Muestra el nombre y la ip del jugador en los labels de la UI
	 /*public void serverLabels(){
		 
		playerName.setText("hola");
		playerIP.setText("Adios");
		
		
	}*/
	
	public void init(int port) throws IOException {
		
			try{
			 	socketServer = new ServerSocket(port);
				System.out.println("ESTOY ESCUCHANDO");
				clientSocket= socketServer.accept();
				communicate();
										
			} catch (Exception e) {
				e.printStackTrace();
			}
		 
	}
	
	public void communicate() throws IOException{
		 
		boolean gameOver=false;
	
		new Thread( new Runnable() {
		    @Override
		    public void run() {
		    	
		    	while (!gameOver){
		    		System.out.println("trying..."); 
		    				    		
		            try {
		    		
			    		in = new InputStreamReader(clientSocket.getInputStream());
			    		BufferedReader bf = new BufferedReader(in);
			    		
			    		String linia = bf.readLine();
			    		System.out.println("REBUT DEL CLIENT " +linia);
			    		if (linia!= null){
			    			
					        int filaClient, columnaClient;
					        
					        StringTokenizer stok= new StringTokenizer(linia, ",");
					        
					        filaClient=Integer.parseInt(stok.nextToken()); 
					        columnaClient= Integer.parseInt(stok.nextToken());
					        
					        System.out.println(filaClient + "-" + columnaClient);
					        
					        int resultat= tablero.comprobarTocado(filaClient, columnaClient);
					        System.out.println("RESULTADO: "+resultat);
					        
					        outStream = clientSocket.getOutputStream();
				        	out = new OutputStreamWriter(outStream);
				        	String sendData;
					        
					        switch (resultat){
					        
					        	//AGUA
					        case 0:
					        		
					        	sendData = new String("AGUA\n");
					        	out.write(sendData);
					        	out.flush();
					        	break;
					        	
					        	//TOCADO
					        case 1: 
					        	
					        	sendData = new String("TOCADO\n");
					        	out.write(sendData);
					        	out.flush();
					        	break;				        	
					       				        
					        }
			    			
			    		}
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		           
		    	}
		    }
		}).start();
		
	 }
	
	//Se rellena la grid del server de botones
	/*public void addButtonsGridServer(){
		
		for (int i=0; i<10; i++){
			
			for (int j=0; j<10; j++){

				matrizBotonesServer[i][j]= new Button();
				matrizBotonesServer[i][j].setId(i+","+j);
				matrizBotonesServer[i][j].setPrefSize(50, 50);
				gridServer.add(matrizBotonesServer[i][j], i, j);		
			}
		}
	}
	
	public void allButtonsToListen(){
		
		for (int i=0; i<10; i++){
			
			for (int j=0; j<10; j++){
				
				final int fila= i;
				final int columna= j;
				matrizBotonesServer[i][j].setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						
						tablero.grid[fila][columna]=1;
						matrizBotonesServer[fila][columna].setStyle("-fx-background-color:#B32821;"
								+ " -fx-border: 5px solid;");
						matrizBotonesServer[fila][columna].setDisable(true);
						System.out.println(matrizBotonesServer[fila][columna].getId());
						tablero.barcos--;
						numBoats.setText(String.valueOf(tablero.barcos));
						
						if(tablero.barcos==0){
							playtime.setVisible(true);
							boatLeft.setVisible(false);
							putYourBoats.setVisible(false);
							
						}
					}
				});;
			}
		}
		
	}*/
	
		
}
