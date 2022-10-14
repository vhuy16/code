package Tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MyTool {

    public static final Scanner SC = new Scanner(System.in);

    public static boolean validStr(String str, String regEx) {
        if (str.matches(regEx)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validPassword(String str, int minLen) {
        if (str.length() < minLen) {
            return false;
        }
        return str.matches(".*[a-zA-Z]+.")
                && str.matches(".*[\\d]+.*")
                && str.matches(".*[\\W]+.*");
    }

    public static Date parseDate(String dateStr, String dateFormat) {
        SimpleDateFormat dF = (SimpleDateFormat) SimpleDateFormat.getInstance();
        dF.applyPattern(dateFormat);
        try {
            long t = dF.parse(dateStr).getTime();
            return new Date(t);

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static String dataToStr(Date date, String dateFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        String strDate = formatter.format(date);
        return strDate;
    }

    public static boolean parseBool(String boolStr) {
        char c = boolStr.trim().toUpperCase().charAt(0);
        return (c == '1' || c == 'Y' || c == 'T');
    }

    public static String readNonBlank(String message) {
        String input = "";
        do {
            System.out.println(message + ": ");
            input = SC.nextLine().trim();

        } while (input.isEmpty());
        return input;
    }

    public static String readPattern(String message, String pattern) {
        String input = "";
        boolean valid;
        do {

            System.out.println(message + ": ");
            input = SC.nextLine().trim();
            valid = validStr(input, pattern);
        } while (!valid);
        return input;
    }

    public static boolean readBool(String message) {
        String input;
        System.out.println(message + "[1/0-Y/N-T/F]: ");
        input = SC.nextLine().trim();
        if (input.isEmpty()) {
            return false;
        }
        char c = Character.toUpperCase(input.charAt(0));
        return (c == '1' || c == 'Y' || c == 'T');
    }

    public static List<String> readLinesFromeFile(String fileName) {
        ArrayList<String> file = new ArrayList<>();
        try {
            FileReader sr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(sr);
            String line = "";
            while ((line = br.readLine()) != null) {
                file.add(line);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return file;
    }

    public static void writeFile(String fileName, List list) {
        try {
            FileWriter createFile = new FileWriter(fileName, true);
            BufferedWriter writeFile = new BufferedWriter(createFile);
            for (int i = 0; i < list.size(); i++) {
                writeFile.write(list.get(i).toString());
                writeFile.newLine();
            }
            writeFile.close();
            createFile.close();
        } catch (Exception e) {
            System.out.println(e);;
        }
    }

    public static int getInt(int min, int max) {
        int n;
        while (true) {
            try {
                System.out.print("Enter the Quantity of Delivery: ");
                n = Integer.parseInt(SC.nextLine());
                if (n < min || n > max) {
                    throw new Exception();
                } else {
                    return n;
                }
            } catch (Exception e) {
                System.out.println("Enter again");

            }
        }
    }

    public static double getDouble(int min, int max) {
        double n;
        while (true) {
            try {
                System.out.print("Enter the Price of Delivery: ");
                n = Double.parseDouble(SC.nextLine());
                if (n < min || n > max) {
                    throw new Exception();
                } else {
                    return n;
                }
            } catch (Exception e) {
                System.out.println("Enter again");
            }
        }

    }
     public static String setName(String format) {
        String name;

        while (true) {
            System.out.println("Enter the name of Delivery: ");
            name = SC.nextLine();
            if (name.isEmpty() || name.matches(format) == true) {
                return name;
            }
            System.out.println("Enter again");
        }
        
    }

    public static double setUnitPrice( int min, int max) {
        String price;
        boolean check =true;
        double a = 0;
       do{
           
            System.out.println("Enter the Price of Delivery: ");
            price = SC.nextLine();
            if (price.isEmpty()) {
                return -1;
               
            } else {
               
                try {
                     a = Double.parseDouble(price);
                    if (a < min || a > max) {
                        throw new Exception();
                    } else {
                        check = false;
                    }
                } catch (Exception e) {
                    System.out.println("Enter again");
                }
            }
        }while(check);
       return a; 
    }
  public static int setQuantity(int min, int max) {
        String price;
        boolean check = true;
        int a = 0;
        while (check){
            System.out.println("Enter the Quantity of Delivery: ");
            price = SC.nextLine();
            if (price.isEmpty()) {
                return -1;
            } else {
                
                try {
                    a = Integer.parseInt(price);
                    if (a < min || a > max) {
                        throw new Exception();
                    } else {
                        check = false;
                    }
                } catch (Exception e) {
                    System.out.println("Enter again");
                }
            }
        }
       return a; 
    }
   
}
