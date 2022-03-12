import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private JList HighScoreList;
    private JFrame HighScoreWindow;

    private JList LatestRunList;
    private JFrame LatestRunWindow;

    private JList NumbersLatest;
    private String[] numbers = {"1. ", "2. ", "3. "};

    public Window(HighScore highscore, LatestRun latestrun){
        HighScoreWindow = new JFrame();
        HighScoreList = new JList(highscore.getDlm());
        HighScoreList.setFont(new Font("TimesRoman", 1, 40));
        HighScoreList.setForeground(Color.YELLOW);
        HighScoreList.setBackground(Color.BLACK);
        HighScoreList.setFocusable(false);
        HighScoreWindow.setLayout(new BorderLayout());        
        HighScoreWindow.add(HighScoreList);
        HighScoreWindow.setSize(200,640);
        HighScoreWindow.setLocation(1290,0);
        HighScoreWindow.setFocusable(false);
        HighScoreWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
        HighScoreWindow.setVisible(true);
        HighScoreWindow.setTitle("HighScore");

        LatestRunWindow = new JFrame();
        LatestRunList = new JList<Integer>(latestrun.getDlm());
        NumbersLatest = new JList<String>(numbers);
        LatestRunList.setFont(new Font("TimesRoman", 1, 40));
        NumbersLatest.setFont(new Font("TimesRoman", 1, 40));
        LatestRunList.setForeground(Color.YELLOW);
        LatestRunList.setBackground(Color.BLACK);
        NumbersLatest.setForeground(Color.YELLOW);
        NumbersLatest.setBackground(Color.BLACK);
        LatestRunList.setFocusable(false);
        NumbersLatest.setFocusable(false);
        LatestRunWindow.setLayout(new GridLayout(1,2));
        LatestRunWindow.add(NumbersLatest);
        LatestRunWindow.add(LatestRunList);
        LatestRunWindow.setSize(200,640);
        LatestRunWindow.setLocation(1100,0);
        LatestRunWindow.setFocusable(false);
        LatestRunWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
        LatestRunWindow.setVisible(true);
        LatestRunWindow.setTitle("Latest Run");
    }
}
