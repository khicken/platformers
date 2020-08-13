package src;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileReadWrite {
    public static void readFile(Window w, String saveFile) {        
        try {
            File f = new File("./../saves/" + saveFile);
            Scanner sc = new Scanner(f);
            
            sc.close();
        } catch(FileNotFoundException e) {
            w.print("Error loading save file.");
        }
    }

    public static void createFile(Window w) {
        try {
            int saveNumber = 1;
            File f;
            while(true) {
                f = new File("./../saves/save-" + saveNumber + ".txt");
                if(!f.createNewFile()) saveNumber++;
                else break;
            }

            w.print("New save file(" + f.getName() + ") successfully generated!");
        } catch(IOException e) {
            w.print("An error occurred.");
            e.printStackTrace();
        }
    }
 
    public static void saveFile() {

    }
}