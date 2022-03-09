import javax.swing.DefaultBoundedRangeModel;
import javax.swing.DefaultListModel;

public class LatestRun {
    private DefaultListModel dlm;
    private DefaultListModel numbers;
    public LatestRun(){
        dlm = new DefaultListModel<Integer>();
        
    }
    public void add(int index, int score){
        dlm.add(index, score);
    }

    public void remove(int index){
        dlm.remove(index);
    }

    public int getAtIndex(int index){
        return (int) dlm.get(index);
    }
    public DefaultListModel getDlm(){
        return dlm;
    }
    public int getSize(){
        return dlm.getSize();
    }

    public DefaultListModel getNumbers(){
        numbers = new DefaultListModel<String>();
        for(int i = 0; i < 3; i++){
            numbers.add(i, (i+1) + ". ");
        }
        return numbers;
    }


}
