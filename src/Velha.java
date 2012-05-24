/**
 *
 * @author pargles and stephano
 * @version 2.0
 */
public class Velha {
    public Jogador jogador1,jogador2;
    public Tabuleiro tabuleiro;
    private enum heuristica{MinMax, CorteAB,Random;}

    public Velha(Jogador jogador1,Jogador jogador2)
    {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        tabuleiro = new Tabuleiro(jogador1.getSimbolo(),jogador2.getSimbolo());
    }


    public int fazerJogadaPC(Jogador jogador, String algoritmo) {
        int posicaoJogada=0;
        switch (heuristica.valueOf(algoritmo)) {
            case MinMax:
                System.err.println("MinMax search not yet developed");
                break;
            case CorteAB:
                System.err.println("CorteAB search not yet developed");
                break;
            case Random:
                posicaoJogada = jogador.jogaRandomico(tabuleiro);
                break;
        }
        
        tabuleiro.tabuleiro[posicaoJogada] = jogador.getSimbolo();//coloca o simbolo do jogador na posicao jogada
        return posicaoJogada;
    }

    /* metodo que computa a jogada, ou seja, coloca no tabuleiro
     * a posicao selecionada pela pessoa
     * @param int posicao
     * @return void
     */
    public void computarJogadaPessoa(int posicao)
    {
        tabuleiro.tabuleiro[posicao] = jogador1.getSimbolo();
    }

    /* metodo que vai verificar se o jogador atual
     * venceu a partidade depois do ultimo movimento
     * @param void
     * @void
     */
    public boolean existeVencedor()
    {
        return false;
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
