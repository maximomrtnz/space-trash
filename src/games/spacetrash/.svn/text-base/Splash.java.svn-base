package games.spacetrash;

import games.spacetrash.Niveles.Nivel;

import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.opengl.texture.atlas.bitmap.source.AssetBitmapTextureAtlasSource;
import org.anddev.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.anddev.andengine.ui.activity.BaseSplashActivity;

import android.app.Activity;

public class Splash extends BaseSplashActivity {
	
	private static final int SPLASH_DURATION = 3;
	
	@Override
	protected Class<? extends Activity> getFollowUpActivity() {
		// TODO Completar con la clase que sigue al splash
		//ni bien temrine el Splash pasamos al primer nivel
		return Nivel.class;
	}

	@Override
	protected ScreenOrientation getScreenOrientation() {
		return ScreenOrientation.PORTRAIT;
	}

	@Override
	protected float getSplashDuration() {
		return SPLASH_DURATION;
	}

	@Override
	protected IBitmapTextureAtlasSource onGetSplashTextureAtlasSource() {
		return new AssetBitmapTextureAtlasSource(this, "gfx/splash.png");
	}
}
