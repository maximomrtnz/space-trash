package games.spacetrash.Utilidades;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.physics.box2d.Body;

public class BorraBodies implements IUpdateHandler {

	private static BorraBodies st;

	private PhysicsWorld physicsWorld;

	private List<Body> aEliminar = new ArrayList<Body>();



	public boolean addAll(Body arg0) {
		return aEliminar.add(arg0);
	}

	/*
	 * public BorraBodies(PhysicsWorld physicsWorld){ this.physicsWorld =
	 * physicsWorld; }
	 */

	private BorraBodies() {
//		st = new BorraBodies();
	}

	public static BorraBodies getInstance() {
		if (st == null) {
			st = new BorraBodies();
		}
		return st;
	}

	public void setPhysicsWorld(PhysicsWorld pw) {
		physicsWorld = pw;
	}

	@Override
	public void onUpdate(float arg0) {
		Iterator<Body> bodies = aEliminar.iterator();
		while (bodies.hasNext()) {
			Body b = bodies.next();
			this.physicsWorld.destroyBody(b);
			bodies.remove();
		}
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
	}
	
	public boolean add(Body arg0) {
		return aEliminar.add(arg0);
	}

	public boolean addAll(Collection<? extends Body> arg0) {
		return aEliminar.addAll(arg0);
	}

}
