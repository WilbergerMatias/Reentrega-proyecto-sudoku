package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.*;
import logica.*;

@SuppressWarnings("serial")
public class interfaz_juego extends JFrame {
	
	private JPanel contenedorJuego;
	private JPanel contenedorBotones;
	private Grid sudoku;
	private JPanel contentPane;
	private Tiempo reloj;
	private JLabel[][] sudokuVisual;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					interfaz_juego frame = new interfaz_juego();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public interfaz_juego() {
		setTitle("SUDOKU");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 50, 1200, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		contenedorJuego = new JPanel();
		contenedorJuego.setBorder(new EmptyBorder(5, 5, 5, 5));
		contenedorJuego.setLayout(new GridLayout(9,9, 5, 5));
		contenedorBotones = new JPanel();
		contenedorBotones.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		/**
		 * agregamos el contenedor del la grafica del juego y el contenedor del tiempo
		 */
		
		
		reloj = new Tiempo();
		reloj.setBounds(5, 445, 300, 100);
		contentPane.add(reloj, BorderLayout.NORTH);
		
		sudoku = new Grid();
		setup();
		setBotones();
		contentPane.add(contenedorJuego, BorderLayout.CENTER);
		contentPane.add(contenedorBotones, BorderLayout.SOUTH);
		
		
		
		if (!sudoku.getEstado()) {
			warning w = new warning("La solucion utilizada para el problema sudoku, no es una solucion valida o no respeta el formato que debe tener, u ocurrio un error al leerlo.");
			w.setVisible(true);
		}
		else {
			Reglas R = new Reglas();
			R.setVisible(true);
		}	
		
		}
	
	private void setBotones() {
		// TODO Auto-generated method stub
		JButton bStart = new JButton("Start");
		bStart.setBounds(62, 425, 164, 28);
		contenedorBotones.add(bStart);
		bStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				reloj.start();
			}
		});	
		
		JButton bReset = new JButton("Reset");
		bReset.setBounds(62, 425, 164, 28);
		contenedorBotones.add(bReset);
		bReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					sudoku.reiniciar();
					actualizar();
					reloj.restart();
			}
		});
		
		JButton bVerificar = new JButton("Verificar");
		bVerificar.setBounds(62, 425, 164, 28);
		contenedorBotones.add(bVerificar);
		bVerificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					boolean victory = sudoku.verificar(sudoku.getCeldas());
					Victoria V = new Victoria(victory);
					V.setVisible(true);
					actualizar();
					if (victory)
						reloj.stop();
			}
		});
		JButton btnRegl = new JButton("REGLAS");
		btnRegl.setBounds(62, 425, 164, 28);
		contenedorBotones.add(btnRegl);
		btnRegl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Reglas R = new Reglas();
				R.setVisible(true);
			}
		});
	}

	/**
	 * prepara la parte visual del juego y vincula su comportamiento a la logica
	 */
	private void setup() {
		// TODO Auto-generated method stub
		sudokuVisual = new JLabel[sudoku.getDimension()][sudoku.getDimension()];
		for (int fil = 0; fil<sudoku.getDimension();fil++) {
			for (int col = 0; col<sudoku.getDimension();col++) {
				Celda c = sudoku.getCelda(fil, col);
				ImageIcon grafico = c.getVisual().getImagen();
				JLabel nuevo = new JLabel();
				sudokuVisual[fil][col] = nuevo;
				
				contenedorJuego.add(nuevo);
				
				ajustar(nuevo,grafico);
				nuevo.setIcon(grafico);
				
				nuevo.addComponentListener(new ComponentAdapter() {
					public void componentResized(ComponentEvent e) {
						ajustar(nuevo,grafico);
						nuevo.setIcon(grafico);
					}
				});
				
				nuevo.addMouseListener( new MouseAdapter(){
					public void mouseClicked(MouseEvent e) {
						sudoku.accionar(c);
						ajustar(nuevo,grafico);
					}
				});
			
			}
		}	
	}

	protected void actualizar() {
		for (int i = 0; i<sudoku.getDimension();i++) {
			for (int j = 0; j<sudoku.getDimension();j++) {
				Celda c = sudoku.getCelda(i, j);
				
				ImageIcon grafico = c.getVisual().getImagen();
				JLabel Lab = sudokuVisual[i][j];
				
				ajustar(Lab,grafico);
				Lab.setIcon(grafico);
			}
		}	
		
	}

	private void ajustar(JLabel l, ImageIcon grafico) {
		Image im = grafico.getImage();
		if (im != null) {
			Image Nim = im.getScaledInstance(100, 60, 0);
			grafico.setImage(Nim);
			l.repaint();
		}
	}     

}