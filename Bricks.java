import java.awt.Graphics2D;
import java.awt.Color;

public class Bricks extends Sprite {
    private Color color;
    public Bricks(int x, int y, int width, int height, Color color) {
        super(x, y, width, height);
        this.color = color;
    }
    public Bricks(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void update(Keyboard keyboard) {
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(color);
        graphics.fillRect(getX(), getY(), getWidth(), getHeight());
    }
    
}
