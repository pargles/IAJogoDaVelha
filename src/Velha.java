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
        boolean resultado=false;
        char tab[] = tabuleiro.tabuleiro;
        char simbolo = j.getSimbolo();
        if(tab[0]==simbolo && tab[0]==tab[1]&&tab[1]==tab[2]){resultado = true;}
        if(tab[3]==simbolo && tab[3]==tab[4]&&tab[4]==tab[5]){resultado = true;}
        if(tab[6]==simbolo && tab[6]==tab[7]&&tab[7]==tab[8]){resultado = true;}
        if(tab[0]==simbolo && tab[0]==tab[3]&&tab[3]==tab[6]){resultado = true;}
        if(tab[1]==simbolo && tab[1]==tab[4]&&tab[4]==tab[7]){resultado = true;}
        if(tab[2]==simbolo && tab[2]==tab[5]&&tab[5]==tab[8]){resultado = true;}
        if(tab[0]==simbolo && tab[0]==tab[4]&&tab[4]==tab[8]){resultado = true;}
        if(tab[6]==simbolo && tab[6]==tab[4]&&tab[4]==tab[2]){resultado = true;}
        return resultado;
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
