/**
 *
 * @author pargles and stephano
 * @version 2.0
 */
public class Velha {
    public Jogador jogador1,jogador2;
    public Tabuleiro tabuleiro;

    public Velha(Jogador jogador1,Jogador jogador2)
    {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        tabuleiro = new Tabuleiro(jogador1.getSimbolo(),jogador2.getSimbolo());
    }

    /* Metodo que faz a jogado do computador e retorna
     * um inteiro indicando a posicao onde foi jogado
     * @param void
     * @return int fazerJogada
     */
    public int fazerJogadaPC()
    {
        int posicaoJogada;
        if(jogador1.getNome().equals("PC"))
        {
            posicaoJogada = jogador1.jogaRandomico(tabuleiro);
            tabuleiro.tabuleiro[posicaoJogada] = jogador1.getSimbolo();
            return posicaoJogada;
        }
        else//senao o jogador 2 e o PC
        {
            posicaoJogada = jogador2.jogaRandomico(tabuleiro);
            tabuleiro.tabuleiro[posicaoJogada] = jogador2.getSimbolo();
            return posicaoJogada;
        }
    }

    /* metodo que computa a jogada, ou seja, coloca no tabuleiro
     * a posicao selecionada pela pessoa
     * @param int posicao
     * @return void
     */
    public void computarJogadaPessoa(int posicao)
    {
        tabuleiro.tabuleiro[posicao] = jogador1.getSimbolo();
    }

    public void setJogador1(Jogador jogador)
    {
       jogador1 = (JogadorPC) jogador;
       tabuleiro.simboloMAX = jogador.getSimbolo();
    }

    public void setJogador2(Jogador jogador)
    {
        jogador2 = (JogadorPC) jogador;
        tabuleiro.simboloMIN = jogador.getSimbolo();
    }
}
