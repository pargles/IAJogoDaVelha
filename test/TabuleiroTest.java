
import junit.framework.TestCase;

/**
 *
 * @author pargles
 * @version 1.0
 */
public class TabuleiroTest extends TestCase {
    Tabuleiro t;
    
    public TabuleiroTest(String testName) {
        super(testName);
        t = new Tabuleiro('X','O');
    }

    public void setaTabuleiro(char a, char b, char c, char d, char e, char f, char g, char h,char i) {
        t.tabuleiro[0]=a;
        t.tabuleiro[0]=b;
        t.tabuleiro[0]=c;
        t.tabuleiro[0]=d;
        t.tabuleiro[0]=e;
        t.tabuleiro[0]=f;
        t.tabuleiro[0]=g;
        t.tabuleiro[0]=h;
        t.tabuleiro[0]=i;
    }

    public void testCalcularLivres1() {
        setaTabuleiro('X',' ',' ','O','O','O','O',' ',' ');
        assertEquals(1,t.calcularLivres('X'));
    }

    public void testCalcularLivres2() {
        setaTabuleiro('X',' ',' ','O','O','O',' ',' ',' ');
        assertEquals(2, t.calcularLivres('X'));
    }
    
    public void testCalcularLivres3() {
        setaTabuleiro('X',' ',' ','O','O',' ',' ',' ',' ');
        assertEquals(3, t.calcularLivres('X'));
    }

    public void testCalcularLivres4() {
        setaTabuleiro('X',' ',' ',' ','O',' ',' ',' ',' ');
        assertEquals(4, t.calcularLivres('X'));
    }

    public void testCalcularLivres5() {
        setaTabuleiro('X',' ','O',' ',' ',' ',' ',' ',' ');
        assertEquals(5, t.calcularLivres('X'));
    }

    public void testCalcularLivres6() {
        setaTabuleiro('X','O',' ',' ',' ',' ',' ',' ',' ');
        assertEquals(6, t.calcularLivres('X'));
    }

    public void testCalcularLivres8() {
        setaTabuleiro('X',' ',' ',' ',' ',' ',' ',' ',' ');
        assertEquals(8, t.calcularLivres('X'));
    }

}
