package games.spacetrash.Utilidades;

import com.badlogic.gdx.physics.box2d.Body;

public class SelectorBodys {

	public static Body getBala(Body A){
		if (A.getUserData().getClass().toString().indexOf("Bala")!= -1) {
			return A;
		}else{ 
			return null;
		}
	}
	public static Body getBala(Body A, Body B){
		Body ret;
		ret = getBala(A);
		if (ret == null){
			return getBala(B);
		}else{
			return ret;
		}
	}
	
	public static Body getTecho(Body A){
		if ((A.getUserData().getClass().equals(String.class))){
			if (((String)A.getUserData()).equals("Techo")){
				return A;
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
	
	public static Body getTecho(Body A, Body B){
		Body ret;
		ret = getTecho(A);
		if (ret == null){
			return getTecho(B);
		}else{
			return ret;
		}
	}
	
	public static Body getEnemigo(Body A){
		if (A.getUserData().getClass().getSuperclass().toString().indexOf("Enemigo")!=-1) {
			return A;
		}else {
			return null;
		}
	}

	public static Body getEnemigo(Body A, Body B){
		Body ret;
		ret = getEnemigo(A);
		if (ret == null){
			return getEnemigo(B);
		}else{
			return ret;
		}
	}
	
}
