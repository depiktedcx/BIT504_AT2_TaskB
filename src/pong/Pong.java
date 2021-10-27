package pong;

import javax.swing.JFrame;

public class Pong extends JFrame {
	private static final int WINDOW_WIDTH = 800;
	private static final int WINDOW_HEIGHT = 600;
	private static final String WINDOW_TITLE = "Pong";

	public static void main(String[] args) {
		new Pong();
	}
	public Pong() {
		setTitle(WINDOW_TITLE);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
