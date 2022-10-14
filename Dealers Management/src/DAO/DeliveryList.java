package DAO;


import Tools.MyTool;
import Entity.Delivery;
import Entity.Account;
import GUI.LogIn;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class DeliveryList extends Delivery {
    public static final String ID_FORMAT = "DEL[0-9]{1,3}";
    public static final String NAME_FORMAT = ".*[a-zA-Z]+.*";
    private String nameFile = "";
    LogIn loginObj = null;
    ArrayList<Delivery> deliveryList = new ArrayList();

    private Scanner sc = new Scanner(System.in);
    
    public DeliveryList() {
       deliveryList = readFile();
    }

    public DeliveryList(LogIn loginObj) {
        this.loginObj = loginObj;
    }
     public void loadDataFromFile(){
        for (String accountToFile : MyTool.readLinesFromeFile(nameFile)) {
            Delivery acc = new Delivery(accountToFile);
            deliveryList.add(acc);
        }
    }
    public void initiWithFile(){
        Config conFigFile = new Config();
        nameFile = conFigFile.getDeliveryFile(); 
        
    }
    public void createDelivery() {
        String id;
        String name;
        double unitPrice;
        int quantity;
        String status;
        MyTool.SC.nextLine();
        do {
            id = MyTool.readPattern("Enter the ID of Delievery ",ID_FORMAT );
        } while (checkDuplicateID(id) == false);

        do {
            name = MyTool.readPattern("Enter the Name of Delievery ",NAME_FORMAT);
        } while (checkDuplicateName(name) == false);

        unitPrice = MyTool.getDouble(0, 10000);
        quantity = MyTool.getInt(0, 1000);

        do {
            System.out.println("Enter Status of Delivery(Available or Not Available): ");
            status = sc.nextLine();
        } while (!status.equalsIgnoreCase("Available") && !status.equalsIgnoreCase("Not Available"));

        Delivery delivery = new Delivery(id, name, unitPrice, quantity, status);
        deliveryList.add(delivery);
        System.out.println("Added successful");
    }

    
    public void checkExistDelivery() {
        String name;
      name = MyTool.readPattern("Enter the Name of Delievery ",NAME_FORMAT);
        for (Delivery product : readFile()) {
            if (product.getName().equals(name)) {
                System.out.println("Exist Product");
                return;
            }
        }
        System.out.println("Have no any product");
    }

   
    public void searchDelivery() {
        String nameCheck;
        nameCheck = MyTool.readPattern("Enter the Name of Delievery ",NAME_FORMAT);

        for (Delivery o : deliveryList) {
            if (o.getName().equals(nameCheck)) {
                System.out.println(o);
                return;
            }
        }
        System.out.println("No Product Found!");
    }

    
    public void updateDelivery() {
        String checkID = MyTool.readPattern("Enter the ID of Delievery ",ID_FORMAT );
        for (Delivery  o : deliveryList) {
            if (o.getId().equals(checkID)) {

                String name;
                double unitPrice;
                int quantity;
                String status;

                do {
                    name = MyTool.setName("[a-zA-Z]{5,}");
                } while (!checkDuplicateName(name) == true && !name.isEmpty());
                if (!name.isEmpty()) {
                    o.setName(name);
                }

                unitPrice = MyTool.setUnitPrice(0, 10000);
                if (unitPrice > 0) {
                    o.setUnitPrice(unitPrice);
                }

                quantity = MyTool.setQuantity(0, 1000);
                if (quantity > 0) {
                    o.setQuantity(quantity);
                }
                
                do {
                    System.out.println("Enter Status of Delivery(Available or Not Available): ");
                    status = sc.nextLine();                    
                } while (!status.equalsIgnoreCase("Available") && !status.equalsIgnoreCase("Not Available") && !status.isEmpty());
                if (!status.isEmpty()) {
                    o.setStatus(status);
                }
                return;
            }
        }
        System.out.println("Delivery name does not exist");
    }

    
    public void deleteDelivery() {
        System.out.println("Enter the ID of Delivery that you want to Delete ");
        String idCheck = sc.nextLine();

        for (Delivery o : deliveryList) {
            if (o.getId().equals(idCheck)) {
                deliveryList.remove(o);
                System.out.println("delete successful");
                return;
            }
        }
        System.out.println("Productname does not exist");
    }

  
    public void saveDelivery() {
        try {
            FileWriter createFile = new FileWriter(nameFile);
            BufferedWriter saveFile = new BufferedWriter(createFile);
            for (Delivery delivery : deliveryList) {
                saveFile.write(delivery.toString());
                saveFile.newLine();
            }
            saveFile.close();
            createFile.close();
        } catch (Exception e) {
            System.out.println("Save file failed!");
        }
        System.out.println("Save successful!");
    }

    
    public void printList() {       
        for (Delivery delivery : deliveryList) {
            System.out.println(delivery);
        }
    }

    
    public void printFromFile() {
        for (Delivery product : readFile()) {         
            System.out.println(product);
        }
    }

    public boolean checkDuplicateName(String nameCheck) {
        for (Delivery product : deliveryList) {
            if (product.getName().equals(nameCheck)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkDuplicateID(String IDCheck) {
        for (Delivery delivery : deliveryList) {
            if (delivery.getId().equals(IDCheck)) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Delivery> readFile() {
        ArrayList<Delivery> deliveryListFromFile = new ArrayList<>();
        String line = "";
        try {
            FileReader sr = new FileReader(nameFile);
            BufferedReader br = new BufferedReader(sr);

            while (true) {

                line = br.readLine();
                if (line == null) {
                    break;
                }
                String arr[] = line.split("\\s" + "\\|"+"\\|" +"\\s");
                String id = arr[0];
                String name = arr[1];
                double price = Double.parseDouble(arr[2]);
                int quantity = Integer.parseInt(arr[3]);
                String status = arr[4] ;
               deliveryListFromFile.add(new Delivery(id, name, price, quantity, status));

            }
        } catch (Exception e) {
        }

        Collections.sort(deliveryListFromFile, new Comparator<Delivery>() {
            @Override
            public int compare(Delivery o1, Delivery o2) {
                if (o1.getQuantity() == o2.getQuantity()) {
                    if (o1.getUnitPrice() - o2.getUnitPrice() > 0) {
                        return 1;
                    } else {
                        return -1;
                    }
                } else {
                    return o2.getQuantity() - o1.getQuantity();
                }
            }
        });
        return deliveryListFromFile;
    }
}
