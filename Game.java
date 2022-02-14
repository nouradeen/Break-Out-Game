import java.awt.Color;
import java.awt.Graphics2D;
import java.lang.reflect.WildcardType;
import java.util.*;

public class Game {
	private static final int WIDTH_X = 800;
	private static final int HEIGHT_Y = 600;
	private static final int BRICK_NUM = 10;

	Ball ball;
	Bat bat;
	Bricks brick;

	public ArrayList<Bricks> bricks;

	Random random = new Random();

	public Game(GameBoard board) {
		ball = new Ball(WIDTH_X/2, HEIGHT_Y/2, 30, 30, Color.red);
		bat = new Bat(WIDTH_X/2 - 150/2, HEIGHT_Y - 10, 150, 10, Color.gray);
		brick = new Bricks(WIDTH_X/2 - 100, HEIGHT_Y - 450, 30, 30, Color.white);
		bricks = new ArrayList<Bricks>();
		// for(int i = 1; i <= BRICK_NUM; i++){
		// 	int X = random.nextInt(78);
		// 	int Y = random.nextInt(30);
		//    bricks.add(new Bricks(10 * X, 10 *Y, 30, 30));
	    // }
		
		//bricks.add(new Bricks(WIDTH_X/2 - 15, 200, 30, 30, Color.gray));
	}

	public void update(Keyboard keyboard) {
		
		ball.update(keyboard);
		bat.update(keyboard);

		if(ball.BatCollision(bat)){
			ball.ySpeed = -ball.ySpeed;
		}


		
		// if(ball.getY() > HEIGHT_Y - 10){
		// 	ball.setX(WIDTH_X/2);
		// 	ball.setY(HEIGHT_Y/2);
		// }
	}

	public void draw(Graphics2D graphics) {
		ball.draw(graphics);
		bat.draw(graphics);
		brick.draw(graphics);
		// for(Bricks e: bricks){
		// 	e.draw(graphics);
		// }
		
	}
}
