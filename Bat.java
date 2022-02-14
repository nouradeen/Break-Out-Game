import java.awt.Graphics2D;
import java.awt.Color;

public class Bat extends Sprite {
    private Color color;
    private BatState state;
    public Bat(int x, int y, int width, int height, Color color) {
        super(x, y, width, height);
        this.color = color;
        state = BatState.Idle;
    }
    public Bat(int x, int y, int width, int height) {
        super(x, y, width, height);
        state = BatState.Idle;
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
        graphics.setColor(color);
        graphics.fillRect(getX(), getY(), getWidth(), getHeight());
    }
    
}
