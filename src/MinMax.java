import java.util.Random;
/**
* @author pargles
* @version 2.0
*/
class MinMax {

    public int profundidadeMaxima;
    private int profundidade;
    private int posicao;
    public int nodosAbertos;
    private char[] vetorSolucao= new char[9];
    private int INFINITO = Integer.MAX_VALUE-1;
    char[] tabInicial = new char[9];
    int validosTabInicial;
    Random r = new Random(System.currentTimeMillis());

    public MinMax() {
        profundidadeMaxima =8;//default, e o maximo
        nodosAbertos=0;
    }

    public int executa(Tabuleiro t,Jogador j)
    {
        profundidade = 0;
        nodosAbertos=0;       
        tabInicial = t.tabuleiro.clone();
        validosTabInicial = t.elementosValidos();
        if(t.tabuleiroEstaVazio())//se o PC inicia jogando, ou seja, o tabuleiro esta vazio, o PC deve sortear uma posicao
        {
            return r.nextInt(9);
        }
        minmax(j.getSimbolo(),t);
        posicao = findPosition();
        //System.out.println("jogou em: "+posicao+ " nodos abertos: "+nodosAbertos);
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
        int valorPosicao = -Integer.MAX_VALUE;
        if (tabuleiro.existeVencedor('O')) {profundidade -- ;return -INFINITO;}
        else if(tabuleiro.tabuleiroEstaCheio()){profundidade--;return 0;}
        if (profundidade < profundidadeMaxima) {
            for (int i = 0; i < 9; i++) {
                if (tabuleiro.tabuleiro[i] == ' ') {
                    tabuleiro.tabuleiro[i] = jogador;
                    profundidade++;
                    nodosAbertos++;
                    int valor = minmax('O', tabuleiro);

                    if (valor > valorPosicao) {
                        posicao = i;
                        if(tabuleiro.elementosValidos() == validosTabInicial+1)
                        {
                            //System.out.println("PODE SER A SOLUCAO: ");
                            //tabuleiro.printTable();
                            vetorSolucao = tabuleiro.tabuleiro.clone();
                        }
                        valorPosicao = valor;
                    }
                    tabuleiro.tabuleiro[i] = ' ';//tira a peca movimentada
                }
            }
         }
        else
        {
             valorPosicao = tabuleiro.calcularLivres('X') - tabuleiro.calcularLivres('O');
        }
        profundidade--;
        return valorPosicao;
    }

    private int min(char jogador, Tabuleiro tabuleiro) {
        int valorPosicao = Integer.MAX_VALUE;
        if (tabuleiro.existeVencedor('X')) {profundidade -- ;return INFINITO;}
        else if(tabuleiro.tabuleiroEstaCheio()){profundidade -- ;return 0;}
        else if (profundidade < profundidadeMaxima) {
            for (int i = 0; i < 9; i++) {
                if (tabuleiro.tabuleiro[i] == ' ') {
                    tabuleiro.tabuleiro[i] = jogador;
                    profundidade++;
                    nodosAbertos++;
                    int valor = minmax('X', tabuleiro);
                    if (valor < valorPosicao) {
                        posicao = i;
                        if(tabuleiro.elementosValidos() == validosTabInicial+1)
                        {
                            //System.out.println("PODE SER A SOLUCAO: ");
                            //tabuleiro.printTable();
                            vetorSolucao = tabuleiro.tabuleiro.clone();
                        }
                        valorPosicao = valor;
                    }
                    tabuleiro.tabuleiro[i] = ' ';//tira a peca movimentada
                }
            }
        }
        else
        {
             valorPosicao = tabuleiro.calcularLivres('X') - tabuleiro.calcularLivres('O');
        }
        profundidade--;
        return valorPosicao;
    }
    
    public int findPosition() {
        for (int i = 0; i < tabInicial.length; i++) {
            if (tabInicial[i] != vetorSolucao[i]) {
                posicao = i;
                return i;
            }
        }
        return posicao;
    }
}
