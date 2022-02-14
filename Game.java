import java.awt.Color;
import java.awt.Graphics2D;
import java.lang.reflect.WildcardType;
import java.util.*;

public class Game {
	private static final int WIDTH_X = 800;
	private static final int HEIGHT_Y = 600;
	//private static final int BRICK_NUM = 10;

	Ball ball;
	Bat bat;
	Bricks brick;

	public ArrayList<Bricks> bricks;

	Random random = new Random();

	public Game(GameBoard board) {
		ball = new Ball(WIDTH_X/2, HEIGHT_Y/2, 30, 30, Color.WHITE);
		bat = new Bat(WIDTH_X/2 - 150/2, HEIGHT_Y - 10, 150, 10, Color.gray);
		bricks = new ArrayList<Bricks>();
		for(int i = 20; i <= 200; i+=50){
			for(int j = 10; j <= 800; j+=80){
				bricks.add(new Bricks(j, i, 60, 30, Color.red));
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
			if((ball.getY() > bricks.get(i).getY() - 30 && ball.getY() < bricks.get(i).getY() + 30) && (ball.getX() == bricks.get(i).getX() + 60 || ball.getX() + 60 == bricks.get(i).getX())){
				ball.xSpeed = -ball.xSpeed;
				bricks.remove(i);
			} 
			else if(ball.BrickCollesion(bricks.get(i))){
				ball.ySpeed = -ball.ySpeed;
				bricks.remove(i);
			}
		}


		
		// if(ball.getY() > HEIGHT_Y - 10){
		// 	ball.setX(WIDTH_X/2);
		// 	ball.setY(HEIGHT_Y/2);
		// }
	}

	public void draw(Graphics2D graphics) {
		ball.draw(graphics);
		bat.draw(graphics);
		//brick.draw(graphics);
		for(Bricks e: bricks){
			e.draw(graphics);
		}
		
	}
}
