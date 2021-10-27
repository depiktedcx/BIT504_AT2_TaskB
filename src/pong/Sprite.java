package pong;

import java.awt.Color;

public class Sprite {
	private Color colour;
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
	public void setXPosition(int newX, int panelWidth) {
		if(newX < 0) {
			newX = 0;
		}
		else if(newX + width > panelWidth) {
			newX = panelWidth - width;
		}
		xPosition = newX;
	}
	public void setYPosition(int newY, int panelHeight) {

		if(newY < 0) {
			newY = 0;
		}
		else if(newY + height > panelHeight) {
			newY = panelHeight - height;
		}
		yPosition = newY;
	}
}
