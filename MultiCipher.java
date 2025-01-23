// Dylan Berger
// 01/22/2025
// CSE 123
// TA: Eeshani Shilamkar
// This class gives us the capability of using more than one of the ciphers we created
// on a single string. I implement this using a list of ciphers
import java.util.*;

public class MultiCipher extends Substitution {
    private List<Cipher> ciphers;

//throws an IllegalArgumentException if the list of ciphers is null
    public MultiCipher(List<Cipher> ciphers) {
        if (ciphers == null) {
            throw new IllegalArgumentException("Cipher list cannot be null.");
        }
        this.ciphers = ciphers;
    }
// Behavior: 
//   - This method allows us to encrypt an input string with multiple ciphers
// Parameters:
//   - String input: The string that is to be encrypted several times 
// Returns:
//   - Returns the encrypted string
// Exceptions:
//   - if the input is null, an IllegalArgumentException is thrown. Encryption cannot be done
    public String encrypt(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        //loops through all the ciphers and encrypts the input string using the current cipher
        for (Cipher cipher : ciphers) {
            input = cipher.encrypt(input);
        }
        return input;
    }
// Behavior: 
//   - This method allows us to decrypt an input string with multiple ciphers
// Parameters:
//   - String input: The string that is to be decrypted several times 
// Returns:
//   - Returns the decrypted string
// Exceptions:
//   - if the input is null, an IllegalArgumentException is thrown. Encryption cannot be done
    public String decrypt(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        //loops through all the ciphers and decodes the input string using the current cipher
        for (int i = ciphers.size() - 1; i >= 0; i--) {
            input = ciphers.get(i).decrypt(input);
        }
        return input;
    }
}


