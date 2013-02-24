package games.spacetrash.Bichos;

import java.util.ArrayList;

import games.spacetrash.Armas.*;

import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.extension.physics.box2d.util.Vector2Pool;
import org.anddev.andengine.extension.physics.box2d.util.constants.PhysicsConstants;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.opengl.vertex.RectangleVertexBuffer;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;

public class Pino extends AnimatedSprite {
	// ===========================================================
	// Fields
	// ===========================================================
	private Arma arma;
	Body body;

	// ===========================================================
	// Constructors
	// ===========================================================
	private void inicializacion(PhysicsWorld physicsWorld, FixtureDef fixDev) {
		
		this.arma = new PistolaLineal();
		
		
		final float halfWidth = this.getWidthScaled() * 0.5f / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT;
		
		final float halfHeight = this.getHeightScaled() * 0.5f /PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT;

		final float top = -halfHeight;
		final float bottom = halfHeight;
		final float left = -halfHeight;
		final float centerX = 0;
		final float right = halfWidth;

		final Vector2[] vertices = {
				new Vector2(centerX, top),
				new Vector2(right, bottom),
				new Vector2(left, bottom)
		};

		body = PhysicsFactory.createPolygonBody(physicsWorld, this, vertices,BodyType.DynamicBody, fixDev);
		
		// creamos el body del Pino
		//body = PhysicsFactory.createBoxBody(physicsWorld, this,BodyType.DynamicBody, fixDev);
		
		body.setFixedRotation(true);

		// guardamos el cuerpo del pino en el pino
		// this.setUserData(body);
	}

	public Pino(float pX, float pY, TiledTextureRegion pTiledTextureRegion,
			PhysicsWorld physicsWorld, FixtureDef fixDev) {
		super(pX, pY, pTiledTextureRegion);
		// TODO Auto-generated constructor stub
		inicializacion(physicsWorld, fixDev);
	}

	public Pino(float pX, float pY, TiledTextureRegion pTiledTextureRegion,
			RectangleVertexBuffer pRectangleVertexBuffer,
			PhysicsWorld physicsWorld, FixtureDef fixDev) {
		super(pX, pY, pTiledTextureRegion, pRectangleVertexBuffer);
		// TODO Auto-generated constructor stub
		inicializacion(physicsWorld, fixDev);
	}

	public Pino(float pX, float pY, float pTileWidth, float pTileHeight,
			TiledTextureRegion pTiledTextureRegion, PhysicsWorld physicsWorld,
			FixtureDef fixDev) {
		super(pX, pY, pTileWidth, pTileHeight, pTiledTextureRegion);
		// TODO Auto-generated constructor stub
		inicializacion(physicsWorld, fixDev);
	}

	public Pino(float pX, float pY, float pTileWidth, float pTileHeight,
			TiledTextureRegion pTiledTextureRegion,
			RectangleVertexBuffer pRectangleVertexBuffer,
			PhysicsWorld physicsWorld, FixtureDef fixDev) {
		super(pX, pY, pTileWidth, pTileHeight, pTiledTextureRegion,
				pRectangleVertexBuffer);
		// TODO Auto-generated constructor stub
		inicializacion(physicsWorld, fixDev);
	}

	public Body getBody(){
		return body;
	}
	
	// ===========================================================
	// M�todos
	// ===========================================================
	public void moverse(Vector2 gravity) {
		//Body body = (Body) this.getUserData();
		gravity = Vector2Pool.obtain(gravity);
		body.setLinearVelocity(gravity);	
		Vector2Pool.recycle(gravity);
	}

	// TODO: Ver si devuelve arreglo de sprites o balas
	public ArrayList<Bala> disparar() {
		return this.arma.disparar(this.getX() + this.getWidth() / 2 - 12.5f,
				this.getY());
	}

}
