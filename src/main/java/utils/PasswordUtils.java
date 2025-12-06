package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtils {


	    
	private static boolean constantTimeEquals(byte[] a, byte[] b) {
        int length = Math.min(a.length, b.length); 
        int result = a.length ^ b.length; 
        for (int i = 0; i < length; i++) {
            result |= a[i] ^ b[i]; 
        }

        return result == 0; 
    }

   
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error FATAL: Algoritmo de hash no disponible. " + e.getMessage());
            throw new RuntimeException("Error al hashear la contraseña", e);
        }
    }
    
   
     
    public static boolean verifyPassword(String password, String storedHashString) {
        
        byte[] inputHashedBytes;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            inputHashedBytes = md.digest(password.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al hashear la contraseña para verificación", e);
        }

        int len = storedHashString.length();
        byte[] storedHashedBytes = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            storedHashedBytes[i / 2] = (byte) ((Character.digit(storedHashString.charAt(i), 16) << 4)
                                     + Character.digit(storedHashString.charAt(i+1), 16));
        }

       
        return constantTimeEquals(inputHashedBytes, storedHashedBytes);
    }


	public static String getSHA256(String password) {
		// TODO Auto-generated method stub
		return null;
	}
	}