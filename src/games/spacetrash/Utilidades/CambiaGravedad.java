package games.spacetrash.Utilidades;

import games.spacetrash.Bichos.Enemigo;

import java.util.Iterator;

import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class CambiaGravedad implements IUpdateHandler{

	private PhysicsWorld physicsWorld;
	
	public CambiaGravedad(PhysicsWorld physicsWorld) {
		// TODO Auto-generated constructor stub
		this.physicsWorld = physicsWorld;
	}
	
	@Override
	public void onUpdate(float arg0) {
		// TODO Auto-generated method stub
		 Vector2 balloonForce = new Vector2(0,-8f);
		 Iterator<Body> bodies = this.physicsWorld.getBodies();
		 while (bodies.hasNext()) {
			Body b = bodies.next();
			
			if(b.getUserData()!=null){
				if (b.getUserData().getClass().toString().indexOf("TanqueCombustible")!=-1){
					
					Enemigo enemigo = (Enemigo)b.getUserData();
					
					b.applyForce(balloonForce, b.getWorldCenter());
					
					
					
				}
			}
			
		 }
    
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	
	
}
