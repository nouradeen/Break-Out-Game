// © Copy Right "Nour"
import java.awt.Graphics2D;
import java.awt.Color;

public class Bat extends Sprite {
    public Bat(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    @Override
    public void update(Keyboard keyboard) {
        if(keyboard.isKeyDown(Key.Right)){
            if(getX() == 800 - getWidth()){ //Ifall slagtäet placerar sig längs till höger (BREDDEN - bredden på slagträet) så rör sig inte längre
                setX(getX());
            }else{// else så rör det sig med hastighet 5
                setX(getX() + 5);
            }
            
        }
        if(keyboard.isKeyDown(Key.Left)){ //Samma sak som förra if satsen men motsats riktning
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
