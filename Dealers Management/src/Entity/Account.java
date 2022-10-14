
package Entity;

import Tools.MyTool;


public class Account {
    public static final String SEPERATOR = " || ";
    private String accName;
    private String pwd;
    private String role;

  

    public Account(String accName, String pwd, String role) {
        this.accName = accName;
        this.pwd = pwd;
        this.role = role;
    }
     public Account(String line){
        String[] parts = line.split("\\s" + "\\|"+"\\|" +"\\s");  
        accName = parts[0].trim();
        pwd = parts[1].trim();
        role = parts[2].trim();
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
      @Override
    public String toString() {
        return  accName+ SEPERATOR + pwd + SEPERATOR + role ;
    }
}
