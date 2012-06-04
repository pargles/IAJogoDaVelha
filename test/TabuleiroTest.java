
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
        t = new Tabuleiro();
    }

    /**
     * Este método será chamado antes de cada método de teste.
     */
    @Override
    protected void setUp() {
    }

    /**
     * Após cada método de teste, este método é invocado para limpar o lixo.
     */
    protected void tearDown() {
    }

    public void setaTabuleiro(char a, char b, char c, char d, char e, char f, char g, char h,char i) {
        t.tabuleiro[0]=a;
        t.tabuleiro[1]=b;
        t.tabuleiro[2]=c;
        t.tabuleiro[3]=d;
        t.tabuleiro[4]=e;
        t.tabuleiro[5]=f;
        t.tabuleiro[6]=g;
        t.tabuleiro[7]=h;
        t.tabuleiro[8]=i;
    }

     /**
  * Todo método que começar com a palavra "test" será executado pelo JUnit.
  * O método testComportamento faz uma chamada ao método assertEquals(mensagem, valor
  * esperado, valor atual)
  */
    
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
