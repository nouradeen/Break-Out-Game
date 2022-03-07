import java.awt.*;
import java.util.*;

public class Reset {
    private Random random = new Random();
    private Color[] colors = new Color[] {Color.red, Color.green};

    public void CreateNewGame(ArrayList <Bricks> list){
        // bricks = new ArrayList<Bricks>();
        for(int i = 20; i <= 200; i+=50){
			for(int j = 10; j <= 800; j+=80){
				Color randomColor = colors[random.nextInt(2)];
				list.add(new Bricks(j, i, 60, 30, randomColor));
			}
		}
    }

    public void reset(ArrayList <Bricks> list){
        list.removeAll(list);
    }
}
