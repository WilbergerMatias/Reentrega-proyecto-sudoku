package logica;

import javax.swing.JLabel;

import GUI.imagen;

/**
 * clase Celda, esta guarda un entero, y tiene un estado de inicializado booleano
 *
 */
public class Celda {
	
	/**
	 * atributos de instancia
	 */
	protected imagen visual;
	protected boolean variable;
	protected Integer numero;
	private boolean mark;
	
	/**
	 * constructor
	 */
	
	public Celda(int cont) {
		variable = true;
		numero = cont;
		visual = new imagen();
		visual.cambiar(cont);
		mark = false;
	}
	
	/**
	 * setters y getters
	 * 
	 */
	public void setContenido(int num) {
		numero = num;
		visual.cambiar(numero);
	}
	
	public void setVariable(boolean var) {
		variable = var;
	}
	
	public int getContenido() {
		return numero;
	}
	
	public boolean getVariable(){
		return variable;
	}	
	
	public imagen getVisual() {
		return visual;
	}
	
	public void setVisual(imagen im) {
		this.visual = im;
	}
	
	public boolean getMark() {
		return mark;
	}
	
	public void setMark(boolean m) {
		mark = m;
	}
	
}
