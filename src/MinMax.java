
import java.util.Random;


/**
* @author pargles
* @version 2.0
*/
class MinMax {

    private int profundidadeMaxima;
    private int profundidade;
    private int posicao;
    private static int nodosAbertos;
    private int INFINITO = Integer.MAX_VALUE;
    char[] tabInicial = new char[9];

    public MinMax() {
        profundidadeMaxima =2;//default, e o maximo
        nodosAbertos=0;
    }

    public int executa(Tabuleiro t,Jogador j)
    {
        profundidade = 0;
        tabInicial = t.tabuleiro.clone();
        minmax(j.getSimbolo(),t);
        System.out.println("jogou em: "+posicao);
        return posicao;
    }

    private int minmax(char jogador, Tabuleiro tabuleiro) {

        //TODO colocar um controle se o tabuleiro esta cheio
        if (jogador == 'X') {
            return max(jogador, tabuleiro);

        } else//jogador ='O'
        {
            return min(jogador, tabuleiro);
        }
    }

    private int max(char jogador, Tabuleiro tabuleiro) {
        int valorPosicao = -INFINITO;
        if (tabuleiro.existeVencedor('O')) {profundidade -- ;return -INFINITO;}
        else if(tabuleiro.tabuleiroEstaCheio()){profundidade--;return 0;}
        else if (profundidade < profundidadeMaxima) {
            for (int i = 0; i < 9; i++) {
                if (tabuleiro.tabuleiro[i] == ' ') {
                    tabuleiro.tabuleiro[i] = jogador;
                    profundidade++;
                    int valor = minmax('O', tabuleiro);

                    if (valor > valorPosicao) {
                        posicao = i;
                        valorPosicao = valor;
                    }
                    tabuleiro.tabuleiro[i] = ' ';//tira a peca movimentada
                }
            }
        }
        valorPosicao = tabuleiro.calcularLivres('X') - tabuleiro.calcularLivres('O');
        profundidade--;
        return valorPosicao;
    }

    private int min(char jogador, Tabuleiro tabuleiro) {
        int valorPosicao = INFINITO;
        if (tabuleiro.existeVencedor('X')) {profundidade -- ;return INFINITO;}
        else if(tabuleiro.tabuleiroEstaCheio()){profundidade -- ;return 0;}
        else if (profundidade < profundidadeMaxima) {
            for (int i = 0; i < 9; i++) {
                if (tabuleiro.tabuleiro[i] == ' ') {
                    tabuleiro.tabuleiro[i] = jogador;
                    profundidade++;
                    int valor = minmax('X', tabuleiro);

                    if (valor < valorPosicao) {
                        posicao = i;
                        valorPosicao = valor;
                    }
                    tabuleiro.tabuleiro[i] = ' ';//tira a peca movimentada
                }
            }
        }
        valorPosicao = tabuleiro.calcularLivres('X') - tabuleiro.calcularLivres('O');
        profundidade--;
        return valorPosicao;
    }
}