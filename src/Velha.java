/**
 *
 * @author pargles and stephano
 * @version 1.0
 */
public class Velha {
    public Jogador jogador1,jogador2;
    public Tabuleiro tabuleiro;

    public Velha(Jogador jogador1,Jogador jogador2)
    {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        tabuleiro = new Tabuleiro(jogador1.getSimbolo(),jogador2.getSimbolo());
    }

    public void setJogador1(Jogador jogador)
    {
       jogador1 = jogador;
       tabuleiro.simboloMAX = jogador.getSimbolo();
    }

    public void setJogador2(Jogador jogador)
    {
        jogador2 = jogador;
        tabuleiro.simboloMIN = jogador.getSimbolo();
    }
}
