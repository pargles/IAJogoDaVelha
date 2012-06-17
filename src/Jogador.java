
import java.awt.Color;

/**
 * @authors pargles and stephano
 * @version 2.0
 */
public class Jogador {

    private String nome;
    char simbolo;//simbolo do jogador
    private Randomico random;
    private int nodosAbertos;
    CorteAB minMaxAB;
    MinMax minMax;
    private enum estrategias{MinMax, CorteAB,Random;}
    private String estrategia;
    private Color cor = Color.RED;//default

    //os jogadores sao criados com uma estrategia de jogo associada e um nivel de previsao de jogadas (depth) utilizado no CorteAB
    public Jogador(char simbolo,String nome, String estrat)
    {
        estrategia = estrat;
        this.nome = nome;
        this.simbolo = simbolo;
        random = new Randomico();
        minMaxAB = new CorteAB();
        minMax = new MinMax();
        nodosAbertos =0;//default para jogador humano
    }

    public Jogador(char simbolo,String nome)
    {
        estrategia = "CorteAB";
        this.nome = nome;
        this.simbolo = simbolo;
        random = new Randomico();
        minMaxAB = new CorteAB();
        minMax = new MinMax();
        nodosAbertos=0;//default para jogador humano
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
                //System.out.println("MinMax!");
                t.livres=0;
                int jogada = minMax.executa(t, this);
                nodosAbertos = minMax.nodosAbertos;
                t.setPosicao(jogada, simbolo);
                return t;
            case CorteAB:
                //System.out.println("CorteAB!");
                minMaxAB.playRound(t,simbolo,0, Integer.MIN_VALUE, Integer.MAX_VALUE);
                //nodosAbertos =;
                t.setPosicao(minMaxAB.bestMove, simbolo);
                return t;
            case Random:
                //System.out.println("Random!");
                nodosAbertos=1;//somente sorteia 1
                t.setPosicao(random.executa(t), simbolo);
                return t;
        }
        return null;
    }

    public void setSimbolo(char simbolo)
    {
        this.simbolo = simbolo;
    }

    public void setCor(Color cor)
    {
        this.cor = cor;
    }

    public void setProfundidade(int depth)
    {
        this.minMaxAB.depth = depth;
        this.minMax.profundidadeMaxima= depth;
    }

    public char getSimbolo()
    {
        return simbolo;
    }

    public String getNome()
    {
        return nome;
    }

    public Color getCor()
    {
        return cor;
    }

    public int getNodosAbertos()
    {
        return nodosAbertos;
    }

}
