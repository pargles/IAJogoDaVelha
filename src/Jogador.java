/**
 * @authors pargles and stephano
 * @version 2.0
 */
public class Jogador {

    private String nomeDoJogador;
    private char simbolo;//simbolo do jogador
    private Randomico random;
    private MinMax minMax;
    //private AlfaBeta corteAB;

    public Jogador(char simbolo,String nome)
    {
        nomeDoJogador = nome;
        this.simbolo = simbolo;
        random = new Randomico();
        minMax = new MinMax();
        //corteAB = new AlfaBeta();
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

    public int jogaMinMax(Tabuleiro t)
    {
        return minMax.executa(t,this);
    }

    public void setSimbolo(char simbolo)
    {
        this.simbolo = simbolo;
    }

    public char getSimbolo()
    {
        return simbolo;
    }

    public String getNome()
    {
        return nomeDoJogador;
    }

}
