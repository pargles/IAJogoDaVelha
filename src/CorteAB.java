/**
*
* @author Stephano
*/

import java.util.*;
public class CorteAB {

    int depth;
    int bestMove;
    static int open = 0;

    public CorteAB(){
        depth = 2;
        bestMove = -2;
    }


    //executa uma jogada do minmax alpha e beta
    public int playRound(Tabuleiro t,char player, int depth, int alpha, int beta){
        open = 0;
        return execute(t,player,depth,alpha,beta);
    }

    private int execute(Tabuleiro t,char player, int depth, int alpha, int beta){
        open++;
        if ((this.depth == depth) || t.tabuleiroEstaCheio() || t.existeVencedor('X')|| t.existeVencedor('O')){
            int vitmin = t.calcularVitoria(t.simboloMIN);
            if (vitmin == 20) return -20+depth;
            int vitmax = t.calcularVitoria(t.simboloMAX);
            if (vitmax == 20) return 20 - depth;
            return vitmax;
        }
        Tabuleiro aux;
        int val;
        int bestMove = -2;
        if (player == t.simboloMAX){
            for (int i = 0; (i < t.tabuleiro.length) && (alpha < beta); i++){ //gera os sucessores
                if (t.posicaoLivre(i)){
                    aux = t.clone();
                    aux.setPosicao(i, player);
                    val = execute(aux, t.simboloMIN, depth+1, alpha, beta);
                    if (val > alpha) {
                        alpha = val;
                        bestMove = i;
                        if (val >= 19)break;
                        if ((depth+1 == this.depth) && (val + depth+1 == 20))break;
                    }
                }
            }
            this.bestMove = bestMove;
            return alpha;
        }
        if (player == t.simboloMIN){
            for (int i = 0; (i < t.tabuleiro.length) && (alpha < beta); i++){ //gera os sucessores
                if (t.posicaoLivre(i)){
                    aux = t.clone();
                    aux.setPosicao(i, player);
                    val = execute(aux, t.simboloMAX, depth+1, alpha, beta);
                    if (val < beta){
                        beta = val;
                        bestMove = i;
                        if (val <= -19)break;
                        if ((depth+1 == this.depth) && (val - depth+1 == -20))break;
                    }
                }
            }
            this.bestMove = bestMove;
            return beta;
        }
        return -1;
    }
}