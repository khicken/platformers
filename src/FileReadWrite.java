package src;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class FileReadWrite {
    public static void ReadFile(String saveFile) {
        try {
            File f = new File("../saves/" + saveFile);
            Scanner sc = new Scanner(f);
            
            sc.close();
        } catch(FileNotFoundException e) {
            System.out.println("Error loading save file.");
        }
    }
}