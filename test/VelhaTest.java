import junit.framework.TestCase;

/**
 * classe para testar alguns metodos especificos (unidades)
 * @author pargles
 * @version 1.0
 */
public class VelhaTest extends TestCase {
    Jogador j1,j2;
    Velha v;
    
    public VelhaTest(String testName) {
        super(testName);
        j1= new Jogador('X',"tiazinha");
        j2= new Jogador('O',"maguila");
        v = new Velha(j1,j2);
    }

    public void testExisteVencedor1() {
        v.tabuleiro.tabuleiro[0] = 'X';
        v.tabuleiro.tabuleiro[1] = 'X';
        v.tabuleiro.tabuleiro[2] = 'X';
        assertEquals(true,v.vencedor(j1));
    }
    
   public void testExisteVencedor2() {
        v.tabuleiro.tabuleiro[0] = 'X';
        v.tabuleiro.tabuleiro[4] = 'X';
        v.tabuleiro.tabuleiro[8] = 'X';
        assertEquals(true,v.vencedor(j1));
    }
   
    public void testExisteVencedor3() {
        v.tabuleiro.tabuleiro[2] = 'X';
        v.tabuleiro.tabuleiro[4] = 'X';
        v.tabuleiro.tabuleiro[6] = 'X';
        assertEquals(true,v.vencedor(j1));
    }

    public void testExisteVencedor4() {
        v.tabuleiro.tabuleiro[0] = 'O';
        v.tabuleiro.tabuleiro[3] = 'O';
        v.tabuleiro.tabuleiro[6] = 'O';
        assertEquals(true,v.vencedor(j2));
    }
    
    public void testExisteVencedor5() {
        v.tabuleiro.tabuleiro[3] = 'O';
        v.tabuleiro.tabuleiro[4] = 'O';
        v.tabuleiro.tabuleiro[5] = 'O';
        assertEquals(true,v.vencedor(j2));
    }

    public void testExisteVencedor6() {
        v.tabuleiro.tabuleiro[6] = 'O';
        v.tabuleiro.tabuleiro[7] = 'O';
        v.tabuleiro.tabuleiro[8] = 'O';
        assertEquals(true,v.vencedor(j2));
    }

     public void testExisteVencedor7() {
        v.tabuleiro.tabuleiro[1] = 'O';
        v.tabuleiro.tabuleiro[4] = 'O';
        v.tabuleiro.tabuleiro[7] = 'O';
        assertEquals(true,v.vencedor(j2));
    }

    public void testExisteVencedor8() {
        v.tabuleiro.tabuleiro[2] = 'O';
        v.tabuleiro.tabuleiro[5] = 'O';
        v.tabuleiro.tabuleiro[8] = 'O';
        assertEquals(true,v.vencedor(j2));
    }
}
