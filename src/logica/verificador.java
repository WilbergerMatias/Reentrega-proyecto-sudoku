package logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class verificador {
	/*
	 *Esta clase verifica que el archivo contenga suficientes elementos como para armar un sudoku. 
	 */
	public boolean verificar(String s) {
		Scanner filereader = null;
		try {
			filereader = new Scanner (new File(s));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int cont = 0;
		for (int f = 0; f<9;f++) {
			for (int c = 0; c<9; c++){
				int n = filereader.nextInt();
				if ( n>9 || n<1)
					return false;
				cont++;
			}
		}
		if (cont == 81) {
			filereader.close();
			return true;
		}
		else
			return false;
	}
}
