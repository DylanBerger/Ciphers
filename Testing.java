import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assume.assumeTrue;
import java.util.*;

public class Testing {

    @Test
    @DisplayName("EXAMPLE TEST CASE - 'A'-'G' Spec Example")
    public void subAGTest() {
        // Remember that you can change MIN_CHAR AND MAX_CHAR 
        // in Cipher.java to make testing easier! For this 
        // example test, we are using MIN_CHAR = A and MAX_CHAR = G

        // Skip this test if the constants have changed
        assumeTrue(Cipher.MIN_CHAR == (int)('A') && Cipher.MAX_CHAR == (int)('G'));

        Cipher testSubstitution = new Substitution("GCBEAFD");
        assertEquals("FGE", testSubstitution.encrypt("FAD"));
        assertEquals("BAD", testSubstitution.decrypt("CGE"));
        
        // Per the spec, we should throw an IllegalArgumentException if 
        // the length of the encoder doesn't match the number of characters
        // within our Cipher's encodable range
        assertThrows(IllegalArgumentException.class, () -> {
            new Substitution("GCB");
        });
    }

    @Test
    @DisplayName("EXAMPLE TEST CASE - 'A'-'Z' encoder")
    public void subAZTest() {
        // Skip this test if the constants have changed
        assumeTrue(Cipher.MIN_CHAR == (int)('A') && Cipher.MAX_CHAR == (int)('Z'));

        // Reverse alphabetic
        Cipher testSubstitution = new Substitution(
            "ZYXWVUTSRQPONMLKJIHGFEDCBA"
        );
        assertEquals("UZW", testSubstitution.encrypt("FAD"));
        assertEquals("BAD", testSubstitution.decrypt("YZW"));
    }

    @Test
    @DisplayName("EXAMPLE TEST CASE - ' '-'}' encoder")
    public void subComplexTest() {
        // Skip this test if the constants have changed
        assumeTrue(Cipher.MIN_CHAR == (int)(' ') && Cipher.MAX_CHAR == (int)('}'));
        
        // Swapping lowercase a<->b
        Cipher testSubstitution = new Substitution(
            " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`" +
            "bacdefghijklmnopqrstuvwxyz{|}"
        );
        assertEquals("FAD", testSubstitution.encrypt("FAD"));
        assertEquals("fbd", testSubstitution.encrypt("fad"));
        assertEquals("BAD", testSubstitution.decrypt("BAD"));
        assertEquals("bad", testSubstitution.decrypt("abd"));
    }

    @Test
    @DisplayName("TODO: CaesarKey - 'A'-'Z'")
    public void keyAZOne() {
        // Skip this test if the constants have changed
        assumeTrue(Cipher.MIN_CHAR == (int)('A') && Cipher.MAX_CHAR == (int)('Z'));

        // TODO: Create a new CaesarKey("TIN"), encrypt the message "HELLO" and check the
        //       result's accurate. Then, take the encrypted message, decrypt it, and
        //       check the result's accurate
        Cipher testCK = new CaesarKey("TIN"); 
        assertEquals("EBJJM", testCK.encrypt("HELLO"));

        assertEquals("HELLO", testCK.decrypt("EBJJM"));
        
    }

    @Test
    @DisplayName("TODO: CaesarShift - 'A'-'Z' encoder")
    public void shiftAZOne() {
        // Skip this test if the constants have changed
        assumeTrue(Cipher.MIN_CHAR == (int)('A') && Cipher.MAX_CHAR == (int)('Z'));

        // TODO: Create a new CaesarShift(6), encrypt the message "HELLO" and check the
        //       result's accurate. Then, take the encrypted message, decrypt it, and
        //       check the result's accurate
        Cipher testCS = new CaesarShift(6); 
        assertEquals("NKRRU", testCS.encrypt("HELLO")); 

        assertEquals("HELLO", testCS.decrypt("NKRRU"));
    }

    @Test
    @DisplayName("TODO: MultiCipher - 'A'-'Z' encoder")
    public void multiAZOne() {
        // Skip this test if the constants have changed
        assumeTrue(Cipher.MIN_CHAR == (int)('A') && Cipher.MAX_CHAR == (int)('Z'));

        // TODO: Create a new MultiCipher with ciphers CaesarKey("TIN") and CaesarShift(6)),
        //       encrypt the message "HELLO", and check the result's accurate. Then, take
        //       the encrypted message, decrypt it, and check the result's accurate
        Cipher testCK = new CaesarKey("TIN"); 

        Cipher testCS = new CaesarShift(6); 
        List<Cipher> tCiphers = new ArrayList<Cipher>(); 
        tCiphers.add(testCK); 

        tCiphers.add(testCS); 

        assertEquals("NKRRU", testCS.encrypt("HELLO")); 
        assertEquals("HELLO", testCS.decrypt("NKRRU"));
    }
}
