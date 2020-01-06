package SampleGame;

import java.util.BitSet;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import static javafx.scene.input.KeyCode.*;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Input{

	private BitSet keyboardBitSet = new BitSet();
	
	private Scene scene = null;
	
	private boolean pause;
	private boolean escape = false;
			
	public Input(Scene scene) {
		this.scene = scene;
	}

	public void addListeners() {
		//scene.addEventFilter(KeyEvent.KEY_RELEASED, keyPP);
		scene.addEventFilter(KeyEvent.KEY_RELEASED, keyPR);
		scene.addEventFilter(KeyEvent.KEY_RELEASED, keyPPR);

	}

	public void removeListeners() {
		//scene.removeEventFilter(KeyEvent.KEY_PRESSED, keyPP);
		scene.removeEventFilter(KeyEvent.KEY_RELEASED, keyPR);
		scene.removeEventFilter(KeyEvent.KEY_RELEASED, keyPPR);

	}
	
	// TOUCHE ESPACE
	
	private EventHandler<KeyEvent> keyPPR = new EventHandler<KeyEvent>() {
		@Override
		public void handle(KeyEvent event) {
			// register key up
			keyboardBitSet.set(event.getCode().ordinal(), false);
			event.consume();
		}
	};
	
	private EventHandler<KeyEvent> keyPR = new EventHandler<KeyEvent>() {
		@Override
		public void handle(KeyEvent event) {
			if(event.getCode() == KeyCode.SPACE) {
				if(pause == true) {
					pause = false;
				}
				else {
					pause = true;
				}
			}
		}
	};
	
	private boolean is(KeyCode key) {
		return keyboardBitSet.get(key.ordinal());
	}
	
	public boolean isPause() {
		return pause;
	}
	
	public boolean isExit() {
		return is(ESCAPE);
	}
}
