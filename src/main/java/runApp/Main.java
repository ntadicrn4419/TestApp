package runApp;

import storageSpec.AbstractUser;
import storageSpec.UserManager;

import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("GoogleDriveUser");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if(args.length < 1){
        System.out.println("Niste uneli putanju skladista.");
             return;
        }
        String storagePath = args[0];
        AbstractUser user = UserManager.getUser();
        user.initStorage(storagePath);

        Scanner sc = new Scanner(System.in);
        int br;
        while(true){
            printOptions();
            br = sc.nextInt();
            switch (br){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
            }
        }

    }
    public static void printOptions(){

    }
}
