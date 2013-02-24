package games.spacetrash.Armas;

import java.util.ArrayList;

import org.anddev.andengine.entity.modifier.MoveModifier;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

public class PistolaLineal extends Arma {
	
	static TextureRegion dibujoBala;
	static float balaWidth = 20;
	static float balaHeight = 25;
	public static void setTexturaBala(TextureRegion textura){
		PistolaLineal.dibujoBala = textura;
	}
	
	
	@Override
	public ArrayList<Bala> disparar(float x, float y) {
		// TODO Auto-generated method stub
		ArrayList<Bala> balas = new ArrayList<Bala>();
		Bala b = new Bala(x,y,balaWidth, balaHeight, dibujoBala);
		//b.registerEntityModifier(new MoveModifier(0.5f,x,x, y, 0));
		balas.add(b);
		return balas;		
	}

}
