/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Stephano
 */
public class Teste {

    public static void main(String []args){
        Jogador pc1 = new Jogador('X',"PC","MinMax", 5);
        Jogador pc2 = new Jogador('O',"PC","MinMax", 5);
        Velha game = new Velha(pc1,pc2);
        char[] tab = {'O',' ',' ',
                      ' ','X',' ',
                      'O',' ','X'};
        
        game.tabuleiro.tabuleiro = tab;
        printTable(game.tabuleiro);
        game.fazerJogadaPC(pc1);
        printTable(game.tabuleiro);
        game.fazerJogadaPC(pc2);
        printTable(game.tabuleiro);
        game.fazerJogadaPC(pc1);
        printTable(game.tabuleiro);
    }

    public static void printTable(Tabuleiro t){
        System.out.print("---------------\n");
        for (int i = 0; i < t.tabuleiro.length; i++){
            System.out.print(t.tabuleiro[i]+"|");
            if ((i == 2) || (i == 5) || (i == 8)){
                System.out.println();
            }
        }
    }
}
