/**
 *
 * @author pargles and stephano
 * @version 2.0
 */
public class Velha {
    public Jogador jogador1,jogador2;
    public Tabuleiro tabuleiro;

    public Velha(Jogador jogador1,Jogador jogador2)
    {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        tabuleiro = new Tabuleiro();
    }


    public Tabuleiro fazerJogadaPC(Jogador jogador) {
        tabuleiro = jogador.joga(tabuleiro);
        return tabuleiro;
    }

    //computa uma jogada do player humano, verifica se a posicao eh valida
    public Tabuleiro computaJogadaHumano(Jogador humano, int pos){
        if (tabuleiro.posicaoLivre(pos)){
            tabuleiro.setPosicao(pos, humano.simbolo);
            return tabuleiro;
        }
        return null;
    }

    /* //TODO da pra otimizar essa funcao
     * metodo que vai verificar se o jogador atual
     * venceu a partidade depois do ultimo movimento
     * @param Jogador j
     * @return boolean existeVencedor
     */
    public boolean vencedor(Jogador j)
    {
        return tabuleiro.existeVencedor(j.getSimbolo());
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
