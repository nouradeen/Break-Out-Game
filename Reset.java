import java.awt.*;
import java.util.*;

public class Reset {
    private Random random = new Random();
    private Color[] colors = new Color[] {Color.red, Color.green};

    public void CreateNewGame(ArrayList <Bricks> list){
        for(int i = 80; i <= 260; i+=50){//x positionen
			for(int j = 10; j <= 800; j+=80){//y positionen
				Color randomColor = colors[random.nextInt(2)];
				list.add(new Bricks(j, i, 60, 30, randomColor));//skapar nya block med i och j och randomColor med samma storlek på alla
			}
		}
    }

    public void reset(ArrayList <Bricks> list){
        list.removeAll(list);
    }
}
