/**
 * @authors pargles and stephano
 * @version 1.0
 */
public class Tabuleiro {

    private int tamTabuleiro;
    char tabuleiro[];// tabuleiro do jogo ex: X O X O O X O X
    private char simboloMIN,simboloMAX; // os simbolos de qem vai ser min e qem vai ser max

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
    public boolean estaLivre(int posicao)
    {
        return tabuleiro[posicao]==' '? true:false;
    }


    /* metodo que retorna o max do jogador simboloMax
     * verifica as colunas linhas e diagonais livres para max jogar
     * @param void
     * @return int MAX
     */
    public int MAX()
    {
        System.err.println("not yet developed");
        return 0;
    }

    /* metodo que retorna o min do jogador simboloMIN
     * verifica as colunas linhas e diagonais livres para min jogar
     * @param void
     * @return int MIN
     */
    public int MIN()
    {
        System.err.println("not yet developed");
        return 0;
    }

}
