package src;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class FileReadWrite {
    /**
     * Save file format:
     * name: string
     * level: int
     */
    private static String[] savefileValueNames = {"name", "lvl"};
    private static String[] saveFileValues = new String[savefileValueNames.length];

    public static void readSaveFile(Window w, String saveID) {        
        try {
            File f = new File("./../saves/save-" + saveID + "");
            Scanner sc = new Scanner(f);
            
            sc.close();
        } catch(FileNotFoundException e) {
            w.print("Error loading save file.");
        }
    }

    public static void readDataFile(Window w, String file) {        
        try {
            File f = new File("./../saves/" + file);
            Scanner sc = new Scanner(f);
            
            sc.close();
        } catch(FileNotFoundException e) {
            w.print("Error loading save file.");
        }
    }

    public static void createFile(Window w) {
        try {
            int saveNumber = 1;
            while(true) { // iterate thru save folder to see the next available save file
                if(new File("./../saves/save-" + saveNumber + ".jadv").exists()) saveNumber++;
                else break;
            }

            File f = new File("./../saves/save-" + saveNumber + ".jadv");
            if(f.createNewFile()) w.println("New save file(" + f.getName() + ") successfully generated!");
        } catch(IOException e) {
            w.println("An error creating a new save file occurred.");
            e.printStackTrace();
        }
    }
 

    public static void saveFile(Window w, int saveID, boolean firstTime) { // this overwrites the entire file lol
        w.println("Saving. . .");
        try {
            FileWriter fw = new FileWriter(new File("./../saves/save-" + saveID + ".jadv"));

            for(int i = savefileValueNames.length)
            fw.write("name: ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}