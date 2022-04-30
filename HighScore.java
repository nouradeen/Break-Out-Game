import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultListModel;

public class HighScore {
    private DefaultListModel<ScoreContainer> dlm; 
    public HighScore(){
        dlm = new DefaultListModel<ScoreContainer>();
    }

    public void add(ScoreContainer score){
        dlm.add(dlm.getSize(), score);//dlm.getSize() är den alltid sista platsen i listan
    }

    public void sort(){
        List<ScoreContainer> list = new ArrayList<>();// ArrayList skapas
        for(int i = 0; i < dlm.getSize(); i++){// Alla element från dlm listan läggs i list
            list.add((ScoreContainer)dlm.get(i));
        }
        Collections.sort(list, Collections.reverseOrder());//list sorteras
        dlm.removeAllElements();//Alla element från dlm tas bort
        for(ScoreContainer o: list){//Nya sorterade element från list läggs tillbaks till dlm
            dlm.addElement(o);
        }
    }
    public void remove(int index){
        dlm.removeElementAt(index);
    }

    public int getSize(){
        return dlm.getSize();
    }

    public DefaultListModel getDlm(){
        return dlm;
    }

}
