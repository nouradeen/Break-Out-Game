import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private JList HighScoreList;
    private JFrame HighScoreWindow;

    private JList LatestRunList;
    private JFrame LatestRunWindow;

    public Window(HighScore highscore, LatestRun latestrun){
        //HighScore Window
        HighScoreWindow = new JFrame();
        HighScoreList = new JList(highscore.getDlm());
        HighScoreList.setFont(new Font("TimesRoman", 1, 40));
        HighScoreList.setForeground(Color.YELLOW);
        HighScoreList.setBackground(Color.BLACK);
        HighScoreList.setFocusable(false);
        HighScoreWindow.setLayout(new BorderLayout());        
        HighScoreWindow.add(HighScoreList);
        HighScoreWindow.setSize(350,640);
        HighScoreWindow.setLocation(1430,0);
        HighScoreWindow.setFocusable(false);
        HighScoreWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
        HighScoreWindow.setVisible(true);
        HighScoreWindow.setTitle("HighScore");

        //LatestRun Window
        LatestRunWindow = new JFrame();
        LatestRunList = new JList(latestrun.getDlm());
        LatestRunList.setFont(new Font("TimesRoman", 1, 40));
        LatestRunList.setForeground(Color.YELLOW);
        LatestRunList.setBackground(Color.BLACK);
        LatestRunList.setFocusable(false);
        LatestRunWindow.setLayout(new BorderLayout());
        LatestRunWindow.add(LatestRunList);
        LatestRunWindow.setSize(350,640);
        LatestRunWindow.setLocation(1100,0);
        LatestRunWindow.setFocusable(false);
        LatestRunWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
        LatestRunWindow.setVisible(true);
        LatestRunWindow.setTitle("Latest Run");
    }
}
