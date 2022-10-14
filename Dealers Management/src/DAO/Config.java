package DAO;

import Tools.MyTool;
import java.util.List;

public class Config {

    private static final String CONFIG_FILE = "DealerData/config.txt";
    private String accountFile;
    private String dealerFile;
    private String deliveryFile;

    public Config() {
        readData();
    }

    private void readData() {
        List<String> lines = MyTool.readLinesFromeFile(CONFIG_FILE);
        for (String line : lines) {
            line = line.toUpperCase();
            String[] parts = line.split(":");
            if (line.indexOf("ACCOUN") >= 0) 
                accountFile = "DealerData/" + parts[1].trim();
            
            else if (line.indexOf("DEAL") >= 0)
                dealerFile = "DealerData/" + parts[1].trim();
            
            else if (line.indexOf("DELIVER") >= 0)
                deliveryFile = "DealerData/" + parts[1].trim();
        }
        
    }

    public String getAccountFile() {
        return accountFile;
    }

    public void setAccountFile(String accountFile) {
        this.accountFile = accountFile;
    }

    public String getDealerFile() {
        return dealerFile;
    }

    public void setDealerFile(String dealerFile) {
        this.dealerFile = dealerFile;
    }

    public String getDeliveryFile() {
        return deliveryFile;
    }

    public void setDeliveryFile(String deliveryFile) {
        this.deliveryFile = deliveryFile;
    }
}
