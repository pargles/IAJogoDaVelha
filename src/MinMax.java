
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
    private static Random random = new Random( System.currentTimeMillis());

    public MinMax() {
        profundidadeMaxima =8;//default, e o maximo
        nodosAbertos=0;
    }

    public int executa(Tabuleiro t,Jogador j)
    {
        profundidade = 0;
        if(t.tabuleiroEstaVazio())//se o PC inicia jogando, ou seja, o tabuleiro esta vazio, o PC deve sortear uma posicao
        {
            return random.nextInt(8);
        }
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

    private int max(char jogador, Tabuleiro tabuleiro)
    {
        int melhorValor = tabuleiro.calcularLivres('X') - tabuleiro.calcularLivres('O');
        if (tabuleiro.existeVencedor())
        {
            melhorValor = -INFINITO;
        }
        if (profundidade < profundidadeMaxima && !tabuleiro.tabuleiroEstaCheio()) {
            for (int i = 0; i < 9; i++) {
                if (tabuleiro.tabuleiro[i] == ' ') {
                    tabuleiro.tabuleiro[i] = jogador;
                    profundidade++;
                    int valor = minmax('O', tabuleiro);

                    if (valor > melhorValor) {
                        posicao = i;
                        melhorValor = valor;
                    }
                    tabuleiro.tabuleiro[i] = ' ';//tira a peca movimentada
                }
            }
        }
        profundidade--;
        return melhorValor;
    }

    private int min(char jogador, Tabuleiro tabuleiro)
    {
        int melhorValor = tabuleiro.calcularLivres('X') - tabuleiro.calcularLivres('O');
        if (tabuleiro.existeVencedor())
        {
            melhorValor = INFINITO;
        }
        if (profundidade < profundidadeMaxima && !tabuleiro.tabuleiroEstaCheio()) {
            for (int i = 0; i < 9; i++) {
                if (tabuleiro.tabuleiro[i] == ' ') {
                    tabuleiro.tabuleiro[i] = jogador;
                    profundidade++;
                    int valor = minmax('X', tabuleiro);

                    if (valor < melhorValor) {
                        posicao =i;
                        melhorValor = valor;
                    }
                    tabuleiro.tabuleiro[i] = ' ';//tira a peca movimentada
                }
            }
        }
        profundidade--;
        return melhorValor;
    }
}