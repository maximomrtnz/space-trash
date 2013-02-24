package games.spacetrash.colisiones;

import games.spacetrash.Armas.Bala;
import games.spacetrash.Niveles.Nivel;
import games.spacetrash.Utilidades.BorraBodies;
import games.spacetrash.Utilidades.SelectorBodys;

import org.anddev.andengine.extension.physics.box2d.PhysicsConnector;
import org.anddev.andengine.util.Debug;

import com.badlogic.gdx.physics.box2d.Body;

public class ccBalaTecho extends chainColisiones {

	@Override
	protected boolean _procesar(Body A, Body B, Nivel nivel) {
		// TODO Auto-generated method stub
		

		Body bala, techo;
		bala = SelectorBodys.getBala(A,B);
		techo = SelectorBodys.getTecho(A, B);
			
		if ((bala != null) && (techo != null)){
			PhysicsConnector physicsConnector = nivel.getPhysicsWorlds().getPhysicsConnectorManager().findPhysicsConnectorByShape((Bala)bala.getUserData());
			nivel.getPhysicsWorlds().unregisterPhysicsConnector(physicsConnector);
			bala.setActive(false);
			((Bala)bala.getUserData()).detachSelf();
			BorraBodies.getInstance().add(bala);
			return true;
		}
		
		return false;
	}

}
