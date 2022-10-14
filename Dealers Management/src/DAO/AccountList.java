package DAO;

import Tools.MyTool;
import Entity.Account;
import GUI.LogIn;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;

public class AccountList extends ArrayList<Account> {
    String nameFile = "";
    LogIn loginObj = null;
    public static final String ACCOUNT_FORMAT = "E\\d{3}";
    public static final String PASSWORD_FORMAT = "\\d{8}";

    public AccountList() {
     
    }

    public AccountList(LogIn loginObj) {
        this.loginObj = loginObj;
    }
    public void loadDataFromFile(){
        for (String accountToFile : MyTool.readLinesFromeFile(nameFile)) {
            Account acc = new Account(accountToFile);
            this.add(acc);
        }
    }
    public void initiWithFile(){
        Config conFigFile = new Config();
        nameFile = conFigFile.getAccountFile(); //DataDealer/account.txt        
    }
    public void addNewAccount() {
        String accName;
        String pwd;
        String role;
        MyTool.SC.nextLine();
        accName = MyTool.readPattern("Enter account", ACCOUNT_FORMAT);
        pwd = MyTool.readPattern("Enter the password", PASSWORD_FORMAT);
        role = MyTool.readNonBlank("Your role ");
        
        this.add(new Account(accName, pwd, role));
        System.out.println("Account has been added!");
    }
    public void searchAccount(){
        String accCheck = MyTool.readPattern("Enter account name you want to search", ACCOUNT_FORMAT);
        for (Account acc : this) {
            if (acc.getAccName().equalsIgnoreCase(accCheck)) {
                System.out.println(acc);
            } else {
                System.out.println("Not found the account");
            }

        }
    }
    public void removeAccount() {
        String accCheck = MyTool.readPattern("Enter account name you want to remove", ACCOUNT_FORMAT);
        for (Account acc : this) {
            if (acc.getAccName().equalsIgnoreCase(accCheck)) {
                this.remove(acc);
            } else {
                System.out.println("Not found the account");
            }
        }
    }

    public void updateDealer() {
        String accCheck = MyTool.readPattern("Enter account name you want to remove", ACCOUNT_FORMAT);
        for (Account acc : this) {
            if (acc.getAccName().equalsIgnoreCase(accCheck)) {
                String newRole = MyTool.readNonBlank("Enter the new role ");
                if (!newRole.isEmpty()) {
                    acc.setRole(newRole);
                }
            } else {
                System.out.println("Not found the account");
            }
        }
    }

    public void prinAllAcount() {
        for (String acc : MyTool.readLinesFromeFile(nameFile)) {
            System.out.println(acc);
        }
    }

    public void saveAcountToFile() {
        MyTool.writeFile(nameFile, this);
        System.out.println("Saved to file!");
    }
}
