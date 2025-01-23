// Dylan Berger
// 01/22/2025
// CSE 123
// TA: Eeshani Shilamkar
// This class implements a caesar cipher that uses a key for us 
import java.util.*;
// Behavior: 
//   - This helps us encode a string from a key that acts as our guide
// Parameters:
//   - String key: the key of the cipher, the guide that the encoding is based on
// Returns:
//   - No returns but calls the setEncoding method from the superclass
// Exceptions:
//   - If the key is empty or nothing, the program returns an IllegalArgumentException
//   - If there are characters outside of the encodable range, there will be an 
//     IllegalArgumentException
//   - If there are duplicate characters, there will be an IllegalArgumentException
public class CaesarKey extends Substitution {
    public CaesarKey(String key) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Key needs substance");
        }
        //notice how my methods for catching errors are the same as in "Substitution" 
        HashSet<Character> uniqueCharacters = new HashSet<>();
        for (char c : key.toCharArray()) {
            if (c < (char) Cipher.MIN_CHAR || c > (char) Cipher.MAX_CHAR) {
                throw new IllegalArgumentException("There are characters outside the range");
            }
            uniqueCharacters.add(c);
        }

        if (uniqueCharacters.size() != key.length()) {
            throw new IllegalArgumentException("Encoding contains duplicate characters.");
        }
        //gets the character and if it is unique, it adds it to the key
        for (int i = Cipher.MIN_CHAR; i <= Cipher.MAX_CHAR; i++) {
            char c = (char) i;
            if (!uniqueCharacters.contains(c)) {
                key += c;
            }
        }
        // Call the setEncoding method to set encoding using the key we just confirmed.
        super.setEncoding(key);  
    }
}
