/**
 * @authors pargles and stephano
 * @version 4.0
 */
public class Tabuleiro {

    public int tamTabuleiro;
    char tabuleiro[];// tabuleiro do jogo ex: X O X O O X O X
    public char simboloMIN,simboloMAX; // os simbolos de qem vai ser min e qem vai ser max

    public Tabuleiro()
    {
        tamTabuleiro=3;//default 3
        tabuleiro = new char[tamTabuleiro*tamTabuleiro];
        inicializarTabuleiro();
    }

    public Tabuleiro(char[] vetor)
    {
        tamTabuleiro=3;//default 3
        tabuleiro = new char[tamTabuleiro*tamTabuleiro];
        for(int i=0;i<vetor.length;i++)
        {
            tabuleiro[i]=vetor[i];
        }
    }

    /* metodo que preenche todo tabuleiro de char com posicoes espacos ' '
     * @param void
     * @return void
     */
    public void inicializarTabuleiro()
    {
        for(int i=0;i<tamTabuleiro*tamTabuleiro;i++)
        {
            tabuleiro[i]=' ';//preenche o tabuleiro com espacos
        }
    }

    /* metodo que avalia se uma posicao esta livre
     * retorna true caso verdadeiro e false c.c
     * @param int posicao
     * @return boolean estaLivre
     */
    public boolean posicaoLivre(int posicao)
    {
        return tabuleiro[posicao]==' '? true:false;
    }

    /* metodo que verifica se o tabuleiro esta cheio
     * necessario pois pode nao haver vencedor porem
     * nao tem mais jogadas a serem feitas
     * @param void
     * @return boolean tabuleiroEstaCheio
     */
    public boolean tabuleiroEstaCheio()
    {
        boolean cheio = true;
        for(int i=0;i<tabuleiro.length;i++)
        {
            if(tabuleiro[i]==' ')//se tem algum especao ainda nao esta cheio
            {
                cheio = false;
                i =tabuleiro.length;//ja descobriu que nao esta cheio, nao precisa continuar
            }
        }
        return cheio;
    }

    /* metodo que verifica se o tabuleiro esta vazio
     * necessario pois quando o PC comeca jogando
     * ele deve sortear uma posicao e nao escolher sempre
     * a mesma
     * @param void
     * @return boolean tabuleiroEstaCheio
     */
    public boolean tabuleiroEstaVazio() {
        boolean vazio = true;
        for (int i = 0; i < tabuleiro.length; i++) {
            if (tabuleiro[i] != ' ')//se tem algum especao ainda nao esta cheio
            {
                vazio = false;
                i = tabuleiro.length;//ja descobriu que nao esta cheio, nao precisa continuar
            }
        }
        return vazio;
    }


    /* metodo que retorna o numero de colunas linha ou diagonais
     * livre para um jogador com um simbolo char simbolo
     * @param char simbolo
     * @return int calcularLivres
     */
    public int calcularLivres(char simbolo) {
        int livres = 8;// 3 colunas + 3 linhas + 2 diagonais num tabuleiro 3x3
        int n = tamTabuleiro;
        //primeiro laco calcula linhas
        for (int i=0;i<=n*(n-1);i+=n){
            for(int j=i;j<i+n;j++){
                if(tabuleiro[j]!=simbolo && tabuleiro[j]!=' '){//se nao for simbolo ou branco entao e peca do oponente
                    livres--;
                    j=n*n;//apenas um numero grande para nao entrar novamente no for
                }
            }
        }
        //segundo laco calcula colunas
        for (int i=0;i<n;i++){
            for(int j=i;j<=(n*(n-1))+i;j+=n){
                if(tabuleiro[j]!=simbolo && tabuleiro[j]!=' '){//se nao for simbolo ou branco entao e peca do oponente
                    livres--;
                    j=n*n;//apenas um numero grande para nao entrar novamente no for
                }
            }
        }
        //terceiro laco calcula diagonal principal
        for (int i = 0; i < n * n; i+=n+1) {
            if (tabuleiro[i] != simbolo && tabuleiro[i] != ' ') {//se nao for simbolo ou branco entao e peca do oponente
                livres--;
                i = n * n;//apenas um numero grande para nao entrar novamente no for
            }
        }

        //quarto laco calcula diagonal secundaria
        for (int i = n-1; i <= n * n -n; i+=n-1) {
            if (tabuleiro[i] != simbolo && tabuleiro[i] != ' ') {//se nao for simbolo ou branco entao e peca do oponente
                livres--;
                i = n * n;//apenas um numero grande para nao entrar novamente no for
            }
        }
        return livres;
    }
}
