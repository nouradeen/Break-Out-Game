import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private JList HighScoreList;
    private JFrame HighScoreWindow;

    public Window(HighScore highscore){
        HighScoreWindow = new JFrame();
        HighScoreList = new JList(highscore.getDlm());

        HighScoreList.setFont(new Font("TimesRoman", 1, 40));

        HighScoreList.setForeground(Color.YELLOW);
        HighScoreList.setBackground(Color.BLACK);

        HighScoreList.setFocusable(false);
        
        HighScoreWindow.setLayout(new BorderLayout());
        HighScoreWindow.add(HighScoreList);

        HighScoreWindow.setSize(350,640);
        HighScoreWindow.setLocation(1100,0);
        HighScoreWindow.setFocusable(false);
        HighScoreWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
        HighScoreWindow.setVisible(true);
        HighScoreWindow.setTitle("HighScore");

    }
}
