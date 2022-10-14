package GUI;

import Entity.Account;
import DAO.AccountChecker;
import DAO.AccountList;
import DAO.DealerList;
import DAO.DeliveryList;
import Tools.MyTool;
import java.util.Scanner;

public class LogIn {

    public static final String ACCOUNT_FORMAT = "E\\d{3}";
    public static final String PASSWORD_FORMAT = "\\d{8}";
    private Account acc = null;

    public LogIn(Account acc) {
        this.acc = acc;
    }

    //implement it 
    public static Account inputAccount() {
        boolean check ;
        String name = "";
        String pwd = "";
        String role = "";
        do {
            check = true;
            name = MyTool.readPattern("Your account name ", ACCOUNT_FORMAT);
            
            pwd = MyTool.readPattern("Your password ", PASSWORD_FORMAT);
            System.out.println("Your role: " );
            role = MyTool.SC.nextLine().trim();
            if (role.equalsIgnoreCase("BOSS")
                    || role.equalsIgnoreCase("ACC-1")
                    || role.equalsIgnoreCase("ACC-2")) {
                check = false;
            }
        } while (check);
        Account acc = new Account(name, pwd, role);
        return acc;
    }

    public Account getAcc() {
        return acc;
    }

    public static void main(String[] args) {
        Account acc = null;
        boolean cont ;
        boolean valid = false;
        do {
            cont = false;
            AccountChecker accChk = new AccountChecker();
            acc = inputAccount();
            valid = accChk.check(acc);
            if (!valid) {
                cont = MyTool.readBool("Invalid Account - Try Again?");
            }
            if (!valid && !cont) {
                System.exit(0);
            }
        } while (cont);
        LogIn loginObj = new LogIn(acc);
        if (acc.getRole().equalsIgnoreCase("ACC-1")) {
            String[] options = {"Add new dealer", "Search a deadler",
                "Remove a dealer", "Update a dealer",
                "Print all dealers", "Print continuing dealers",
                "Print Un-Continuing dealers", "Write to file"
            };
            Menu mnu = new Menu(options);
            DealerList dList = new DealerList(loginObj);
            dList.intitWithFile();
            int choice = 0;
            do {
                choice = mnu.getChoice("Managing dealers");
                switch (choice) {
                    case 1:
                        dList.addDealer();
                        break;
                    case 2:
                        dList.searchDealer();
                        break;
                    case 3:
                        dList.removeDealer();
                        break;
                    case 4:
                        dList.updateDealer();
                        break;
                    case 5:
                        dList.printAllDealers();
                        break;
                    case 6:
                        dList.printContinuingDealers();
                        break;
                    case 7:
                        dList.printUnContinuingDealers();
                        break;
                    case 8:
                        dList.writeDealerToFile();
                        break;
                    default:
                        if (dList.isChanged()) {
                            boolean res = MyTool.readBool("Data changed. Write to file?");
                            if (res == true) {
                                dList.writeDealerToFile();
                            }
                        }
                }
            } while (choice > 0 && choice < mnu.size());
            System.out.println("Bye.");
        }
                if (acc.getRole().equalsIgnoreCase("BOSS")) {
            String[] options = {"Add new user", "Search a user",
                "Remove a user", "Update a user",
                "Print all dealers","Save to file"
            };
            Menu mnu = new Menu(options);
           AccountList accList = new AccountList(loginObj) ;
           accList.initiWithFile();
            int choice = 0;
            do {
                choice = mnu.getChoice("Managing user");
                switch (choice) {
                    case 1:
                        accList.addNewAccount();
                        break;
                    case 2:
                        accList.searchAccount();
                        break;
                    case 3:
                        accList.removeAccount();
                        break;
                    case 4:
                        accList.updateDealer();
                        break;
                    case 5:
                        accList.prinAllAcount();
                        break;
                    case 6:
                        accList.saveAcountToFile();
                        break;
                }
            } while (choice > 0 && choice <= mnu.size());
            System.out.println("Bye.");
        }
                        if (acc.getRole().equalsIgnoreCase("ACC-2")) {
            String[] options = {"Add new Delivery", "Search a Delivery",
                "Remove a Delivery", "Update a Delivery", 
                "Print all Delivery","Print Delivery from file","Save to file"
            };
            Menu mnu = new Menu(options);
            DeliveryList delList = new DeliveryList(loginObj);
           delList.initiWithFile();
            int choice = 0;
            do {
                choice = mnu.getChoice("Managing deliveries");
                switch (choice) {
                    case 1:
                        delList.createDelivery();
                        break;
                    case 2:
                        delList.searchDelivery();
                        break;
                    case 3:
                        delList.deleteDelivery();
                        break;
                    case 4:
                        delList.updateDelivery();
                        break;
                    case 5:
                        delList.printList();
                        break;
                    case 6:
                        delList.printFromFile();
                        break;
                    case 7:
                        delList.saveDelivery();
                        break;
                }
            } while (choice > 0 && choice <= mnu.size());
            System.out.println("Bye.");
        }
    }
    
}
