package SampleGame;

//import java.util.BitSet;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

public class Input {

	private Scene scene = null;

	public Input(Scene scene) {
		this.scene = scene;
	}

	public void addListeners() {
		scene.addEventFilter(MouseEvent.MOUSE_PRESSED, mousePressedEventHandler);
		scene.addEventFilter(MouseEvent.MOUSE_RELEASED, mouseReleasedEventHandler);
	}

	public void removeListeners() {
		scene.removeEventFilter(MouseEvent.MOUSE_PRESSED, mousePressedEventHandler);
		scene.removeEventFilter(MouseEvent.MOUSE_RELEASED, mouseReleasedEventHandler);
	}

	private EventHandler<MouseEvent> mousePressedEventHandler = event -> {
		// register key down
		event.consume();
	};

	private EventHandler<MouseEvent> mouseReleasedEventHandler = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			// register key up
			event.consume();
		}
	};
}
