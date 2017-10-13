package model;

public class tableroJuego {

	static public int[][] grid;
	private int FILAS = 10;
	private int COLUMNAS = 10;
	//static public int barcos=10;
	
	/*public int getBarcos() {
		return barcos;
	}

	public void setBarcos(int barcos) {
		this.barcos = barcos;
	}*/

	public tableroJuego(){
		
		grid= new int[FILAS][COLUMNAS];
		//incializarBarcos(grid);		
		
	}
	
	/*public int generarNumAleatorio(){
		
		return (int) ((Math.random()*2));
	}*/
	
	/*public void incializarBarcos(int[][] tablero){
		
		for (int i=0; i<FILAS;i++){
			
			for (int j=0; j<COLUMNAS; j++){
				
				grid[i][j]=0;
				System.out.print(grid[i][j]);
				
			}
			
			System.out.println("");
			
		}
		
	}*/
	
	public int comprobarTocado(int fila, int columna){
		
		if (grid[fila][columna] == 1){
			
			//TOCADO
			return 1;
			
				//AGUA
		}else return 0;
		
	}
	
	
}
