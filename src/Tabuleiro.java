/**
 * @authors pargles and stephano
 * @version 2.0
 */
public class Tabuleiro {

    private int tamTabuleiro;
    char tabuleiro[];// tabuleiro do jogo ex: X O X O O X O X
    public char simboloMIN,simboloMAX; // os simbolos de qem vai ser min e qem vai ser max

    public Tabuleiro(char min,char max)
    {
        simboloMAX = max;
        simboloMIN = min;
        tamTabuleiro=3;//default 3
        tabuleiro = new char[tamTabuleiro*tamTabuleiro];
        inicializarTabuleiro();
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


    /* metodo que retorna o numero de colunas linha ou diagonais
     * livre para um jogador com um simbolo char simbolo
     * @param char simbolo
     * @return int calcularLivres
     */
    public int calcularLivres(char simbolo)
    {
        System.err.println("not yet developed");
        return 0;
    }
}
