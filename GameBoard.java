import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.JComponent;

public class GameBoard extends JComponent {
	private final int FPS = 140; 
	private Game game;
	private GameState gamestate;
	private Keyboard keyboard;
	public GameBoard() {
		keyboard = new Keyboard();
		game = new Game(this);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(1100, 600);
	}

	@Override
	protected void paintComponent(Graphics arg0) {
		super.paintComponent(arg0);
		Graphics2D graphics = (Graphics2D)arg0;
		graphics.setColor(Color.black);
		graphics.fillRect(0, 0, getWidth(), getHeight());
		game.draw(graphics);
		painLine(arg0);
	}
	private void painLine(Graphics g){
		g.setColor(Color.white);
		g.drawLine(810, 0, 810, 600);
	}
	
	@Override
	protected void processKeyEvent(KeyEvent e) {
		super.processKeyEvent(e);
		if (e.getID() == KeyEvent.KEY_PRESSED)
			keyboard.processKeyEvent(e.getKeyCode(), true);
		else if (e.getID() == KeyEvent.KEY_RELEASED)
			keyboard.processKeyEvent(e.getKeyCode(), false);
	}

	public void start() {
		while(game.state != GameState.PASUE) { // Change back? change to "true" and remove GameState.java and remove the PAUSE from Game.java
			game.update(keyboard);
			try {
				Thread.sleep(1000 / FPS); //Throttle thread
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.repaint();
		}
		
		//Change back? Remove this section
		if(game.state == GameState.PASUE){
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
