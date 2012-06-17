/**
 *
 * @author pargles and stephano
 * @version 2.0
 */

import java.util.Random;

public class Velha {
    public Jogador jogador1,jogador2;
    public Tabuleiro tabuleiro;
    Random r = new Random(System.currentTimeMillis());

    public Velha(Jogador jogador1,Jogador jogador2)
    {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        tabuleiro = new Tabuleiro();
    }


    public Tabuleiro fazerJogadaPC(Jogador jogador) {
        tabuleiro = jogador.joga(tabuleiro);
        if(tabuleiro.livres >= 7)   //primeira jogada de um jogador tenta ser aleatorizada sem afetar na inteligencia do algoritmo
        {
            int lastPos = tabuleiro.lastPos;
            if (lastPos != 4){  //se nao jogou no meio
                tabuleiro.tabuleiro[lastPos] = ' ';
                int n = r.nextInt(3);
                if ((lastPos == 0) || (lastPos == 2) || (lastPos == 6) || (lastPos == 8)){ //se escolheu uma extremidade sorteia entre as extremdiades
                    if ( n == 0){tabuleiro.tabuleiro[0] = jogador.simbolo; tabuleiro.lastPos = 0;}
                    if ( n == 1){tabuleiro.tabuleiro[2] = jogador.simbolo;tabuleiro.lastPos = 2;}
                    if ( n == 2){tabuleiro.tabuleiro[6] = jogador.simbolo;tabuleiro.lastPos = 6;}
                    if ( n == 3){tabuleiro.tabuleiro[8] = jogador.simbolo;tabuleiro.lastPos = 8;}
                    return tabuleiro;
                }
                if ( n == 0){tabuleiro.tabuleiro[1] = jogador.simbolo;tabuleiro.lastPos = 1;}
                if ( n == 1){tabuleiro.tabuleiro[3] = jogador.simbolo;tabuleiro.lastPos = 3;}
                if ( n == 2){tabuleiro.tabuleiro[5] = jogador.simbolo;tabuleiro.lastPos = 5;}
                if ( n == 3){tabuleiro.tabuleiro[7] = jogador.simbolo;tabuleiro.lastPos = 7;}
                return tabuleiro;
            }
        }
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
