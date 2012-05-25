/**
 *
 * @author pargles and stephano
 * @version 2.0
 */
public class Velha {
    public Jogador jogador1,jogador2;
    public Tabuleiro tabuleiro;
    private enum heuristica{MinMax, CorteAB,Random;}

    public Velha(Jogador jogador1,Jogador jogador2)
    {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        tabuleiro = new Tabuleiro(jogador1.getSimbolo(),jogador2.getSimbolo());
    }


    public int fazerJogadaPC(Jogador jogador, String algoritmo) {
        int posicaoJogada=0;
        switch (heuristica.valueOf(algoritmo)) {
            case MinMax:
                System.err.println("MinMax search not yet developed");
                //computarJogada(posicaoJogada,jogador);
                break;
            case CorteAB:
                System.err.println("CorteAB search not yet developed");
                //computarJogada(posicaoJogada,jogador);
                break;
            case Random:
                posicaoJogada = jogador.jogaRandomico(tabuleiro);
                computarJogada(posicaoJogada,jogador);
                break;
        }
        
        tabuleiro.tabuleiro[posicaoJogada] = jogador.getSimbolo();//coloca o simbolo do jogador na posicao jogada
        return posicaoJogada;
    }

    /* metodo que computa a jogada, ou seja, coloca no tabuleiro
     * a posicao selecionada pelo jogador j
     * @param int posicao , Jogador j
     * @return void
     */
    public void computarJogada(int posicao,Jogador j)
    {
        tabuleiro.tabuleiro[posicao] = j.getSimbolo();
        System.out.println(j.getNome()+" tem posicoes livres: "+tabuleiro.calcularLivres(j.getSimbolo()));
    }

    /* //TODO da pra otimizar essa funcao
     * metodo que vai verificar se o jogador atual
     * venceu a partidade depois do ultimo movimento
     * @param Jogador j
     * @return boolean existeVencedor
     */
    public boolean existeVencedor(Jogador j)
    {
        boolean resultado=false;
        char tab[] = tabuleiro.tabuleiro;
        char simbolo = j.getSimbolo();
        if(tab[0]==simbolo && tab[0]==tab[1]&&tab[1]==tab[2]){resultado = true;}
        if(tab[3]==simbolo && tab[3]==tab[4]&&tab[4]==tab[5]){resultado = true;}
        if(tab[6]==simbolo && tab[6]==tab[7]&&tab[7]==tab[8]){resultado = true;}
        if(tab[0]==simbolo && tab[0]==tab[3]&&tab[3]==tab[6]){resultado = true;}
        if(tab[1]==simbolo && tab[1]==tab[4]&&tab[4]==tab[7]){resultado = true;}
        if(tab[2]==simbolo && tab[2]==tab[5]&&tab[5]==tab[8]){resultado = true;}
        if(tab[0]==simbolo && tab[0]==tab[4]&&tab[4]==tab[8]){resultado = true;}
        if(tab[6]==simbolo && tab[6]==tab[4]&&tab[4]==tab[2]){resultado = true;}
        return resultado;
    }

    public void setJogador1(Jogador jogador)
    {
       jogador1 = jogador;
       tabuleiro.simboloMAX = jogador.getSimbolo();
    }

    public void setJogador2(Jogador jogador)
    {
        jogador2 = jogador;
        tabuleiro.simboloMIN = jogador.getSimbolo();
    }
}
