
package DAO;

import Tools.MyTool;
import Entity.Account;
import java.util.List;

public class AccountChecker {
    private String accFile;
    private static String SEPARATOR =  ",";
    public AccountChecker(){
        setupAccFile();
    }

    private void setupAccFile() {
       Config cR = new Config();
       accFile = cR.getAccountFile();
    }
    
    public boolean check(Account acc){
      List<String> lines = MyTool.readLinesFromeFile(accFile);
      
        for (String line : lines) {
            String[] parts = line.split("\\s" + "\\|"+"\\|" +"\\s");
            if (parts.length < 3) return false;
            if(parts[0].equalsIgnoreCase(acc.getAccName())&&
               parts[1].equals(acc.getPwd()) &&
               parts[2].equalsIgnoreCase(acc.getRole()))
                return true;
        }
        return false;
    }
}
