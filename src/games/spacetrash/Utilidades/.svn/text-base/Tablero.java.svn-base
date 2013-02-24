package games.spacetrash.Utilidades;

import org.anddev.andengine.entity.text.ChangeableText;

import android.util.Log;

//clase usada para llevar el marcador de puntos
public class Tablero {

	private int puntos;
	
	private ChangeableText score;
	
	private static Tablero instance;
	
	private Tablero(){}
	
	public static Tablero getInstance(){
		
		if(instance == null){
			instance = new Tablero();
		}
		
		return instance;
		
	}
	
	public void setChangeableText(ChangeableText score){
		this.score = score;
	}
	
	public void addScore(int puntos){
		this.puntos += puntos;
		Log.d("Tablero",""+puntos);
		this.score.setText("Score: "+Integer.toString(this.puntos));
		
	}
}
