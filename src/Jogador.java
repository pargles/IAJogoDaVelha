/**
 * @authors pargles and stephano
 * @version 2.0
 */
public class Jogador {

    private String nome;
    char simbolo;//simbolo do jogador
    private Randomico random;
    MinMaxAB minMaxAB;
    MinMax minMax;
    private enum estrategias{MinMax, MinMaxAB,Random;}
    private String estrategia;

    //os jogadores sao criados com uma estrategia de jogo associada e um nivel de previsao de jogadas (depth) utilizado no MinMaxAB
    public Jogador(char simbolo,String nome, String estrat, int depth)
    {
        estrategia = estrat;
        this.nome = nome;
        this.simbolo = simbolo;
        random = new Randomico();
        minMaxAB = new MinMaxAB(depth);
        minMax = new MinMax();
    }

    public Jogador(char simbolo,String nome)
    {
        estrategia = "MinMaxAB";
        this.nome = nome;
        this.simbolo = simbolo;
        random = new Randomico();
        minMaxAB = new MinMaxAB(5);
        minMax = new MinMax();
    }

    public void setEstrategia(String e){
        estrategia = e;
    }
     /* metodo que chama o algoritmo aleatorio para fazer uma
     * jogada aleatoria, passando como referencia o tabuleiro atual
     * e retornando a posicao em que o jogadorPC deve jogar
     * @param Tabuleiro t
     * @return int jogaRandomico
     */
    public Tabuleiro joga(Tabuleiro t)
    {
        switch(estrategias.valueOf(estrategia)){
            case MinMax:
                System.out.println("MinMax!");
                int jogada = minMax.executa(t, this);
                //minMaxAB.playRoundDefault(t,simbolo,0);
                t.setPosicao(jogada, simbolo);
                return t;
            case MinMaxAB:
                System.out.println("MinMaxAB!");
                minMaxAB.playRoundAlphaBeta(t,simbolo,0);
                t.setPosicao(minMaxAB.bestMove, simbolo);
                return t;
            case Random:
                System.out.println("Random!");
                t.setPosicao(random.executa(t), simbolo);
                return t;
        }
        return null;
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
        return nome;
    }

}
