package org.javabrain.util.codify;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;

/***
 * @author Fernando García
 * @version 0.0.1
 */
public class Security {

    public static String encrypt(String texto) {

        String secretKey = "qualityinfosolutions"; //llave para encriptar datos
        String base64EncryptedString = "";

        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            //se le manda el parametro de lo que encriptara y a cuanto maximo

            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] plainTextBytes = texto.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            base64EncryptedString = new String(base64Bytes);

        } catch (Exception e) {}

        return base64EncryptedString;
    }

    public static String decrypt(String textoEncriptado){

        String secretKey = "qualityinfosolutions"; //llave para encriptar datos
        String base64EncryptedString = "";

        try {
            byte[] message = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");

            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);

            byte[] plainText = decipher.doFinal(message);

            base64EncryptedString = new String(plainText, "UTF-8");

        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }

    public static String randomString()
    {
        String todo = "";
        try
        {
            String [] letra = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                    "K", "L", "M","N","O","P","Q","R","S","T","U","V","W", "X","Y","Z" };

            int numRandon  = (int) Math.round(Math.random() * 10000 );
            int numRandon1 = (int) Math.round(Math.random() * 26 ) ;
            int numRandon2 = (int) Math.round(Math.random() * 26 ) ;
            int numRandon3 = (int) Math.round(Math.random() * 26 ) ;
            int numRandon4 = (int) Math.round(Math.random() * 26 ) ;

            todo = letra[numRandon1]+letra[numRandon2]+letra[numRandon3]+letra[numRandon4]+
                    numRandon;
        }
        catch(Exception e)
        {

        }

        return todo;
    }

}
