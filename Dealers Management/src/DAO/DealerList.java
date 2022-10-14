package DAO;

import Tools.MyTool;
import Entity.Dealer;
import GUI.LogIn;
import java.util.ArrayList;
import java.util.Collection;

public class DealerList extends ArrayList<Dealer> {

    LogIn loginObj = null;
    private static final String PHONEPATTERN = "\\d{9}|\\d{11}";
    private String dataFile = "";
    boolean changed = false;

    public DealerList() {
    }

    //implement it 
    public DealerList(LogIn LoginObj) {
        this.loginObj = LoginObj;
    }
    //implement it 

    private void loadDealerFromFile() {
        for (String data : MyTool.readLinesFromeFile(dataFile)) {
            Dealer dealerFromFile = new Dealer(data);
            this.add(dealerFromFile);
        }

    }

    public void intitWithFile() {
        Config cR = new Config();
        dataFile = cR.getDealerFile();
        loadDealerFromFile();
    }
    //implement it 

    public DealerList getContinuingList() {
        DealerList dealer = new DealerList();
        for (Dealer obj : this) {
            if (obj.isContinuing()) {
                dealer.add(obj);
            }
        }
        return dealer;
    }

    //implement it    
    public DealerList getUnContinuingList() {
        DealerList dealer = new DealerList();
        for (Dealer obj : this) {
            if (obj.isContinuing() == false) {
                dealer.add(obj);
            }
        }
        return dealer;
    }
    //implement it 

    private int searchDealer(String ID) {

        for (int i = 0; i < DealerList.super.size(); i++) {
            if (this.get(i).getID().equalsIgnoreCase(ID)) {
                return i;
            }
        }
        return -1;
    }
    //implement it 

    public void searchDealer() {
        String ID = MyTool.readNonBlank("Enter the ID's Dealer you want to search");
        int pos = searchDealer(ID);
        if (pos < 0) {
            System.out.println("NOT FOUND!");
        } else {
            System.out.println(this.get(pos));
        }
    }

    public void addDealer() {
        String ID;
        String name;
        String addr;
        String phone;
        boolean continuing;
        int pos;
        do {
            MyTool.SC.nextLine();
            ID = MyTool.readPattern("ID of new dealer", Dealer.ID_FORMAT);
            ID = ID.toUpperCase();
            pos = searchDealer(ID);
            if (pos >= 0) {
                System.out.println("ID is duplicated!");
            }
        } while (pos >= 0);
        name = MyTool.readNonBlank("Name of new Dealer: ").toUpperCase();
        addr = MyTool.readNonBlank("Address of new dealer: ");
        phone = MyTool.readPattern("Phone number: ", Dealer.PHONE_FORMAT);
        continuing = true;
        this.add(new Dealer(ID, name, addr, phone, continuing));
        System.out.println("New dealer has been added.");
        changed = true;
    }
    //implement it 

    public void removeDealer() {
          String ID = MyTool.readNonBlank("Enter the ID's Dealer you want to delete");
          int pos = searchDealer(ID);
        if (pos < 0) {
            System.out.println("NOT FOUND!");
        } else {
            this.remove(pos);
        }
    }

    public void updateDealer() {
        System.out.println("Dealer's ID needs updating: ");
        String ID = MyTool.SC.nextLine();
        int pos = searchDealer(ID);
        if (pos < 0) {
            System.out.println("Dealer " + ID + " not found!");

        } else {
            Dealer d = this.get(pos);
            String newName = "";
            System.out.println("new name, ENTER for omitting: ");
            newName = MyTool.SC.nextLine().trim().toUpperCase();
            if (!newName.isEmpty()) {
                d.setName(newName);
                changed = true;
            }
            String newAddr = "";
            System.out.println("new address, ENTER for omitting: ");
            newAddr = MyTool.SC.nextLine().trim();
            if (!newAddr.isEmpty()) {
                d.setAddr(newAddr);
                changed = true;
            }
            String newPhone ="";
           newPhone = MyTool.SC.nextLine().trim();
            if (!newPhone.isEmpty()) {
                d.setPhone(newPhone);
                changed = true;
            }
        }
    }

    public void printAllDealers() {
        if (this.isEmpty()) {
            System.out.println("Empty List!");
        } else {
            System.out.println(this);
        }
    }

    public void printContinuingDealers() {
        this.getContinuingList().printAllDealers();
    }

    public void printUnContinuingDealers() {
        this.getUnContinuingList().printAllDealers();
    }

    public void writeDealerToFile() {
        if (changed) {
            MyTool.writeFile(dataFile, this);
            changed = false;
        }
    }
    //implement it 

    public boolean isChanged() {
        return changed;
    }
    //implement it 

    public void setChanged(boolean changed) {
        this.changed = changed;
    }
}
