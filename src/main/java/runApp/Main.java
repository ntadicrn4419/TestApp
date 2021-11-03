package runApp;

import storageSpec.AbstractUser;
import storageSpec.UserManager;

import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("GoogleDriveUser");
            AbstractUser user = UserManager.getUser();

            Scanner sc = new Scanner(System.in);
            while(true){
                System.out.println("Za kreiranje skladista unesite broj 1: ");
                System.out.println("Za izlazak iz programa unesite broj 2: ");
                String input = sc.nextLine();
                if(input.equalsIgnoreCase("1")){
                    System.out.println("Unesite naziv za novo skladiste: ");
                    String name = sc.nextLine();
                    user.initStorage(name, "random string koji za sad nicemu ne sluzi");

                }else{
                    if(input.equalsIgnoreCase("2")){
                        System.out.println("Kraj programa.");
                        return;
                    }else{
                        System.out.println("Uneli ste nesto drugo, tako da nista se ne desava.");
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
