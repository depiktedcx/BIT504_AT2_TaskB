package pong;

import java.awt.Color;

public class Ball extends Sprite{
	private static final Color BALL_COLOUR = Color.WHITE;
	private static final int WIDTH = 25, HEIGHT = 25;
	public Ball(int panelWidth, int panelHeight) {
		setColour(BALL_COLOUR);
		setWidth(WIDTH);
		setHeight(HEIGHT);
		setInitialPosition(panelWidth/2 - (getWidth()/2), panelHeight/2 - (getHeight() / 2));
		resetToInitialPosition();
	}
}