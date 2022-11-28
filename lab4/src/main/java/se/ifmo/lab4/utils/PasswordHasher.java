package se.ifmo.lab4.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class to hash password
 */
public class PasswordHasher {
    public static String hashPassword(String input) {
        try {
            // getInstance() method is called with algorithm SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(input.getBytes());
            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);
            // Convert message digest into hex value
            StringBuilder hashtext = new StringBuilder(no.toString(16));
            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext.insert(0, "0");
            }
            // return the HashText
            return hashtext.toString();
        }
        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException ignored) {
            return null;
        }
    }
}
