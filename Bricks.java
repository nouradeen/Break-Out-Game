// © Copy Right "Nour"
import java.awt.Graphics2D;
import java.awt.Color;

public class Bricks extends Sprite {
    public Bricks(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    @Override
    public void update(Keyboard keyboard) {
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(getColor());
        graphics.fillRect(getX(), getY(), getWidth(), getHeight());
    }
    
}
