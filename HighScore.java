import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultListModel;

public class HighScore {
    private DefaultListModel<ScoreContainer> dlm; 
    public HighScore(){
        dlm = new DefaultListModel<ScoreContainer>();
    }

    public void add(ScoreContainer score){
        dlm.add(dlm.size(), score);
    }

    public void sort(){
        List<ScoreContainer> list = new ArrayList<>();
        for(int i = 0; i < dlm.size(); i++){
            list.add((ScoreContainer)dlm.get(i));
        }
        Collections.sort(list, Collections.reverseOrder());
        dlm.removeAllElements();
        for(ScoreContainer o: list){
            dlm.addElement(o);
            //System.out.println(o.getName() + " " + o.getScore());
        }
    }
    public void remove(int index){
        dlm.removeElementAt(index);
    }
    public int size(){
        return dlm.size();
    }

    public DefaultListModel getDlm(){
        return dlm;
    }
    



    //Testing method, remove when finish
    public void printList(){
        System.out.println("Begin of PrinList(): \n-----------------------------------");
        for(int i = 0; i < dlm.size(); i++){
            System.out.println(dlm.get(i).getName() + " " + dlm.get(i).getScore());
        }
        System.out.println("-----------------------------------");
    }

    


}


/*
1- Contructor
2- Sort
3- Add 
*/