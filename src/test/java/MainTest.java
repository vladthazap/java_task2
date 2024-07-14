import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;


class MainTest {
    static ByteArrayOutputStream baos;
    static PrintStream ps;


    @BeforeAll
    static void preparing() {
        baos = new ByteArrayOutputStream();
        ps = new PrintStream(baos);
        System.setOut(ps);

    }

    @Test
    void testCacheMiss() {
        System.out.println("testCacheMiss begin");
        Fractionable fr = new Fraction(1,2);
        fr = Utils.cache(fr);
        System.out.println("1 call: doubleValue=" + fr.doubleValue());
        String strOut = baos.toString();
        // Assertions.assertNotEquals(-1, strOut.indexOf("test"));
        // 3-й вызов мимо кэша
        assertTrue( strOut.indexOf("cache miss")>-1 );
    }

    @Test
    void testCacheHit() {
        System.out.println("testCacheHit begin");
        Fractionable fr = new Fraction(1,2);
        fr = Utils.cache(fr);
        System.out.println("1 call: doubleValue=" + fr.doubleValue());
        System.out.println("2 call: doubleValue=" + fr.doubleValue());
        String strOut = baos.toString();
        // попадание в кэш
        assertTrue( strOut.indexOf("cache hit")>-1 );
    }



    @Test
    void testCacheAll() {
        System.out.println("testCacheClear begin");
        Fractionable fr = new Fraction(1,2);
        fr = Utils.cache(fr);
        System.out.println("1 call: doubleValue=" + fr.doubleValue());
        System.out.println("2 call: doubleValue=" + fr.doubleValue());
        fr.setDenum(4);
        System.out.println("3 call: doubleValue=" + fr.doubleValue());
        String strOut = baos.toString();
        // Assertions.assertNotEquals(-1, strOut.indexOf("test"));
        //
        // assertTrue( strOut.indexOf("cache miss")>-1 );

        assertAll("test Cache", () -> assertTrue(strOut.indexOf("cache miss")>-1),
                () -> assertTrue(strOut.indexOf("cache hit")>-1),
                () -> assertTrue(strOut.indexOf("cache clear")>-1)
                );
    }


}