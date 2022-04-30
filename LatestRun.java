import javax.swing.DefaultListModel;

public class LatestRun {
    private DefaultListModel dlm;
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
        return (int) dlm.get(index);//Narrowing Casting - för att Integer är ett objekt
    }
    public DefaultListModel getDlm(){
        return dlm;
    }
    public int getSize(){
        return dlm.getSize();
    }


}
