
import java.util.Random;

/**
 *
 * @author pargles
 * @version 1.0
 */
public class Randomico {
    private static Random random;

    public Randomico()
    {
        random = new Random( System.currentTimeMillis());
    }

    public int executa(Tabuleiro t)
    {
        int tam = t.tabuleiro.length;
        int sorteado = random.nextInt(tam);
        while(t.tabuleiro[sorteado]!=' ')//nao pode sortear aonde ja tem 'X' ou 'O' repete o sorteio
        {
            sorteado =  random.nextInt(tam);
        }
        //System.out.println("posicao sorteada: "+sorteado);
        return sorteado;
    }

}
