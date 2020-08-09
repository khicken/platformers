package src;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;
import java.io.File;

public class Encryption {
    private static KeyGenerator keygen;
    private static SecretKey deskey;
    private static Cipher desCipher;
    public static void encryptFile(File f) {
        try {
            keygen = KeyGenerator.getInstance("DES");
            deskey = keygen.generateKey();
            
            desCipher = Cipher.getInstance("DES");

            byte[] text = "hmn".getBytes("UTF8");

            desCipher.init(Cipher.ENCRYPT_MODE, deskey);
            byte[] textEncrypted = desCipher.doFinal(text);

            String s = new String(textEncrypted);
            System.out.println(s);

            desCipher.init(Cipher.DECRYPT_MODE, deskey);
            byte[] textDecrypted = desCipher.doFinal(textEncrypted);

            s = new String(textDecrypted);
            System.out.println(s);
        } catch(Exception e) {
            System.out.println("Encryption error: " + e);
        }
    }

    public static void decryptFile(File f) {

    }
}