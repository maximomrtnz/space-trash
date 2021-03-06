package games.spacetrash.colisiones;

import org.anddev.andengine.extension.physics.box2d.PhysicsConnector;
import org.anddev.andengine.util.Debug;

import games.spacetrash.Armas.Bala;
import games.spacetrash.Bichos.Enemigo;
import games.spacetrash.Niveles.Nivel;
import games.spacetrash.Utilidades.BalanzaBasura;
import games.spacetrash.Utilidades.BorraBodies;
import games.spacetrash.Utilidades.SelectorBodys;
import games.spacetrash.Utilidades.Tablero;

import com.badlogic.gdx.physics.box2d.Body;

public class ccBalaEnemigo extends chainColisiones {

	@Override
	protected boolean _procesar(Body A, Body B, Nivel nivel) {
		// TODO Auto-generated method stub
		
		Body bala, enemigo;
		bala = SelectorBodys.getBala(A, B);
		enemigo = SelectorBodys.getEnemigo(A, B);
		
		if ((bala!=null) && (enemigo!=null)){
			//pasos para borrar la bala
			PhysicsConnector physicsConnector = nivel.getPhysicsWorlds().getPhysicsConnectorManager().findPhysicsConnectorByShape((Bala)bala.getUserData());
			nivel.getPhysicsWorlds().unregisterPhysicsConnector(physicsConnector);
			
			bala.setActive(false);
			((Bala)bala.getUserData()).detachSelf();
			BorraBodies.getInstance().add(bala);
			
			//pasos para borrar enemigo
			physicsConnector = nivel.getPhysicsWorlds().getPhysicsConnectorManager().findPhysicsConnectorByShape((Enemigo)enemigo.getUserData());
			nivel.getPhysicsWorlds().unregisterPhysicsConnector(physicsConnector);
				
			enemigo.setActive(false);
			((Enemigo)enemigo.getUserData()).detachSelf();
			Tablero.getInstance().addScore(((Enemigo)enemigo.getUserData()).getPuntosToKill());
			BalanzaBasura.getInstance().subBasura(((Enemigo)enemigo.getUserData()).getWeight());
			BorraBodies.getInstance().add(enemigo);

			return true;
		}else{
			return false;
		}
	}

}
