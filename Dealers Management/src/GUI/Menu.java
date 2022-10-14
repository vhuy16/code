
package GUI;

import Tools.MyTool;
import java.util.ArrayList;

public class Menu extends ArrayList<String>{
    public Menu(){
        super();
    }
    public Menu(String[] items){
        super();
        for (String item : items) {
            this.add(item);
        }
    }
    public int getChoice(String title){
        int choice;
        System.out.println(title);
        for (int i = 0; i < this.size(); i++) {
            System.out.println((i+1)+" "+this.get(i));
        }
        System.out.println("Enter your choice: ");
        choice = MyTool.SC.nextInt();
        return choice;
    }
}
