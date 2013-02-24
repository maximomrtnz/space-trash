package games.spacetrash.Niveles;

import java.util.ArrayList;
import java.util.Iterator;

import games.spacetrash.Armas.Bala;
import games.spacetrash.Armas.PistolaLineal;
import games.spacetrash.Bichos.*;
import games.spacetrash.Utilidades.BalanzaBasura;
import games.spacetrash.Utilidades.BorraBodies;
import games.spacetrash.Utilidades.CambiaGravedad;
import games.spacetrash.Utilidades.Tablero;
import games.spacetrash.Utilidades.Timer;
import games.spacetrash.colisiones.ccBalaEnemigo;
import games.spacetrash.colisiones.ccBalaTecho;
import games.spacetrash.colisiones.chainColisiones;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.*;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.entity.scene.background.SpriteBackground;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.font.FontFactory;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureBuilder;
import org.anddev.andengine.opengl.texture.atlas.buildable.builder.ITextureBuilder.TextureAtlasSourcePackingException;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.sensor.accelerometer.AccelerometerData;
import org.anddev.andengine.sensor.accelerometer.IAccelerometerListener;
import org.anddev.andengine.ui.activity.BaseGameActivity;
import org.anddev.andengine.util.Debug;
import org.anddev.andengine.util.MathUtils;
import org.anddev.andengine.extension.physics.box2d.PhysicsConnector;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.extension.physics.box2d.util.Vector2Pool;
import org.anddev.andengine.entity.primitive.Rectangle;

import android.graphics.Color;
import android.graphics.Typeface;
import android.hardware.SensorManager;


import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Manifold;


public class Nivel extends BaseGameActivity implements IAccelerometerListener, IOnSceneTouchListener,ContactListener, ITimerCallback {
	// ===========================================================
	// Fields
	// ===========================================================
	private TextureRegion background;
	private int frecuencia;
	private PhysicsWorld mPhysicsWorld;
	private Scene mScene;
	private TimerHandler timer;
	private Pino mPino;
	
	
	//definimos las fuentes del nivel 1
	private BitmapTextureAtlas mFontTextureScore;
	private Font scoreFont;

	private BitmapTextureAtlas mFontTextureTime;
	private Font timeFont;

	
	//definimos dos constantes alto y ancho de la camara
	private int anchoCamara = 720;
	private int altoCamara = 480; 
	
	//definimos nuestro manejador de texturas de Background
	private BitmapTextureAtlas bitmapBackground ;
	
	private TiledTextureRegion texturaPino;
	
	private TextureRegion texturaAsteroide;
	
	private TextureRegion texturaTanqueCombustible;
	
	private TextureRegion bala;
	
	private chainColisiones colisiones;
	
	// ===========================================================
	// Constructors
	// ===========================================================
	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	 
	
	//Funciones de creaci�n del mundo
	
		
	@Override
	public void onLoadComplete() {
		// TODO Auto-generated method stub

	}

	@Override
	public Engine onLoadEngine() {
		// TODO Auto-generated method stub
		
		//creamos el objeto camara
		Camera camera = new Camera(0, 0, this.anchoCamara,this.altoCamara);
		
		//creamos las opciones del Engine entre ellas la resolucion 720 x 480
		//y que el juego lo vamos a ver de Forma LandScape
		EngineOptions engineOptions = new EngineOptions(true,
				ScreenOrientation.LANDSCAPE, new RatioResolutionPolicy(
						this.anchoCamara,this.altoCamara), camera);
		engineOptions.getTouchOptions().setRunOnUpdateThread(true);
		
		
		colisiones = new ccBalaTecho();
		colisiones.setSiguiente(new ccBalaEnemigo());
		
		
		return new Engine(engineOptions);
	}
	
	//este metodo lo usaremos para cargar todos nuestros recursos
	//como musica, efectos de sonido, imagenes, fuentes
	@Override
	public void onLoadResources() {
		// TODO Auto-generated method stub
		
		//cargamos las fuentes
		
		this.mFontTextureScore = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mFontTextureTime = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);

		
		FontFactory.setAssetBasePath("font/");
		
		this.scoreFont = FontFactory.createFromAsset(this.mFontTextureScore, this, "carton regular.ttf", 48, true, Color.rgb(0, 255,0));
		
		this.mEngine.getTextureManager().loadTexture(this.mFontTextureScore);
		
		this.mEngine.getFontManager().loadFont(this.scoreFont);
		
		
		this.timeFont = FontFactory.createFromAsset(this.mFontTextureTime, this, "carton regular.ttf", 48, true, Color.rgb(255, 255,0));
		
		this.mEngine.getTextureManager().loadTexture(this.mFontTextureTime);
		
		this.mEngine.getFontManager().loadFont(this.timeFont);

		
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

		this.bitmapBackground = new BitmapTextureAtlas(1024,512,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);

		this.background = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.bitmapBackground, this, "fondo.png",
						0, 0);
		
		this.mEngine.getTextureManager().loadTexture(this.bitmapBackground);

		//Cargamos la textura del pino
		BuildableBitmapTextureAtlas mBitmapTextureAtlas = new BuildableBitmapTextureAtlas(2048, 2048, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		
		this.texturaPino = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mBitmapTextureAtlas, this, "pino.png",3, 1); 
		
		this.bala = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, this, "bala.png"); 
		
		this.texturaAsteroide = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, this, "asteroide.png");
		
		this.texturaTanqueCombustible =  BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, this, "lata.png");
		
		
		// Le indicamos a la textura que gestione donde cargar� cada imagen
		try {
			mBitmapTextureAtlas.build(new BlackPawnTextureBuilder(0));
		} catch (final TextureAtlasSourcePackingException e) {
			Debug.e(e);
		}

		// Cargamos las im�genes en memoria (dentro de la textura)
		this.mEngine.getTextureManager().loadTexture(mBitmapTextureAtlas);

	}

	@Override
	public Scene onLoadScene() {
		// TODO Auto-generated method stub
		this.mEngine.registerUpdateHandler(new FPSLogger());

		//creamos una nueva scena
		this.mScene = new Scene();
		
		//creamos un sprite para nuestro background
		Sprite backgroundSprite = new Sprite(0, 0, this.background);
		
		//seteamos a nuestra scena un background
		this.mScene.setBackground(new SpriteBackground(backgroundSprite));

		//creamos el mundo fisico
		this.mPhysicsWorld = new PhysicsWorld(new Vector2(0, SensorManager.GRAVITY_EARTH), false);
		
		//Creamos los bordes
		//final VertexBufferObjectManager vertexBufferObjectManager = getVertexBufferObjectManager();
		final Rectangle ground = new Rectangle(0, this.altoCamara - 10, this.anchoCamara, 0);
		final Rectangle roof = new Rectangle(0, 0, this.anchoCamara, 0);
		final Rectangle left = new Rectangle(0, 0, 0, this.altoCamara);
		final Rectangle right = new Rectangle(this.anchoCamara - 2, 0, 0, this.altoCamara);
		
		//el segundo 0 indica que el piso no va a tener rebote, esto disminuye el rebote de los objetos que caen
		final FixtureDef wallFixtureDef = PhysicsFactory.createFixtureDef(0f, 0f, 0.5f);
		//TODO: Ver si agregamos nombres a todas los bordes de la pantalla
		PhysicsFactory.createBoxBody(this.mPhysicsWorld, ground, BodyType.StaticBody, wallFixtureDef);	
		
		
		Body techo = PhysicsFactory.createBoxBody(this.mPhysicsWorld, roof, BodyType.DynamicBody, wallFixtureDef);
		techo.setUserData("Techo");
		
		//se agrega este codigo para que el techo pueda ser dinamico y entonces exista colision entre la bala y el techo
		//pq si es estatico como la bala es de tipo kinematic no habria colision
		//al agregarle una masa gigante logramos que el techo no se vea afectado por el resto del mundo fisico
		MassData data = techo.getMassData();
		data.center.set(999999999,999999999);
		techo.setMassData(data);
	
		PhysicsFactory.createBoxBody(this.mPhysicsWorld, left, BodyType.StaticBody, wallFixtureDef);
		PhysicsFactory.createBoxBody(this.mPhysicsWorld, right, BodyType.StaticBody, wallFixtureDef);
		
		this.mScene.attachChild(ground);
		this.mScene.attachChild(roof);
		this.mScene.attachChild(left);
		this.mScene.attachChild(right);
		
		//agregamos la textura de la bala
		PistolaLineal.setTexturaBala(this.bala);	
		
		//Agregamos el pino
		final FixtureDef objectFixtureDef = PhysicsFactory.createFixtureDef(1, 0f, 0.5f);
		this.mPino = new Pino(this.anchoCamara/2, this.altoCamara-110,100,100, this.texturaPino, mPhysicsWorld, objectFixtureDef);
		//creamos el body del Pino
		//Body body = PhysicsFactory.createBoxBody(this.mPhysicsWorld, mPino, BodyType.DynamicBody, objectFixtureDef);
		//guardamos el cuerpo del pino en el pino
		//mPino.setUserData(body);
		
		Body body = this.mPino.getBody();

		this.mScene.attachChild(this.mPino);
		//agregamos el pino al mundo fisico
		this.mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(this.mPino, body, true, true));
		this.mPhysicsWorld.setContactListener(this);
		
		//cargamos las fuentes
		
		ChangeableText score = new ChangeableText(0, 0, this.scoreFont, "Score: 0",20);
		this.mScene.attachChild(score);
		

		ChangeableText time = new ChangeableText(this.anchoCamara*2/3, 0, this.timeFont, "Time: 03:00",20);
		this.mScene.attachChild(time);

		
		//creamos la progress bar de basura
		
		final Rectangle progressBarBasura = new Rectangle(0, this.altoCamara - 10, 10, 10);
		
		progressBarBasura.setColor(0, 0,255);
		
		this.mScene.attachChild(progressBarBasura);
		
		BalanzaBasura.getInstance().setRectangle(progressBarBasura);
		
		//seteamos el ChangeableText a la clase Tablero
		
		Tablero.getInstance().setChangeableText(score);
		
		
		this.mScene.registerUpdateHandler(mPhysicsWorld);
		this.mScene.setOnSceneTouchListener(this);
		//this.mScene.registerUpdateHandler(new BorraBodies(mPhysicsWorld));
		BorraBodies.getInstance().setPhysicsWorld(mPhysicsWorld);
		this.mScene.registerUpdateHandler(BorraBodies.getInstance());
		
		//registramos el update handler donde colocaremos la creacion de enemigos
		this.mScene.registerUpdateHandler(new TimerHandler(1f,true,this));
		this.mScene.registerUpdateHandler(new CambiaGravedad(mPhysicsWorld));
		
		//registramos el timer
		this.mScene.registerUpdateHandler(new TimerHandler(1f, true,new Timer(time)));
	
		return this.mScene;
	}

	
	//Funciones de jugabilidad	
	@Override
//	public boolean onSceneTouchEvent(Scene arg0, TouchEvent arg1) {
	public boolean onSceneTouchEvent(final Scene pScene, final TouchEvent pSceneTouchEvent) {
		if(this.mPhysicsWorld != null) {
			if(pSceneTouchEvent.isActionDown()){
				//en cada disparo animamos al pino!!
				this.mPino.animate(100,false);	
				ArrayList<Bala> balas = mPino.disparar();
				Iterator<Bala> b = balas.iterator();
				while (b.hasNext()){
					Bala bala = (Bala) b.next();
					this.mScene.attachChild(bala);			
					this.mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(bala, bala.createBody(this.mPhysicsWorld), true, true));
				}
				return true;
			}
		}
	
		return false;
	}

	@Override 
	public void onAccelerometerChanged(AccelerometerData arg0) {
		Vector2 gravity = new Vector2((float) (arg0.getX() * 2.7),0);
		mPino.moverse(gravity);		
	}	
	
	//Funciones para el manejo del juego...
	public void onResumeGame(){
		//TODO: Implementar

		this.enableAccelerometerSensor(this);
	}
	
	public void onPauseGame(){
		//TODO: Implementar
		this.disableAccelerometerSensor();
	}
	
	// ===========================================================
	// Methods
	// ===========================================================
	public void crearEnemigos(){
		//TODO: Implementar
	}
	
	public PhysicsWorld getPhysicsWorlds(){
		return mPhysicsWorld;
	}
	
	// ===========================================================
	// Methods ContactListener
	// ===========================================================
	
	@Override
	public void beginContact(Contact arg0) {
		// TODO Auto-generated method stub
		
		
		Fixture fixtureA = arg0.getFixtureA();
		Fixture fixtureB = arg0.getFixtureB();
		//TODO: Agregar todos los choques que tienen acciones...
		//Por el momento solo detectamos colision  el techo y la bala
		if ((fixtureA.getBody().getUserData() == null) || (fixtureB.getBody().getUserData() == null)){
			return;
		}
		
		colisiones.procesar(fixtureA.getBody(), fixtureB.getBody(), this);
		
	
		
	}

	@Override
	public void endContact(Contact arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postSolve(Contact arg0, ContactImpulse arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preSolve(Contact arg0, Manifold arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTimePassed(TimerHandler arg0) {
		// TODO Auto-generated method stub
		//obtenemos un valor entre 10 numeros
		int valor = MathUtils.random(0,99);
		//con 60% de probavilidad creamos un monstruo
		if(valor<20){
			//creamos un monstruo
			
			valor = MathUtils.random(0,99);
			if(valor<20){
				this.crearTanqueCombustible();
			}else{
				this.crearAsteoride();
			}
			
		}
	
	}

	//metodo para crear un asteroide
	public void crearAsteoride(){
		
		//obtenemos de donde empieza a caer el asteroide
		//vamos a hacer que caiga o del 0,0 o del anchoCamara,0
		int valor = MathUtils.random(0, 99);

		int pYinicial = 0;
		int pXinicial = 0;
		int pXfinal = this.anchoCamara - Math.round(MathUtils.random(0, this.anchoCamara)/2);
		int pYfinal = this.altoCamara;
		
		if(valor<50){
			//restamos el width del asteroide
			pXinicial = this.anchoCamara-40;
			pXfinal = MathUtils.random(0, this.anchoCamara/2);
		}
		
		//creamos el sprite
		Enemigo enemigo = new Asteroide(pXinicial,pYinicial,35,40,this.texturaAsteroide);
		
		//vamos a agregarlo al mundo fisico
		final FixtureDef asteroideFixtureDef = PhysicsFactory.createFixtureDef(0, 0.2f, 0.5f);
		
		Body body = PhysicsFactory.createCircleBody(this.mPhysicsWorld,enemigo,BodyType.DynamicBody,asteroideFixtureDef);
		
		body.setUserData(enemigo);
		
		MassData masa = new MassData();
		
		masa.center.x = enemigo.getWidth() /2 ;
		masa.center.y = enemigo.getHeight() / 2;
		masa.mass = 10;
		
		body.setMassData(masa);		// Ahora nuestro asteroide es pesado, hurra! (?)
		
		this.mScene.attachChild(enemigo);
		
		//agregamos el asteroide al mundo fisico
		this.mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(enemigo, body, true, true));
		
		int pXVector = pXfinal-pXinicial;
		
		int pYVector = pYfinal-pYinicial;
		
		//movemos el asteroide en forma de caida de asteroide
		Vector2 v = Vector2Pool.obtain(pXVector,pYVector);
		
		body.setLinearVelocity(v.x*0.02f, v.y*0.02f);
		
		Vector2Pool.recycle(v);
		
		//agregamos peso a la balnza de basura
		
		BalanzaBasura.getInstance().addBasura(enemigo.getWeight());
		
	}
	
	public void crearTanqueCombustible(){
		
		//el rango en el que puede caer la basura es entre 50 y 670 asi no se va de pantalla
		int xinicial = MathUtils.random(50, this.anchoCamara-50);
		
		//creamos el sprite
		Enemigo enemigo = new TanqueCombustible(xinicial,0,50,75,this.texturaTanqueCombustible);
		
		//vamos a agregarlo al mundo fisico
		final FixtureDef asteroideFixtureDef = PhysicsFactory.createFixtureDef(0, 0.2f, 0.5f);
		
		Body body = PhysicsFactory.createBoxBody(this.mPhysicsWorld,enemigo,BodyType.DynamicBody,asteroideFixtureDef);
		
		body.setUserData(enemigo);
		
		this.mScene.attachChild(enemigo);
		
		//agregamos el asteroide al mundo fisico
		this.mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(enemigo, body, true, true));
		
		body.applyForce(new Vector2(0,-2*this.mPhysicsWorld.getGravity().y), body.getWorldCenter());
		
		this.mPhysicsWorld.setAutoClearForces(true);
		

		BalanzaBasura.getInstance().addBasura(enemigo.getWeight());
	}
	
}
