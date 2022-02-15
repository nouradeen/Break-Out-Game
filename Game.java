import java.awt.*;
import java.util.*;

public class Game {
	private static final int WIDTH_X = 800;
	private static final int HEIGHT_Y = 600;
	int Score = 0;
	int Lives = 3;
	public GameState state;

	Ball ball;
	Bat bat;
	Bricks brick;

	public ArrayList<Bricks> bricks;

	Random random = new Random();
	private Color[] colors = new Color[] {Color.red, Color.green};
	public Game(GameBoard board) {
		
		ball = new Ball(WIDTH_X/2, HEIGHT_Y/2, 30, 30, Color.WHITE);
		bat = new Bat(WIDTH_X/2 - 150/2, HEIGHT_Y - 10, 150, 10, Color.gray);
		bricks = new ArrayList<Bricks>();
		for(int i = 20; i <= 200; i+=50){
			for(int j = 10; j <= 800; j+=80){
				Color randomColor = colors[random.nextInt(2)];
				bricks.add(new Bricks(j, i, 60, 30, randomColor));
			}
		}
	}

	public void update(Keyboard keyboard) {
		
		ball.update(keyboard);
		bat.update(keyboard);

		if(ball.BatCollision(bat)){
			ball.ySpeed = -ball.ySpeed;
		}

		for(int i = 0; i < bricks.size(); i++){
			if((ball.getY() > bricks.get(i).getY() - 30 && ball.getY() < bricks.get(i).getY() + 30) && (ball.getX() == bricks.get(i).getX() + 60 || ball.getX() + 30 == bricks.get(i).getX())){
				if(bricks.get(i).getColor() == Color.red){
					bricks.set(i, new Bricks(bricks.get(i).getX(), bricks.get(i).getY(), bricks.get(i).getWidth(), bricks.get(i).getHeight(), Color.green));
					ball.xSpeed = -ball.xSpeed;
					Score += 100;
				}else if(bricks.get(i).getColor() == Color.green){
					ball.xSpeed = -ball.xSpeed;
					bricks.remove(i);
					Score += 100;
				}
				
			} 
			else if(ball.BrickCollesion(bricks.get(i))){
				if(bricks.get(i).getColor() == Color.red){
					bricks.set(i, new Bricks(bricks.get(i).getX(), bricks.get(i).getY(), bricks.get(i).getWidth(), bricks.get(i).getHeight(), Color.green));
					ball.ySpeed = -ball.ySpeed;
					Score += 100;
				}else if(bricks.get(i).getColor() == Color.green){
					ball.ySpeed = -ball.ySpeed;
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

	public void draw(Graphics2D graphics) {
		Bricks brickRed_tutorial = new Bricks(820, 10, 60, 30, Color.red);
		Bricks brickGreen_tutorial = new Bricks(820, 60, 60, 30, Color.green);
		ball.draw(graphics);
		bat.draw(graphics);
		for(Bricks e: bricks){
			e.draw(graphics);
		}

		brickRed_tutorial.draw(graphics);
		drawString(graphics, "Ink Free", "200 Points", 890, 38, 38);
		brickGreen_tutorial.draw(graphics);
		drawString(graphics, "Ink Free", "100 Points", 890, 88, 38);
		graphics.setColor(Color.blue);
		drawString(graphics, "Ink Free", "Score: " + String.valueOf(Score), 820, 140, 38);
		drawString(graphics, "Ink Free", "Lives: " + String.valueOf(Lives), 820, 190, 38);

		graphics.setColor(Color.red);
		if(Lives <= 0 && !bricks.isEmpty()){
			state = state.PASUE; //Changed the GameBoard class
			drawString(graphics, "Ink Free", "GAME OVER!", 180, 400, 80);
		} else if(Lives > 0 && bricks.isEmpty()){
			state = state.PASUE;
			drawString(graphics, "Ink Free", "You WON! Score: " + String.valueOf(Score), 200, 300, 80);
		}
		
	}

	private void drawString(Graphics g, String _font, String text, int x, int y, int size){
		Font font = new Font(_font, Font.BOLD, size);
		g.setFont(font);
		g.drawString(text, x, y);
	}

}
