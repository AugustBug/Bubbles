package main;

// Bubbles
// Ahmert
// 21.02.2013

import gui.BubblesField;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class ApplicationMain extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	private BubblesField bubs;
	private Stage primaryStage;

	@Override
	public void start(final Stage emptyStage) {
		
		primaryStage = new Stage(StageStyle.UNDECORATED);
		
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.setFullScreen(true);
		primaryStage.setAlwaysOnTop(true);
		primaryStage.initModality(Modality.APPLICATION_MODAL);
		
		bubs = new BubblesField();

		Scene scene = new Scene(bubs);
		scene.setFill(null);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
		bubs.startAnimation();
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent key) {

				if (key.getCode() == KeyCode.SPACE) {
					bubs.playPauseAnimation();
				} else if(key.getCode() == KeyCode.ESCAPE) {
					primaryStage.close();
				}

			}
		});
	}
}