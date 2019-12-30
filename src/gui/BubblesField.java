package gui;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.util.Duration;

public class BubblesField extends Group {

	Image imgBubble;

	Random random;
	private int screenWidth;
	private int screenHeight;

	private static final int BUBBLE_LIMIT = 20;
	private int frameCounter;

	private ArrayList<Bubble> bubs;

	private Timeline timeline;

	public BubblesField() {

		imgBubble = new Image("resources/bubble.png");

		random = new Random();

		bubs = new ArrayList<>();

		frameCounter = 1;

		screenWidth = (int) Screen.getPrimary().getBounds().getWidth();
		screenHeight = (int) Screen.getPrimary().getBounds().getHeight();

		generateABuble();
		generateABuble();

	}

	public void startAnimation() {

		KeyFrame keyFrame = new KeyFrame(Duration.millis(10),
				new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						for (int i = 0; i < bubs.size(); i++) {
							Bubble bub = bubs.get(i);
							
							if(bub.rescueMe > 0) {
								bub.rescueMe -= 1;
							}
							if ((bub.locX + bub.diffX) < 0.0) {
								bub.diffX = -1 * bub.diffX;
							} else if ((bub.locX + bub.diffX) > (screenWidth - 60)) {
								bub.diffX = -1 * bub.diffX;
							}

							if ((bub.locY + bub.diffY) < 0.0) {
								bub.diffY = -1 * bub.diffY;
							} else if ((bub.locY + bub.diffY) > (screenHeight - 60)) {
								bub.diffY = -1 * bub.diffY;
							}

							for (int j = 0; j < i; j++) {
								Bubble bubToCompare = bubs.get(j);

								int distance = (int) (((bub.locX) - (bubToCompare.locX))
										* ((bub.locX) - (bubToCompare.locX)) + ((bub.locY) - (bubToCompare.locY))
										* ((bub.locY) - (bubToCompare.locY)));

								if (distance < 3700) {
									if (distance > 3500) {
										if ((bubToCompare.rescueMe > 0)
												&& (bub.rescueMe > 0)) {
											bubToCompare.rescueMe -= 1;
											bub.rescueMe -= 1;
										} else {
											crash(bub, bubToCompare);
											break;
										}
									} else {
										bubToCompare.rescueMe = 15;
										bub.rescueMe = 15;
									}
								}
							}

							bub.locX += bub.diffX;
							bub.locY += bub.diffY;

							bub.setTranslateX(bub.locX);
							bub.setTranslateY(bub.locY);
						}

						if (((++frameCounter % 300) == 0)
								&& (bubs.size() <= BUBBLE_LIMIT)) {
							generateABuble();
						}
					}
				});

		timeline = new Timeline();
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.getKeyFrames().add(keyFrame);

		timeline.play();
	}

	private void crash(Bubble bub1, Bubble bub2) {
		double tempDiffX = bub1.diffX;
		double tempDiffY = bub1.diffY;

		bub1.diffX = bub2.diffX;
		bub1.diffY = bub2.diffY;
		bub2.diffX = tempDiffX;
		bub2.diffY = tempDiffY;
	}

	public void playPauseAnimation() {
		if (timeline.getStatus() == Status.RUNNING) {
			timeline.pause();
		} else if (timeline.getStatus() == Status.PAUSED) {
			timeline.play();
		}
	}

	public void generateABuble() {
		double bubX = random.nextInt(screenWidth - 60);
		double bubY = random.nextInt(screenHeight - 60);

		double diffX = (double) (10 - random.nextInt(20)) / 10;
		double diffY = (double) (10 - random.nextInt(20)) / 10;

		Bubble bubble = new Bubble(imgBubble, bubX, bubY, diffX, diffY);

		bubble.setTranslateX(bubX);
		bubble.setTranslateY(bubY);

		bubs.add(bubble);
		getChildren().add(bubble);
	}
}
