import java.awt.Graphics2D;
import java.awt.Color;

public class Bat extends Sprite {
    public Bat(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }
    public Bat(int x, int y, int width, int height) {
        super(x, y, width, height, Color.white);
    }

    @Override
    public void update(Keyboard keyboard) {
        if(keyboard.isKeyDown(Key.Right)){
            if(getX() == 800 - getWidth()){
                setX(getX());
            }else{
                setX(getX() + 5);
            }
            
        }
        if(keyboard.isKeyDown(Key.Left)){
            if(getX() == 0){
                setX(getX());
            }else{
                setX(getX() - 5);
            }
        }
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(getColor());
        graphics.fillRect(getX(), getY(), getWidth(), getHeight());
    }
    
}
