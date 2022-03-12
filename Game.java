// © Copy Right "Nour"
import java.awt.*;
import java.util.*;

import javax.swing.JOptionPane;

public class Game {
	private static final int WIDTH_X = 800;
	private static final int HEIGHT_Y = 600;
	private int Score = 0;
	private int Lives = 1;
	private int time;
	private int counter = 0;

	private Window window;

	private boolean GameOver = false;

	private Ball ball;
	private Bat bat;

	private ArrayList<Bricks> bricks;


	private long startTime = System.currentTimeMillis();

	private String name;

	private Reset reset;

	private HighScore highscore;
	private LatestRun latestrun;

	public Game(GameBoard board) {
		highscore = new HighScore();
		latestrun = new LatestRun();
		
		ball = new Ball(WIDTH_X/2, HEIGHT_Y/2, 30, 30, Color.WHITE);
		bat = new Bat(WIDTH_X/2 - 150/2, HEIGHT_Y - 10, 150, 10, Color.gray);
		bricks = new ArrayList<Bricks>();
		reset = new Reset();
		reset.CreateNewGame(bricks);

		window = new Window(highscore, latestrun);
	}



	public void update(Keyboard keyboard) {
		if(GameOver == false){
			ball.update(keyboard);
			bat.update(keyboard);
	
			if(ball.BatCollision(bat)){
				ball.setYSpeed(-ball.getYSpeed());
			}
	
			for(int i = 0; i < bricks.size(); i++){
				if((ball.getY() > bricks.get(i).getY() - 30 && ball.getY() < bricks.get(i).getY() + 30) && (ball.getX() == bricks.get(i).getX() + 60 || ball.getX() + 30 == bricks.get(i).getX())){
					if(bricks.get(i).getColor() == Color.red){
						bricks.set(i, new Bricks(bricks.get(i).getX(), bricks.get(i).getY(), bricks.get(i).getWidth(), bricks.get(i).getHeight(), Color.green));
						ball.setXSpeed(-ball.getXSpeed());
						Score += 100;
					}else if(bricks.get(i).getColor() == Color.green){
						ball.setXSpeed(-ball.getXSpeed());
						bricks.remove(i);
						Score += 100;
					}
					
				} 
				else if(ball.BrickCollesion(bricks.get(i))){
					if(bricks.get(i).getColor() == Color.red){
						bricks.set(i, new Bricks(bricks.get(i).getX(), bricks.get(i).getY(), bricks.get(i).getWidth(), bricks.get(i).getHeight(), Color.green));
						ball.setYSpeed(-ball.getYSpeed());
						Score += 100;
					}else if(bricks.get(i).getColor() == Color.green){
						ball.setYSpeed(-ball.getYSpeed());
						bricks.remove(i);
						Score += 100;
					}
				}
			}
	
			if(ball.getY() >= HEIGHT_Y){
				ball.setX(WIDTH_X/2 - 15);
				ball.setY(HEIGHT_Y - 70);
				Lives --;
			}
		}
		if(GameOver == true && counter < 1){
			counter++;
			
			do{
				name = popup();
			}while(name.length() > 3);

			
			highscore.add(new ScoreContainer(name, Score - time));
			if(highscore.size() > 2){
				highscore.sort();
			}

			if(highscore.size() == 11){
				highscore.sort();
				highscore.remove(10);
			}

			if(latestrun.getSize() < 3){
				for(int i = latestrun.getSize() - 1; i>=0; i--){
					int temp = latestrun.getAtIndex(i);
					latestrun.remove(latestrun.getSize() - 1);
					latestrun.add(latestrun.getSize(), temp);	
				}
				latestrun.add(0, Score - time);

				
			}else{
				int temp = latestrun.getAtIndex(1);
				latestrun.remove(2);
				latestrun.add(2, temp);
				temp = latestrun.getAtIndex(0);
				latestrun.remove(1);
				latestrun.add(1, temp);
				latestrun.remove(0);
				latestrun.add(0, Score - time);
			}
		}
		if(keyboard.isKeyDown(Key.Space) && GameOver == true){
			GameOver = false;
			counter = 0;
			Lives = 1;
			Score = 0;
			reset.reset(bricks);
			reset.CreateNewGame(bricks);
			startTime = System.currentTimeMillis();
		}
		
	}

	public String popup(){
		String name = JOptionPane.showInputDialog("Please write your name (MAX 3 characters): ");
		return name;
	}

	public void draw(Graphics2D graphics) {
		if(Lives > 0 && !bricks.isEmpty() && GameOver == false){
			ball.draw(graphics);
			bat.draw(graphics);
			for(Bricks e: bricks){
				e.draw(graphics);
			}
			long elapsedTime = System.currentTimeMillis() - startTime;
			long elapsedSeconds = elapsedTime / 1000;
			long elapsedDisplay = elapsedSeconds % 60;
			long elapsedMinutes = elapsedSeconds / 60;
			graphics.setColor(Color.white);
			drawString(graphics, "Ink Free", "Timer: ", 820, 240, 38);
			drawString(graphics, "Ink Free", String.valueOf(elapsedMinutes) + ":" + String.valueOf(elapsedDisplay), 950, 240, 38);
			time = (int)elapsedSeconds;
		}else if(Lives <= 0 && !bricks.isEmpty()){
			graphics.setColor(Color.red);
			drawString(graphics, "Ink Free", "GAME OVER!", 180, 350, 80);
			GameOver = true;
		}else if(Lives > 0 && bricks.isEmpty()){
			graphics.setColor(Color.red);
			drawString(graphics, "Ink Free", "You WON! Score: " + String.valueOf(Score), 200, 300, 80);
			GameOver = true;
		}

		Bricks brickRed_tutorial = new Bricks(820, 10, 60, 30, Color.red);
		Bricks brickGreen_tutorial = new Bricks(820, 60, 60, 30, Color.green);
		

		brickRed_tutorial.draw(graphics);
		drawString(graphics, "Ink Free", "200 Points", 890, 38, 38);
		brickGreen_tutorial.draw(graphics);
		drawString(graphics, "Ink Free", "100 Points", 890, 88, 38);
		graphics.setColor(Color.blue);
		drawString(graphics, "Ink Free", "Score: " + String.valueOf(Score), 820, 140, 38);
		drawString(graphics, "Ink Free", "Lives: " + String.valueOf(Lives), 820, 190, 38);		
	}

	private void drawString(Graphics g, String _font, String text, int x, int y, int size){
		Font font = new Font(_font, Font.BOLD, size);
		g.setFont(font);
		g.drawString(text, x, y);
	}

}


/*
1- Programmera
2- Klasser
3- Datastrukturer
	* Implement ADTer
4- Analys och desgin
5- Grafik
	* Swing

*/