package pong;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;

public class PongPanel extends JPanel implements ActionListener, KeyListener {

	private static final Color WINDOW_BACKGROUND = Color.BLACK;
	private static final int TIMER_DELAY = 5;
	private static final int BALL_MOVE_SPEED = 5;
	private static final int SCORE_TO_WIN = 3;
	private static final int PADDLE_SPEED = 5;
	int player1Score = 0, player2Score = 0;
	Player gameWinner;
	Ball ball;
	Paddle paddle1, paddle2;
	GameState gameState = GameState.Initialising;
	
	public PongPanel() {
		setBackground(WINDOW_BACKGROUND);
		Timer timer = new Timer (TIMER_DELAY, this);
		timer.start();
		addKeyListener(this);
		setFocusable(true);
	}
	
	public void createObjects() {
		ball = new Ball(getWidth(), getHeight());
		paddle1 = new Paddle(Player.One, getWidth(), getHeight());
		paddle2 = new Paddle(Player.Two, getWidth(), getHeight());
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintDottedLine(g);
		if(gameState != GameState.Initialising) {
			paintSprite(g, ball);
			paintSprite(g, paddle1);
			paintSprite(g, paddle2);
			paintScores(g);
		}
		if(gameState == GameState.GameOver) {
			paintWin(g);
		}
	}
	
	private void paintDottedLine(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] {9}, 0);
		g2d.setStroke(dashed);
		g2d.setPaint(Color.WHITE);
		g2d.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
		g2d.dispose();
	}

	private void update() {
		switch(gameState) {
		case Initialising:{
			createObjects();
			player1Score = 0;
			player2Score = 0;
			gameWinner = null;
			ball.setxVelocity(0);
			ball.setyVelocity(0);
			gameState = GameState.Playing;
			break;
		}
		case Playing:{
			moveObject(paddle1);
			moveObject(paddle2);
			moveObject(ball);
			checkWallBounce();
			checkPaddleBounce();
			checkWin();
			break;
		}
		case GameOver:{
			break;
		}
		}
	}
	
	private void paintSprite(Graphics g, Sprite sprite) {
		g.setColor(sprite.getColour());
		g.fillRect(sprite.getxPosition(), sprite.getyPosition(), sprite.getWidth(), sprite.getHeight());
	}
	
	@Override
	public void keyTyped(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent event) {
		if(ball.getxVelocity() == 0 && ball.getyVelocity() == 0) {
			ball.setxVelocity(BALL_MOVE_SPEED);
			ball.setyVelocity(BALL_MOVE_SPEED);
		}
		if(event.getKeyCode() == KeyEvent.VK_UP) {
			paddle2.setyVelocity(-PADDLE_SPEED);
		}else if(event.getKeyCode() == KeyEvent.VK_DOWN) {
			paddle2.setyVelocity(PADDLE_SPEED);
		}else if(event.getKeyCode() == KeyEvent.VK_W) {
			paddle1.setyVelocity(-PADDLE_SPEED);
		}else if(event.getKeyCode() == KeyEvent.VK_S) {
			paddle1.setyVelocity(PADDLE_SPEED);
		}
		if(gameState == GameState.GameOver && event.getKeyCode() == KeyEvent.VK_R) {
			gameState = GameState.Initialising;
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {
		if(event.getKeyCode() == KeyEvent.VK_UP || event.getKeyCode() == KeyEvent.VK_DOWN) {
			paddle2.setyVelocity(0);
		}else if(event.getKeyCode() == KeyEvent.VK_W || event.getKeyCode() == KeyEvent.VK_S) {
			paddle1.setyVelocity(0);
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		update();
		repaint();
	}
	
	public void moveObject(Sprite sprite) {
		sprite.setxPosition(sprite.getxPosition() + sprite.getxVelocity(), getWidth());
		sprite.setyPosition(sprite.getyPosition() + sprite.getyVelocity(), getHeight());
	}
	
	public void resetBall() {
		ball.resetToInitialPosition();
	}
	
	public void checkWallBounce() {
		if(ball.getxPosition() <= 0 || ball.getxPosition() >= getWidth() - ball.getWidth()) {
			if(ball.getxVelocity() > 0) {
				addScore(Player.One);
			}else {
				addScore(Player.Two);
			}
			ball.setxVelocity(-ball.getxVelocity());
			resetBall();
		}else if(ball.getyPosition() <= 0 || ball.getyPosition() >= getHeight() - ball.getHeight()) {
			ball.setyVelocity(-ball.getyVelocity());
		}
	}
	
	public void checkPaddleBounce() {
		if((ball.getxVelocity() < 0 && ball.getRectangle().intersects(paddle1.getRectangle()))|| (ball.getxVelocity() > 0 && ball.getRectangle().intersects(paddle2.getRectangle()))) {
			ball.setxVelocity(-ball.getxVelocity());
			ball.randomBallColour();
		}
	}
	
	public void addScore(Player player) {
		if(player == Player.One) {
			player1Score++;
		}else {
			player2Score++;
		}
	}
	
	public void checkWin() {
		if(player1Score == SCORE_TO_WIN) {
			gameWinner = Player.One;
		}else if(player2Score == SCORE_TO_WIN) {
			gameWinner = Player.Two;
		}
		if(gameWinner == Player.One || gameWinner == Player.Two) {
			gameState = GameState.GameOver;
		}
	}
	
	public void paintScores(Graphics g) {
		int xPadding = 100;
		int yPadding = 100;
		int fontSize = 50; 
		Font scoreFont = new Font("Serif", Font.BOLD, fontSize);
		String leftScore = Integer.toString(player1Score);
		String rightScore = Integer.toString(player2Score);
		g.setFont(scoreFont);
		g.drawString(leftScore, xPadding, yPadding);
		g.drawString(rightScore, getWidth()-xPadding, yPadding);
	}
	public void paintWin(Graphics g) {
		int xPadding = getWidth()/4;;
		if(gameWinner == Player.Two) {
			xPadding = xPadding * 3;
		}
		int yPadding = getHeight()/2;
		int fontSize = 50;
		Font font = new Font("Serif", Font.BOLD, fontSize);
		String win = "WIN!";
		g.setFont(font);
		g.drawString(win, xPadding, yPadding);
	}

}
