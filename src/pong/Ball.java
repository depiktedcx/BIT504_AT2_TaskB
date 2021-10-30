package pong;

import java.awt.Color;
import java.util.Random;

public class Ball extends Sprite{
	private static final Color[] BALL_COLOURS = new Color[]{Color.RED, Color.WHITE, Color.BLUE, Color.GREEN, Color.YELLOW};
	private static final Color INIT_BALL_COLOUR = Color.WHITE;
	private static final int WIDTH = 25, HEIGHT = 25;
	public Ball(int panelWidth, int panelHeight) {
		setColour(INIT_BALL_COLOUR);
		setWidth(WIDTH);
		setHeight(HEIGHT);
		setInitialPosition(panelWidth/2 - (getWidth()/2), panelHeight/2 - (getHeight() / 2));
		resetToInitialPosition();
	}
	public void randomBallColour() {
		Random r = new Random();
		setColour(BALL_COLOURS[r.nextInt(BALL_COLOURS.length)]);
	}
}
