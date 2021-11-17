package runApp;

import storageSpec.AbstractUser;
import storageSpec.UserManager;

import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("LocalUser");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

//        if(args.length < 1){
//        System.out.println("Niste uneli putanju skladista.");
//             return;
//        }
//        String storagePath = args[0];
        String storagePath = "C:\\Users\\Luka\\Desktop\\123";
        AbstractUser user = UserManager.getUser();
        if (user.storageExists(storagePath)) {
            System.out.println("Storage exists please logIn");
            Scanner sc = new Scanner(System.in);
            System.out.println("username:");
            String username = sc.nextLine();
            System.out.println("password:");
            String password = sc.nextLine();
            int message = user.logIn(storagePath, username, password);
            if(message == 0)
                System.out.println("Wrong username or password");
            else
                System.out.println("Success");
        } else {
            System.out.println("Storage doesnt exist please register");
            Scanner sc = new Scanner(System.in);
            System.out.println("username:");
            String username = sc.nextLine();
            System.out.println("password:");
            String password = sc.nextLine();
            int message = user.initStorage(storagePath, username, password);
            if(message == 0)
                System.out.println("Something went wrong");
            else
                System.out.println("Storage created");
        }

        Scanner sc = new Scanner(System.in);
        Scanner userInput = new Scanner(System.in);

        int br;
        int message;
        while(true){
            printOptions();
            br = sc.nextInt();
            switch (br){
                case 1:
                    break;
                case 2:
                    userInput = new Scanner(System.in);
                    System.out.println("New dir name: ");
                    String dirName = userInput.nextLine();
                    System.out.println("Path relative to storage root eg: \\ -> means current folder");
                    String path = userInput.nextLine();
                    message = user.createDir(dirName, path);
                    if(message == 1)
                        System.out.println("Created directory: " + path + dirName);
                    else
                        System.out.println("Error while creating directory on: " + path + dirName);
                    break;
                case 3:
                    System.out.println("Number of dirs: ");
                    String numberOfDirsStr = userInput.nextLine();
                    int numberOfDirs = Integer.parseInt(numberOfDirsStr);
                    System.out.println("Name Prefix: ");
                    String namePrefix = userInput.nextLine();

                    System.out.println("Name path:  eg-> \\ means root folder \\existingFolder\\ means in existing folder");
                    String pathToCreateDirs = userInput.nextLine();
                    message = user.createDir("", pathToCreateDirs, namePrefix, numberOfDirs);
                    if(message == 1)
                        System.out.println("created " + numberOfDirs + "dirs with name prefix " + namePrefix + "in folder" + pathToCreateDirs);
                    else
                        System.out.println("Something went wrong");
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
                case 11:
                    break;
                case 12:
                    break;
                case 13:
                    break;
                case 14:
                    break;
                case 15:
                    break;
                case 16:
                    break;
                case 17:
                    break;
                case 18:
                    break;
                case 19:
                    break;
                case 20:
                    break;
                case 21:
                    break;
                case 22:
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Wrong command");
                    break;
            }
        }

    }
    public static void printOptions(){
        System.out.println("Choose operation number: ");
        System.out.println("1. saveStorageData");
        System.out.println("2. createDir");
        System.out.println("3. create multiple Dir");
        System.out.println("4. createFile");
        System.out.println("5. uploadExistingFile");
        System.out.println("6. move");
        System.out.println("7. move");
        System.out.println("8. delete");
        System.out.println("9. searchFilesInDir");
        System.out.println("10. searchDirsInDir");
        System.out.println("11. searchByName");
        System.out.println("12. searchByExtension");
        System.out.println("13. getFilesInDirSortedByName");
        System.out.println("14. getModificationDate");
        System.out.println("15. getCreationDate");
        System.out.println("16. searchByDateCreationRange");
        System.out.println("17. searchFilesInDirByDateCreationRange");
        System.out.println("18. setStorageSize");
        System.out.println("19. setForbiddenExtensions");
        System.out.println("20. setMaxFileNumberInDir");
        System.out.println("21. addUser");
        System.out.println("22. removeUser");
        System.out.println("0. Exit");
    }
}
