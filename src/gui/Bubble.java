package gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bubble extends ImageView{
	
	public double diffX;
	public double diffY;
	
	public double locX;
	public double locY;
	
	public int rescueMe;

	public Bubble(Image img, double lX, double lY, double dX, double dY) {
		
		setImage(img);
		
		setFitHeight(60);
		setPreserveRatio(true);
		
		diffX = dX;
		diffY = dY;
		locX = lX;
		locY = lY;
		
		rescueMe = 0;
	}
}
