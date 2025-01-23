// Dylan Berger
// 01/22/2025
// CSE 123
// TA: Eeshani Shilamkar
// This class implements caesar shift for us
import java.util.*;

public class CaesarShift extends Substitution {

    private int shift;

    public CaesarShift(int shift) {
        if (shift <= 0) {
            throw new IllegalArgumentException("Shift value cannot be negative.");
        }

        this.shift = shift;
        String encoded = createEncoding(shift); // This encoding string will be used to perform encryption and decryption
        super.setEncoding(encoded); // call the superclas s method setEncoding
    }

// Behavior: 
//   - This method creates the encoding by shifting characters in a string by
//     a specified amount
// Parameters:
//   - shift: the size of the shift that each to-be encoded character will venture
// Returns:
//   - String: the encoded string 
// Exceptions:
//   - None for this method 
    public String createEncoding(int shift) {
        Queue<Character> helperQueue = new LinkedList<>();
        for (int i = Cipher.MIN_CHAR; i <= Cipher.MAX_CHAR; i++) {
            helperQueue.add((char) i);
        }
        for (int i = 0; i < shift; i++) {
            char c = helperQueue.remove();
            helperQueue.add(c);
        }
        String encoded = "";
        while (!helperQueue.isEmpty()) {
            encoded += helperQueue.remove();
        }
        return encoded;
    }
}
