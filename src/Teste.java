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
        Jogador pc = new Jogador('X',"PC","MinMax", 5);
        Jogador eu = new Jogador('O',"EU");
        Velha game = new Velha(pc,eu);
        char[] tab = {'O',' ',' ',
                      ' ','X',' ',
                      'O',' ','X'};
        game.tabuleiro.tabuleiro = tab;
        game.fazerJogadaPC(pc);
        printTable(game.tabuleiro);
        game.tabuleiro.setPosicao(3, 'O');
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
