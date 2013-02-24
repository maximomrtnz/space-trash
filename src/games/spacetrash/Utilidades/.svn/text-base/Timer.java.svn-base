package games.spacetrash.Utilidades;

import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.text.ChangeableText;

//esta clase manejara el tiempo de la aplicacion y se ejecutara cada un segundo
public class Timer implements ITimerCallback{
	
	//cada nivel durara cinco minutos
	private int minutos = 3;
	
	private int segundos=0;
	
	private ChangeableText text;
	
	public Timer(ChangeableText text){
		this.text = text;
	}

	@Override
	public void onTimePassed(TimerHandler arg0) {
		// TODO Auto-generated method stub
		
		if(segundos==0){
			this.segundos=59;
			if(this.minutos == 1){
				this.text.setColor(255,0,0);
			}
			if(!(minutos==0)){
				this.minutos--;
			}
		}else{
			segundos--;
		}
		
		if(segundos<10){
			//si los segundos son menores a 10 agregamos un 0 adelante asi siempre tenemos dos digitos en el timer
			this.text.setText("Time: 0"+Integer.toString(minutos)+" : "+"0"+Integer.toString(segundos));
		}else{
			this.text.setText("Time: 0"+Integer.toString(minutos)+" : "+Integer.toString(segundos));
		}
		
	}
	
	
	
	
}
