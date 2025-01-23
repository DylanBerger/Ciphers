import java.util.*;
// Dylan Berger
// 01/22/2025
// CSE 123
// TA: Eeshani Shilamkar
// This class implements the substitution method of encryption and decryption
public class Substitution extends Cipher {
    private String encoding;
    private Map<Character, Character> decoderMap;

    public Substitution() {
        this.encoding = null;
    }

    public Substitution(String encoding) {
        setEncoding(encoding);
    }

// Behavior: 
//   - This helper method converts a string into an array of its characters
// Parameters:
//   - theInput: the string you want converted into an array of its characters
// Returns:
//   - char[]: returns an array of characters
    public char[] toArray(String inputString) {
        char[] charArray = new char[inputString.length()];
        for (int i = 0; i < inputString.length(); i++) {
            charArray[i] = inputString.charAt(i);
        }

        return charArray;
    }

// Behavior: 
//   - This method sets the encoding and ensures there are not Exceptions
//     to worry about
// Parameters:
//   - encoding: A string with the encoding
// Returns:
//   - void: No return
// Exceptions:
//   - encoding is not null: if encoding is empty, it throws an IllegalArgumentException
//   - if length of encoding does not match the # of characters in the
//     defined encodable range, it returns an IllegalArgumentException
//   - if any characters outside the range of characters it returns an IllegalArgumentException
//   - if there are duplicate characters, it returns an IllegalArgumentException
    public void setEncoding(String encoding) {
        if (encoding == null) {
            throw new IllegalArgumentException("No mapping found");
        }
        if (encoding.length() != TOTAL_CHARS) {
            throw new IllegalArgumentException("Length of encoding doesn't match  number of characters in Cipher's encodable range ");
        }

        HashSet<Character> uniqueCharacters = new HashSet<>(); //set to help with finding duplicates

        char[] encodingAsArray = toArray(encoding);
        for (char c : encodingAsArray) {
            if (c < Cipher.MIN_CHAR || c > Cipher.MAX_CHAR) {
                throw new IllegalArgumentException("Encoding contains characters outside the valid range.");
            }
            uniqueCharacters.add(c); //adds to set
        }
        //if the set size is unequal to the length of the encoding string, there are duplicates
        if (uniqueCharacters.size() != encoding.length()) {
            throw new IllegalArgumentException("Encoding contains duplicate characters.");
        }

        this.encoding = encoding;

    }

// Behavior: 
//   - This method encrypts a string as per the defined encoding scheme 
// Parameters:
//   - theInput: the string that is to be encrypted
// Returns:
//   - encrypted: the input string, but encrypted
// Exceptions:
//   - if the encoding string or theInput string are null, encryption cannot be performed
//     an IllegalArgumentException is thrown
    public String encrypt(String theInput) {
        if (encoding == null) {
            throw new IllegalStateException("There is no encoding");
        }

        if (theInput == null) {
            throw new IllegalArgumentException("There is no input");
        }

        String encrypted = "";
        char[] inputAsArray = toArray(theInput); //calls my helper method
        for (char c : inputAsArray) {
            int integer = (int)(c); //casts the integer of the current character
            encrypted += encoding.charAt(integer - MIN_CHAR); //gets the correct character for encryption
        }

        return encrypted;
    
    }

// Behavior: 
//   - This method decrypts a string input based on the defined encoding scheme
// Parameters:
//   - theInput: the string that is to be decrypted
// Returns:
//   - String: the previously encrypted string, now decrypted and readable
// Exceptions:
//   - if the encoding string or theInput string are null, decryption cannot be performed
//     an IllegalArgumentException is thrown
    public String decrypt(String theInput) {
        if (encoding == null) {
            throw new IllegalStateException("There is no encoding");
        }

        if (theInput == null) {
            throw new IllegalArgumentException("There is no input");
        }

        String decrypted = ""; 

        char[] decoderMap = new char[TOTAL_CHARS]; //I mapped with an array this time 
        for (int i = 0; i < encoding.length(); i++) {
            decoderMap[encoding.charAt(i) - MIN_CHAR] = (char) (i + MIN_CHAR); //performs the mapping
        }

        char[] inputAsArray = toArray(theInput);
        for (char c : inputAsArray) {
            
            decrypted += decoderMap[c - MIN_CHAR]; //creates the decrypted string
        }

        return decrypted;
    }
 

}
