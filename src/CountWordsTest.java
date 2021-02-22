import org.junit.Test;
import static org.junit.Assert.*;

class CountWordsTest {

    @Test
    public void testStrings() {
        String a = "Hi", b = " there";
        String c = "Hi there";
        assertEquals( a + b, c );
    }

    @Test
    public void addToLogTest1 () {
        CountWords.addToLog( "Hello", 2 );
        String expected = "{Hello=[2]}";
        String actual = CountWords.log.toString();
        assertEquals(expected, actual);
    }

}