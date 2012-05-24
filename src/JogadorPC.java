/**
 * @author pargles
 * @version 1.0
 */
public class JogadorPC extends Jogador{

    private Randomico random;
    //MinMax minMax;
    //AlfaBeta corteAB;

    //classe que extende o jogador normal
    public JogadorPC(char simbolo)
    {
        super(simbolo);
        random = new Randomico();
    }

    /* metodo que chama o algoritmo aleatorio para fazer uma
     * jogada aleatoria, passando como referencia o tabuleiro atual
     * e retornando a posicao em que o jogadorPC deve jogar
     * @param Tabuleiro t
     * @return int jogaRandomico
     */
    public int jogaRandomico(Tabuleiro t)
    {
        return random.executa(t);
    }

}
