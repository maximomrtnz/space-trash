package games.spacetrash.Utilidades;

import org.anddev.andengine.entity.primitive.Rectangle;

//esta clase maneja el progress bar que indica el total de basura que hay en todo momento
public class BalanzaBasura {
	
	private static BalanzaBasura instance;
	
	//el maximo de basura es el maximo del ancho de pantalla
	private int maxBasura=720;
	
	private int basuraTotal=0;
	
	private Rectangle rectangle;
	
	private BalanzaBasura(){}
	
	public static BalanzaBasura getInstance(){
		if(instance==null){
			instance = new BalanzaBasura();
		}
		return instance;
	}
	
	
	public void addBasura(int basura){
		this.basuraTotal+=basura;
		if(this.basuraTotal<=720){
			rectangle.setSize(this.basuraTotal, 10);
		}		
	}
	
	public void subBasura(int basura){
		
		this.basuraTotal-=basura;
		if(this.basuraTotal<0){
			this.basuraTotal=0;
		}else{
			rectangle.setSize(this.basuraTotal, 10);
		}
	}
	
	public void setRectangle(Rectangle rectangle){
		this.rectangle = rectangle;
	}
}
