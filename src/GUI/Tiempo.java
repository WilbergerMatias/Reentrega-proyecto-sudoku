package GUI;

import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Tiempo extends JPanel {
	
	protected Timer relojVirtual;
	protected ImageIcon[] recursos;
	protected int h, m, s;
	protected JLabel Lhora1, Lhora2, Lminuto1, Lminuto2, Lsegundo1, Lsegundo2, sep1, sep2;
	
	public Tiempo() {
		Lhora1 = new JLabel();
		Lhora2 = new JLabel();
		Lminuto1 = new JLabel();
		Lminuto2 = new JLabel();
		Lsegundo1 = new JLabel();
		Lsegundo2 = new JLabel();
		sep1 = new JLabel();
		sep2 = new JLabel();
		h = m = s = 0;
		recursos = Recursos();
		setup();
		Tsetup();
	}

	private void Tsetup() {
		// TODO Auto-generated method stub
		relojVirtual = new Timer(1000,  e -> {
			s++;
		    if (s == 60) {
		    	m++;
		        s = 0;
		    }
		    if (m == 60) {
		    	m = 0;
		        h++;
		    }
		    ImageIcon aux;
		    aux = recursos[h/10];
		    Lhora1.setIcon(aux);
		    reacomodar(Lhora1, aux);
		    
		    aux = recursos[h%10];
		    Lhora2.setIcon(aux);
		    reacomodar(Lhora1, aux);
		    
		    aux = recursos[m/10];
		    Lminuto1.setIcon(aux);
		    reacomodar(Lhora1, aux);
		    
		    aux = recursos[m%10];
		    Lminuto2.setIcon(aux);
		    reacomodar(Lhora1, aux);
		    
		    aux = recursos[s/10];
		    Lsegundo1.setIcon(aux);
		    reacomodar(Lhora1, aux);
		    
		    aux = recursos[s%10];
		    Lsegundo2.setIcon(aux);
		    reacomodar(Lhora1, aux);

		});
	}

	/**
	 * comandos
	 */
	public void start() {
		relojVirtual.start();
	}
	
	public void stop() {
		relojVirtual.stop();
	}
	
	public void restart() {
		relojVirtual.restart();
		m = s = h = 0;
	}
	
	/**
	 * setters y getters
	 */
	public int getSegundos() {
		return s;
	}
	
	public int getMinutos() {
		return m;
	}
	
	public int getHoras() {
		return h;
	}
	
	/**
	 * Metodos auxiliares
	 */
	private void reacomodar(JLabel lab, ImageIcon rec) {
		Image image = rec.getImage();
		if (image != null) {  
			Image newimg = image.getScaledInstance(lab.getWidth(), lab.getHeight(),  java.awt.Image.SCALE_SMOOTH);
			rec.setImage(newimg);
			lab.repaint();
		}
	}

	private ImageIcon[] Recursos() {
		ImageIcon[] aux = new ImageIcon[11];
		String path = "/imagenes/";
		for (int i = 0; i<11;i++) {
			aux[i] = new ImageIcon(getClass().getResource(path + i + ".jpg"));
		}
		return aux;
	}

	private void setup() {
		// TODO Auto-generated method stub
		this.setLayout(new GridLayout(0,8,0,0));
		
		Lhora1.setIcon(recursos[0]);
		this.add(Lhora1);
		
		Lhora2.setIcon(recursos[0]);
		this.add(Lhora2);
		
		sep1.setIcon(recursos[10]);
		this.add(sep1);
		
		Lminuto1.setIcon(recursos[0]);
		this.add(Lminuto1);
		
		Lminuto2.setIcon(recursos[0]);
		this.add(Lminuto2);
		
		sep2.setIcon(recursos[10]);
		this.add(sep2);
		
		Lsegundo1.setIcon(recursos[0]);
		this.add(Lsegundo1);
		
		Lsegundo2.setIcon(recursos[0]);
		this.add(Lsegundo2);
	}
}
