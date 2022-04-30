// © Copy Right "Nour"
import java.awt.*;
import java.util.*;

import javax.swing.JOptionPane;

public class Game {
	private static final int WIDTH_X = 800;
	private static final int HEIGHT_Y = 600;
	private int Score = 0;
	private int Lives = 3;
	private int time;
	private int counter = 0;
	private Window window;
	private boolean GameOver = false;
	private Ball ball;
	private Bat bat;
	private ArrayList<Bricks> bricks;
	private long startTime = System.currentTimeMillis();// Variabeln innehåller nuvarande tiden
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
		reset.CreateNewGame(bricks);//Den anropas för att skapa spelet för första gången

		window = new Window(highscore, latestrun);
	}



	public void update(Keyboard keyboard) {
		//--------------Spelet är på--------------------------------------//
		if(GameOver == false){
			ball.update(keyboard);
			bat.update(keyboard);
	
			if(ball.BatCollision(bat)){
				ball.setYSpeed(-ball.getYSpeed());
			}
	
			for(int i = 0; i < bricks.size(); i++){
				/*
				(ball.getY() > bricks.get(i).getY() && ball.getY() < bricks.get(i).getY() + 30):
				-Bollens "y" större än Bricks "y" ---> Om bollen är under brickans höjd
				-Bollens "y" mindre än Bricks "y + 30" ---> Om bollen är över brickans botten
				
				(ball.getX() == bricks.get(i).getX() + 60 || ball.getX() + 30 == bricks.get(i).getX())):
				-Bollens "x" lika med Bricks "x + 60" ---> Om bollen på vänster sida träffar brickan från höger sida
				-Bollen "x + 30" lika med Bricks "x" ---> Om bollen på höger sida träffar brickan från vänster sida
				*/
				if((ball.getY() > bricks.get(i).getY() && ball.getY() < bricks.get(i).getY() + 30) && (ball.getX() == bricks.get(i).getX() + 60 || ball.getX() + 30 == bricks.get(i).getX())){
					if(bricks.get(i).getColor() == Color.red){
						bricks.set(i, new Bricks(bricks.get(i).getX(), bricks.get(i).getY(), bricks.get(i).getWidth(), bricks.get(i).getHeight(), Color.green));//Brickan ersätts med annan bricka som har samma storlek men färgen Grön
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
	
			if(ball.getY() >= HEIGHT_Y){// Om bollen hamnar under skärmen så återuppstår bollen men Lives minskas
				ball.setX(WIDTH_X/2 - 15);
				ball.setY(HEIGHT_Y - 70);
				Lives --;
			}
		}
		//----------------------------------------------------------------------//

		//----------------------Spelaren har förlorat---------------------------//
		if(GameOver == true && counter < 1){
			counter++;//Så att inte if satsen körs flera gånger
			
			do{
				name = popup();
				if(name == null){//Ifall användaren trycker på "cancel"
					break;
				}
			}while(name.length() > 3);//Loopar om användaren skriver in mer än 3 tecken

			if(name != null){//Om spelaren inte tryckte på "cancel"
				if(name.length() > 0){//Om längden på namn är större än 0. Spelaren kan trycka "OK" knappen utan problem, inget läggs till listan
					highscore.add(new ScoreContainer(name, Score - time));
				}
			}
			
			//-----------------------------HighScore--------------------------------------------------------//
			//Sorterar listan om den innehåller mer än 1 element
			if(highscore.getSize() > 1){
				highscore.sort();
			}
			//Om storleken på listan är mer än 10 element så sorteras listan först sedan tas bort sista elementet i listan
			if(highscore.getSize() == 11){
				highscore.sort();
				highscore.remove(10);
			}
			
			//------------------------------------LatestRun------------------------------------------------//
			//Om listan har mindre än 3 element så flyttas alla element ner ett steg och sist läggs resultaten i första platsen längst upp. 
			if(latestrun.getSize() < 3){
				for(int i = latestrun.getSize() - 1; i>=0; i--){
					int temp = latestrun.getAtIndex(i);
					latestrun.remove(latestrun.getSize() - 1);
					latestrun.add(latestrun.getSize(), temp);	
				}
				latestrun.add(0, Score - time);

			//Om listan innehåller 3 element eller mer, så flyttas elementen på platsen 1 och 2 ner ett steg och sist läggs poängen på platsen 0
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
			//---------------------------------------------------------------------------------------------//
		}

		//--------------------Spelet har pausat och väntar på Space knappen----------------------//
		if(keyboard.isKeyDown(Key.Space) && GameOver == true){
			GameOver = false;//Spelet börjar igen
			counter = 0;
			Lives = 3;
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
		//Om spelet är igång, Lives större än 0 och listan är inte tom och GameOver är "false"
		if(Lives > 0 && !bricks.isEmpty() && GameOver == false){
			ball.draw(graphics);
			bat.draw(graphics);
			for(Bricks e: bricks){
				e.draw(graphics);
			}
			//Beräkning av tiden i minuter och sekunder genom att ta tiden nu minus tiden när spelet började
			long elapsedTime = System.currentTimeMillis() - startTime;
			long elapsedSeconds = elapsedTime / 1000;
			long elapsedDisplay = elapsedSeconds % 60;
			long elapsedMinutes = elapsedSeconds / 60;
			graphics.setColor(Color.white);
			drawString(graphics, "Ink Free", "Timer: ", 820, 240, 38);
			drawString(graphics, "Ink Free", String.valueOf(elapsedMinutes) + ":" + String.valueOf(elapsedDisplay), 950, 240, 38);//Tiden skrivs ut på skärmen
			time = (int)elapsedSeconds;
		
		//Om spelaren förlorar
		}else if(Lives <= 0 && !bricks.isEmpty()){
			graphics.setColor(Color.red);
			drawString(graphics, "Ink Free", "GAME OVER!", 180, 350, 80);
			drawString(graphics, "Times Roman", "Press SPACE to restart", 170, 420, 40);
			GameOver = true;//GameOver blir "true" = spelet pausas
		//Om spelaren vinner
		}else if(Lives > 0 && bricks.isEmpty()){
			graphics.setColor(Color.red);
			drawString(graphics, "Ink Free", "You WON! Score: " + String.valueOf(Score - time), 100, 300, 80);
			drawString(graphics, "Times Roman", "Press SPACE to restart", 170, 420, 40);
			GameOver = true;//GameOver blir "true" = spelet pausas
		}

		//Referens för brickor finns alltid till höger i spelfönstret
		Bricks brickRed_tutorial = new Bricks(820, 10, 60, 30, Color.red);
		Bricks brickGreen_tutorial = new Bricks(820, 60, 60, 30, Color.green);
		

		brickRed_tutorial.draw(graphics);
		drawString(graphics, "Ink Free", "200 Points", 890, 38, 38);
		brickGreen_tutorial.draw(graphics);
		drawString(graphics, "Ink Free", "100 Points", 890, 88, 38);
		graphics.setColor(Color.blue);
		drawString(graphics, "Ink Free", "Score: " + String.valueOf(Score - time), 820, 140, 38);//Poängen finns alltid till höger i spelfönstret
		drawString(graphics, "Ink Free", "Lives: " + String.valueOf(Lives), 820, 190, 38);//Antal bollar kvar finns alltid till höger i spelfönstret
	}

	private void drawString(Graphics g, String _font, String text, int x, int y, int size){
		Font font = new Font(_font, Font.BOLD, size);
		g.setFont(font);
		g.drawString(text, x, y);
	}

}