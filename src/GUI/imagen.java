package GUI;

import javax.swing.ImageIcon;

public class imagen {
	private ImageIcon im;
	protected String [] repos;
	
	public imagen() {
		im = new ImageIcon();
		repos = new String[]{"src/imagenes/0.jpg", "src/imagenes/1.jpg", "src/imagenes/2.jpg", "src/imagenes/3.jpg", "src/imagenes/4.jpg", "src/imagenes/5.jpg", "src/imagenes/6.jpg", "src/imagenes/7.jpg", "src/imagenes/8.jpg", "src/imagenes/9.jpg", "src/filler", "src/imagenes/m1.jpg", "src/imagenes/m2.jpg", "src/imagenes/m3.jpg", "src/imagenes/m4.jpg", "src/imagenes/m5.jpg", "src/imagenes/m6.jpg", "src/imagenes/m7.jpg", "src/imagenes/m8.jpg", "src/imagenes/m9.jpg",};
	}

	public void setImagen(ImageIcon im) {
		this.im = im;
	}
	
	public void setRepositorio(String [] repos) {
		this.repos = repos;
	}
	
	public ImageIcon getImagen() {
		return im;
	}

	public void cambiar(Integer num) {
		// TODO Auto-generated method stub
		ImageIcon nuevo = new ImageIcon(repos[num]);
		im.setImage(nuevo.getImage());
	}
}
