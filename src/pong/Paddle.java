package pong;

import java.awt.Color;

public class Paddle extends Sprite {
	static final int WIDTH = 10, HEIGHT = 100;
	static final int EDGE_DISTANCE = 40;
	static final Color PADDLE_COLOUR = Color.WHITE;
	public Paddle(Player player, int panelWidth, int panelHeight) {
		setWidth(WIDTH);
		setHeight(HEIGHT);
		setColour(PADDLE_COLOUR);
		if(player == Player.One) {
			setxPosition(EDGE_DISTANCE);
		}else {
			setxPosition(panelWidth - EDGE_DISTANCE - getWidth());
		}
		setInitialPosition(getxPosition(), panelHeight/2 - (getHeight()/2));
		resetToInitialPosition();
	}
}
