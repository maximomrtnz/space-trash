package games.spacetrash.colisiones;

import games.spacetrash.Niveles.Nivel;

import com.badlogic.gdx.physics.box2d.Body;

public abstract class chainColisiones {

	private chainColisiones cSiguiente;

	public chainColisiones() {

	}

	public chainColisiones(chainColisiones siguiente) {
		cSiguiente = siguiente;
	}

	public final boolean procesar(Body A, Body B, Nivel nivel) {
		if (_procesar(A, B, nivel)) {
			return true;
		} else {
			if (cSiguiente != null) {
				return cSiguiente.procesar(A, B, nivel);
			} else {
				return false;
			}
		}
	}

	public chainColisiones getSiguiente() {
		return cSiguiente;
	}

	public void setSiguiente(chainColisiones siguiente) {
		cSiguiente = siguiente;
	}

	protected abstract boolean _procesar(Body A, Body B, Nivel nivel);

}
