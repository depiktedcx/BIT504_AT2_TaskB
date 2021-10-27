package pong;

import java.awt.Color;
import java.awt.Rectangle;

public class Sprite {
	private Color colour;
	private int initialXPosition, initialYPosition;
	private int xPosition, yPosition;
	private int xVelocity, yVelocity;
	private int width, height;
	public int getxPosition() {
		return xPosition;
	}
	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}
	public int getyPosition() {
		return yPosition;
	}
	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}
	public int getxVelocity() {
		return xVelocity;
	}
	public void setxVelocity(int xVelocity) {
		this.xVelocity = xVelocity;
	}
	public int getyVelocity() {
		return yVelocity;
	}
	public void setyVelocity(int yVelocity) {
		this.yVelocity = yVelocity;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public Color getColour() {
		return colour;
	}
	public void setColour(Color colour) {
		this.colour = colour;
	}
	public void setxPosition(int xPosition, int panelWidth) {
		if(xPosition < 0) {
			xPosition = 0;
		}
		else if(xPosition + width > panelWidth) {
			xPosition = panelWidth - width;
		}
		this.xPosition = xPosition;
	}
	public void setyPosition(int yPosition, int panelHeight) {

		if(yPosition < 0) {
			yPosition = 0;
		}
		else if(yPosition + height > panelHeight) {
			yPosition = panelHeight - height;
		}
		this.yPosition = yPosition;
	}
	public void setInitialPosition(int initialX, int initialY) {
		initialXPosition = initialX;
		initialYPosition = initialY;
	}
	public void resetToInitialPosition() {
		setyPosition(initialYPosition);
		setxPosition(initialXPosition);
	}
	public Rectangle getRectangle() {
		return new Rectangle(getxPosition(), getyPosition(), getWidth(), getHeight());
	}
}
