package runApp;

import storageSpec.AbstractUser;
import storageSpec.UserManager;

import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            //Class.forName("LocalUser");
            Class.forName("GoogleDriveUser");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        /*
        if(args.length < 1){
        System.out.println("Niste uneli putanju skladista.");
             return;
        }
        String storagePath = args[0];
        */
        //String storagePath = "C:\\Users\\Luka\\Desktop\\123";
        String storagePath = "sgaNaXma";

        AbstractUser user = UserManager.getUser();
        if (user.storageExists(storagePath)) {
            System.out.println("Storage exists please logIn");
            Scanner sc = new Scanner(System.in);
            System.out.println("username:");
            String username = sc.nextLine();
            System.out.println("password:");
            String password = sc.nextLine();
            int message = user.logIn(storagePath, username, password);
            if(message == 0){
                System.out.println("Wrong username or password");
                return;
            }else{
                System.out.println("Success");
            }
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
                    System.out.println("New file name: ");
                    String fileName = userInput.nextLine();
                    System.out.println("Path relative to storage root eg: \\ -> means current folder");
                    String pathToCreateFile = userInput.nextLine();
                    message = user.createFile(fileName, pathToCreateFile, null, "text/plain");
                    if(message == 1)
                        System.out.println("Created file: " + pathToCreateFile + fileName);
                    else
                        System.out.println("Error while creating file on: " + pathToCreateFile + fileName);
                    break;
                case 5:
                    System.out.println("Give name to your file: ");
                    fileName = userInput.nextLine();
                    System.out.println("Path of your file on local file system ->eg: C:Users\\Desktop\\myFile");
                    String pathFromWhereToUpload = userInput.nextLine();
                    System.out.println("Path relative to storage root eg: \\ -> means current folder");
                    String pathWhereToUploadFile = userInput.nextLine();
                    message = user.uploadExistingFile(fileName, pathWhereToUploadFile, pathFromWhereToUpload, "text/plain");
                    if(message == 1)
                        System.out.println("Created file: " + pathFromWhereToUpload + fileName);
                    else
                        System.out.println("Error while creating file on: " + pathFromWhereToUpload + fileName);
                    break;
                case 6:
                    System.out.println("Enter path of file you want to move: ");
                    String filePath = userInput.nextLine();
                    System.out.println("Enter where you want to move it: ");
                    String whereToMove = userInput.nextLine();
                    message = user.move(filePath, whereToMove);
                    if(message == 1)
                        System.out.println("Moved file: " + filePath + "to " + whereToMove);
                    else
                        System.out.println("Error while moving file from " + filePath + " to " + whereToMove);
                    break;
                case 7:
                    System.out.println("Enter paths of files you want to move separated with '-': ");
                    String input = userInput.nextLine();
                    String filespaths [] = input.split("-");
                    System.out.println("Enter where you want to move it: ");
                    whereToMove = userInput.nextLine();
                    message = user.move(Arrays.asList(filespaths), whereToMove);
                    if(message == 1)
                        System.out.println("Moved files" + "to " + whereToMove);
                    else
                        System.out.println("Error while moving files from to " + whereToMove);
                    break;
                case 8:
                    System.out.println("Enter filePath you want to delete: ");
                    input = userInput.nextLine();
                    message = user.delete(input);
                    if(message == 1)
                        System.out.println("Deleted file " + input);
                    else
                        System.out.println("Error while deleting file " + input);
                    break;
                case 9:
                    System.out.println("Enter directory path: ");
                    String dirPath = userInput.nextLine();
                    Collection<String> coll = user.searchFilesInDir(dirPath);
                    System.out.println("Files in dir " + dirPath + " : ");
                    for(String c: coll){
                        System.out.println(c);
                    }
                    break;
                case 10:
                    System.out.println("Enter directory path: ");
                    dirPath = userInput.nextLine();
                    coll = user.searchDirsInDir(dirPath);
                    System.out.println("Directories in directory " + dirPath + " : ");
                    for(String c: coll){
                        System.out.println(c);
                    }
                    break;
                case 11:
                    break;
                case 12:
                    System.out.println("Enter directory path: ");
                    dirPath = userInput.nextLine();
                    printValidExtentions();
                    System.out.println("Enter extention:");
                    String ext = userInput.nextLine();
                    String mimeType = convertExtentionToMimeType(ext);
                    if(mimeType == null || mimeType == ""){
                        System.out.println("Failed: Not valid extention input");
                        break;
                    }
                    coll = user.searchByExtension(mimeType, dirPath);
                    System.out.println("Files with extention " + ext + " in directory " + dirPath + ": ");
                    for(String c: coll){
                        System.out.println(c);
                    }
                    break;
                case 13:
                    System.out.println("Enter directory path: ");
                    dirPath = userInput.nextLine();
                    coll = user.getFilesInDirSortedByName(dirPath);
                    System.out.println("Files in " + dirPath + ", sorted by name: ");
                    for(String c: coll){
                        System.out.println(c);
                    }
                    break;
                case 14:
                    System.out.println("Enter path: ");
                    path = userInput.nextLine();
                    String modificationDate = user.getModificationDate(path);
                    System.out.println(path + " has been modified:  " + modificationDate);
                    break;
                case 15:
                    System.out.println("Enter path: ");
                    path = userInput.nextLine();
                    String creationDate = user.getCreationDate(path);
                    System.out.println(path + "has been created:  " + creationDate);
                    break;
                case 16:
                    break;
                case 17:
                    break;
                case 18:
                    System.out.println("Enter storage size in bytes: ");
                    int bytes = userInput.nextInt();
                    message = user.setStorageSize(bytes);
                    if(message == 1){
                        System.out.println("Storage size in bytes set successfully");
                    }else{
                        System.out.println("Error while setting storage size in bytes");
                    }
                    break;
                case 19:
                    System.out.println("Enter forbidden extentions separated with '-' : ");
                    printValidExtentions();
                    input = userInput.nextLine();
                    String arr[] = input.split("-");
                    message = user.setForbiddenExtensions(Arrays.asList(arr));
                    if(message == 1){
                        System.out.println("Forbidden extentions set successfully");
                    }else{
                        System.out.println("Error while setting forbidden extentions");
                    }
                    break;
                case 20:
                    System.out.println("Enter max file number in dir: ");
                    int maxNumber = userInput.nextInt();
                    System.out.println("Enter dirPath: ");
                    dirPath = userInput.nextLine();
                    message = user.setMaxFileNumberInDir(maxNumber, dirPath);
                    if(message == 1){
                        System.out.println("Max file number in directory " + dirPath + " is " + maxNumber);
                    }else{
                        System.out.println("Error while setting max file number in dir " + dirPath);
                    }
                    break;
                case 21:

                    break;
                case 22:
                    break;
                case 0:
                    System.out.println("End of program");
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
        System.out.println("6. move one file");
        System.out.println("7. move multiple files");
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
    public static void printValidExtentions(){
        System.out.println("Valid extentions: .txt, .css, .doc, .docx, .gif, .html, .jar, .jpg, .jpeg, .js, .json, .mp3, .mp4, .png, .pdf, .rar, .xml, .zip, .7z");
    }
    public static String convertExtentionToMimeType(String ext){
        String mimeType = "";
        switch (ext){
            case ".txt":
                mimeType = "text/plain";
                break;
            case ".css":
                mimeType = "text/css";
                break;
            case ".doc":
                mimeType = "application/msword";
                break;
            case ".docx":
                mimeType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
                break;
            case ".gif":
                mimeType = "image/gif";
                break;
            case ".html":
                mimeType = "text/html";
                break;
            case ".jar":
                mimeType = "application/java-archive";
                break;
            case ".jpg":
                mimeType = "image/jpeg";
                break;
            case ".jpeg":
                mimeType = "image/jpeg";
                break;
            case ".js":
                mimeType = "text/javascript";
                break;
            case ".json":
                mimeType = "application/json";
                break;
            case ".mp3":
                mimeType = "audio/mpeg";
                break;
            case ".mp4":
                mimeType = "video/mp4";
                break;
            case ".png":
                mimeType = "image/png";
                break;
            case ".pdf":
                mimeType = "application/pdf";
                break;
            case ".rar":
                mimeType = "application/vnd.rar";
                break;
            case ".xml":
                mimeType = "application/xml";
                break;
            case ".zip":
                mimeType = "application/zip";
                break;
            case ".7z":
                mimeType = "application/x-7z-compressed";
                break;
            default:
                return null;
        }
        return mimeType;
    }
}
