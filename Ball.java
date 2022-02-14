import java.awt.Graphics2D;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.w3c.dom.css.Rect;

import java.awt.Color;
import java.awt.Rectangle;
import java.lang.reflect.WildcardType;

public class Ball extends Sprite {
    private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
    //private Bat bat;
    public int xSpeed;
    public int ySpeed;
    private Color color;
    //private Random random;
    //private List<BallState> states = Arrays.asList(BallState.TurningLeft, BallState.TurningRight, BallState.Falling, BallState.Jumping);
    public Ball(int x, int y, int width, int height, Color color){
        super(x, y, width, height, color);
        xSpeed = 2;
        ySpeed = -2;
    }
    @Override
    public void update(Keyboard keyboard) {
        move();
        EdgeChecker();
    
    }

    public void move(){
        setX(getX() + xSpeed);
        setY(getY() + ySpeed);
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(getColor());
        graphics.fillOval(getX(), getY(), getWidth(), getHeight());
    }

    public void EdgeChecker(){
        if(getX() >= WIDTH - getWidth() || getX() <= 0){
            xSpeed = -xSpeed;
        }
        if(getY() <= 0 || getY() >= 600){
            ySpeed = -ySpeed;
        }
    }

    public boolean BrickCollesion(Bricks brick){
        Rectangle ballRec = new Rectangle(getX(), getY(), getWidth(), getHeight());
        Rectangle brickRec = new Rectangle(brick.getX(), brick.getY(), brick.getWidth(), getHeight());
        return ballRec.intersects(brickRec);
    }
    public boolean BatCollision(Bat bat){
        Rectangle ballRec = new Rectangle(getX(), getY(), getWidth(), getHeight());
        Rectangle batRec = new Rectangle(bat.getX(), bat.getY(), bat.getWidth(), bat.getHeight());
        return ballRec.intersects(batRec);
    }
    
}