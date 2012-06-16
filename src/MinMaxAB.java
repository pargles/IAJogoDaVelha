/**
 *
 * @author Stephano
 */

import java.util.*;
public class MinMaxAB {

    int alpha;
    int beta;
    int depth;
    int bestMove;
    //ArrayList<ArrayList<Tabuleiro>> lista = new ArrayList<ArrayList<Tabuleiro>>();
    public MinMaxAB(int maxDepth){
        alpha = Integer.MIN_VALUE;
        beta = Integer.MAX_VALUE;
        depth = maxDepth;
        bestMove = -1;
    }

    /*
     * executa uma partida inteira utilizando minmax padrao
     */
    public ArrayList<Tabuleiro> executeDefault(){
        return new ArrayList<Tabuleiro>();
    }

    //executa uma jogada do minmax padrao
    public Tabuleiro playRoundDefault(Tabuleiro t,char player, int depth){
        return new Tabuleiro();
    }

    /*
     * executa uma partida inteira utilizando minmax alpha e beta
     */
    public ArrayList<Tabuleiro> executeAlphaBeta(){
        return new ArrayList<Tabuleiro>();
    }

    //executa uma jogada do minmax alpha e beta
    public int playRoundAlphaBeta(Tabuleiro t,char player, int depth){
        alpha = Integer.MIN_VALUE;
        beta = Integer.MAX_VALUE;
        bestMove = -1;
        return minmaxAB(t,player,depth);
    }

    private int minmaxAB(Tabuleiro t,char player, int depth){
        //Teste.printTable(t);
        /*if (depth >= lista.size()){
            lista.add(new ArrayList<Tabuleiro>());
        }
        lista.get(depth).add(t);*/
        //System.out.println("Chamou!"+depth);
        if ((this.depth == depth) || t.tabuleiroEstaCheio() || t.existeVencedor('X')|| t.existeVencedor('O')){
            //if (t.existeVencedor()) System.out.println("EXISTE VENCEDOR!");
            if (t.calcularVitoria(t.simboloMIN) == Integer.MAX_VALUE) return Integer.MIN_VALUE;
            t.calcularVitoria(t.simboloMAX);
            return t.vit;
        }
        Tabuleiro aux;
        int val;
        if (player == t.simboloMAX){
            for (int i = 0; (i < t.tabuleiro.length) && (alpha <= beta); i++){ //gera os sucessores
                if (t.posicaoLivre(i)){
                    aux = t.clone();
                    aux.setPosicao(i, player);
                    val = playRoundAlphaBeta(aux, t.simboloMIN, depth+1);
                    if (val > alpha) {
                        alpha = val;
                        bestMove = i;
                    }
                }
            }
            return alpha;
        }
        if (player == t.simboloMIN){
            for (int i = 0; (i < t.tabuleiro.length) && (alpha <= beta); i++){ //gera os sucessores
                if (t.posicaoLivre(i)){
                    aux = t.clone();
                    aux.setPosicao(i, player);
                    val = playRoundAlphaBeta(aux, t.simboloMAX, depth+1);
                    if (val < beta){
                        beta = val;
                        bestMove = i;
                    }
                }
            }
            return beta;
        }
        return -1;
    }
    public static void printTree(ArrayList<ArrayList<Tabuleiro>> l){
        for (int i = 0; i < l.size(); i++){
            System.out.println("NIVEL "+i+":\n");
            for (int j = 0; j < l.get(i).size(); j++){
                Teste.printTable(l.get(i).get(j));
            }
        }
    }
    private void printSucs(ArrayList<Tabuleiro> l){
        System.out.println("--------------------");
        for (int i = 0; i < l.size(); i++){
            Teste.printTable(l.get(i));
        }
    }

}
