package logica;

import java.io.*;
import java.util.Scanner;
import java.util.Random;


public class Grid {
	protected Celda [][] celdas; //arreglo para el juego
	protected Celda [][] solucion; // almacena la solucion del archivo utilizado
	protected final static int dim = 9; //dimension de los arreglos, dado que se asume que es una grilla cuadrada, se usa 1 solo entero
	protected String path = "src/solucion/solucion.txt";//localizacion del archivo a ser utilizado
	protected boolean [][] colisiones;//arreglo secundario para correcta actualizacion sobre errores que no respetan las reglas del juego
	protected boolean estado;// variable usada para conocer si la solucion en el archivo es valida, o si ocurrio algun otro problema
	
	public Grid() {
		verificador v = new verificador();
		if (v.verificar(path)) { //El verificador, valida el formato del archivo
			int f = 0;
			int c = 0;
			estado = true;
			solucion = new Celda[dim][dim];
			int n = 0;
			Scanner filereader = null;
			try {
				filereader = new Scanner (new File(path));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (f = 0; f<9;f++) {
				for (c = 0; c<9; c++){
					n = filereader.nextInt();
					solucion[f][c] = new Celda(n);
				}
			}
		}			
		if (verificar(solucion)) 
			inicializar();
		else 
			estado = false;
	}
	
	
	/**
	 * toma la solucion ya guardada, y decide en base a un numero al azar, que posiciones se
	 * revelan para preparar el juego
	 */ 
	public void inicializar () {
		iniciarColisiones();
		Random r = new Random();
		celdas = new Celda[dim][dim];
		for (int f = 0; f<dim;f++) {
			for (int c = 0; c<dim; c++){
				if (r.nextInt(99)>36) {
					celdas [f][c] = new Celda(0);
					celdas [f][c].setVariable(true);
				}
				else {
					celdas [f][c] = new Celda(solucion[f][c].getContenido());
					celdas [f][c].setVariable(false);
				}
			}
		}
	}
	
	private void iniciarColisiones() {
		// TODO Auto-generated method stub
		colisiones = new boolean[dim][dim];
		for (int f = 0; f<dim; f++) 
			for (int c = 0; c<dim; c++)
				colisiones[f][c] = false;	
	}

	/**
	 * metodo que reinicia el estado del juego a como se encontraba en el comienzo
	 */
	public void reiniciar() {
		for (int f = 0; f<9;f++) {
			for (int c = 0; c<9; c++){
				if (celdas [f][c].getVariable()) {
					celdas [f][c].setContenido(0);
				}
			}
		}
	}
	
	public void accionar(Celda c) {
		if (c.getVariable())
			if(c.getContenido()<9)
				c.setContenido(c.getContenido() +1);
			else
				c.setContenido(0);
		
	}
	
	/**
	 * metodo para evaluar si el estado actual del juego, es o no una solucion valdia
	 * tambien es el encargado de marcar cuales posiciones colisionan, estan infringiendo
	 * las reglas
	 * @param juego arreglo de celdas que retiene el estado del juego
	 * @return TRUE en caso de que la solucion sea valida, FALSE en caso contrario
	 */
	public boolean verificar(Celda[][] juego) {
		boolean cumple = true;
		unmark(juego);
		for (int f = 0; f<9;f++) {
			for (int c = 0; c<9; c++){
				Celda celda = juego[f][c];
				int cont = celda.getContenido();
				if (celda.getContenido()!=0) {
					if ( !( verificarCol(juego, celda, c) & verificarFil(juego, celda, f) & verificarPan(juego, cont, f, c, celda) ))
						cumple = false;
				}else
					cumple = false;
			}
		}
		marked(juego);
		return cumple;
	}
	
	private boolean verificarCol(Celda[][] juego, Celda celda, int col) {
		int cont = 0;
		for (int fil = 0; fil< 9; fil++)
			if (juego[fil][col].getContenido()==celda.getContenido() && juego[fil][col]!=celda) {
				cont ++;
				juego[fil][col].setMark(true);
			}
		boolean cumple = cont == 0;
		return cumple;
	}
	


	private boolean verificarFil(Celda[][] juego, Celda celda, int fil) {
		int cont = 0;
		for (int col = 0; col< 9; col++)
			if (juego[fil][col].getContenido()==celda.getContenido() && juego[fil][col]!=celda) {
				cont ++;
				juego[fil][col].setMark(true);
			}
		boolean cumple = cont == 0;
		return cumple;
	}

	private boolean verificarPan(Celda[][] juego, int n, int fil, int col, Celda celd) {
		if (fil < 3) {
			if (col < 3)
				return verificarPanAux(juego, n, 0, 3, 0, 3, celd);
			else
				if (col >=3 && col < 6)
					return verificarPanAux(juego, n, 0, 3, 3, 6, celd);
				else
					return verificarPanAux(juego, n, 0, 3, 6, 9, celd);
		}
		else {
			if (fil >= 3 && fil < 6) {
				if (col < 3)
					return verificarPanAux(juego, n, 3, 6, 0, 3, celd);
				else
					if (col >=3 && col < 6)
						return verificarPanAux(juego, n, 3, 6, 3, 6, celd);
					else
						return verificarPanAux(juego, n, 3, 6, 6, 9, celd);
			}else {
				if (col < 3)
					return verificarPanAux(juego, n, 6, 9, 0, 3, celd);
				else
					if (col >=3 && col < 6)
						return verificarPanAux(juego, n, 6, 9, 3, 6, celd);
					else
						return verificarPanAux(juego, n, 6, 9, 6, 9, celd);
			}
		}
	}
	
	private boolean verificarPanAux(Celda[][] juego, int n, int filI, int filF, int colI, int colF, Celda celd) {
		int cont = 0;
		for (int fil = filI; fil<filF; fil++) {
			for (int col = colI; col<colF; col++){
				if (juego[fil][col].getContenido()==n && juego[fil][col]!=celd) {
					cont ++;
					juego[fil][col].setMark(true);
				}
			}
		}
		boolean cumple = cont == 0;
		return cumple;
	}

	public Celda[][] getCeldas(){
		return celdas;
	}
	
	public int getDimension() {
		// TODO Auto-generated method stub
		return dim;
	}

	public Celda getCelda(int i, int j) {
		// TODO Auto-generated method stub
		return celdas[i][j];
	}

	public boolean getEstado() {
		// TODO Auto-generated method stub
		return estado;
	}
	
	

	private void marked(Celda[][] juego) {
		for (int f = 0; f<9;f++) {
			for (int c = 0; c<9; c++){
				if (juego [f][c].getMark() && juego[f][c].getVariable()) {
					juego [f][c].setContenido(juego [f][c].getContenido() + 10);
				}
			}
		}
	}
	
	public void unmark(Celda[][] juego) {
		for (int f = 0; f<9;f++) {
			for (int c = 0; c<9; c++){
				if (juego [f][c].getMark() && juego[f][c].getVariable() && juego[f][c].getContenido()!=0) {
					juego [f][c].setMark(false);
					juego[f][c].setContenido(juego [f][c].getContenido());
				}
			}
		}
	}
	
}
