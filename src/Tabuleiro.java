/**
 * @authors pargles and stephano
 * @version 3.0
 */
public class Tabuleiro {

    private int tamTabuleiro;
    char tabuleiro[];// tabuleiro do jogo ex: X O X O O X O X
    public char simboloMIN = 'O',simboloMAX = 'X'; // os simbolos de qem vai ser min e qem vai ser max
    int vit; //nao é calculado para todos os nodos abertos do minmax, apenas para as folhas
    int livres; //numero de posicoes livres
    int lastPos; //ultima posicao jogada

    public Tabuleiro()
    {
        tamTabuleiro=3;//default 3
        tabuleiro = new char[tamTabuleiro*tamTabuleiro];
        inicializarTabuleiro();
        vit = 0;
        livres = tamTabuleiro*tamTabuleiro;
        lastPos = -1;
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

    public Tabuleiro clone(){
        Tabuleiro aux = new Tabuleiro();
        aux.livres = this.livres;
        aux.simboloMAX = this.simboloMAX;
        aux.simboloMIN = this.simboloMIN;
        aux.tabuleiro = this.tabuleiro.clone();
        aux.tamTabuleiro = this.tamTabuleiro;
        aux.vit = this.vit;
        return aux;
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

    /*seta uma posicao do tabuleiro com um simbolo
     * retorna true se atingiu sucesso ou false se a posicao nao estava vazia
     */
    public boolean setPosicao(int pos, char simb){
        if  (posicaoLivre(pos)){
            tabuleiro[pos] = simb;
            livres--;
            lastPos = pos;
            return true;
        }
        return false;
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


    public boolean existeVencedor(){
        boolean resultado=false;
        if(tabuleiro[0]=='X' && tabuleiro[0]==tabuleiro[1]&&tabuleiro[1]==tabuleiro[2]){resultado = true;}
        if(tabuleiro[3]=='X' && tabuleiro[3]==tabuleiro[4]&&tabuleiro[4]==tabuleiro[5]){resultado = true;}
        if(tabuleiro[6]=='X' && tabuleiro[6]==tabuleiro[7]&&tabuleiro[7]==tabuleiro[8]){resultado = true;}
        if(tabuleiro[0]=='X' && tabuleiro[0]==tabuleiro[3]&&tabuleiro[3]==tabuleiro[6]){resultado = true;}
        if(tabuleiro[1]=='X' && tabuleiro[1]==tabuleiro[4]&&tabuleiro[4]==tabuleiro[7]){resultado = true;}
        if(tabuleiro[2]=='X' && tabuleiro[2]==tabuleiro[5]&&tabuleiro[5]==tabuleiro[8]){resultado = true;}
        if(tabuleiro[0]=='X' && tabuleiro[0]==tabuleiro[4]&&tabuleiro[4]==tabuleiro[8]){resultado = true;}
        if(tabuleiro[6]=='X' && tabuleiro[6]==tabuleiro[4]&&tabuleiro[4]==tabuleiro[2]){resultado = true;}

        if(tabuleiro[0]=='O' && tabuleiro[0]==tabuleiro[1]&&tabuleiro[1]==tabuleiro[2]){resultado = true;}
        if(tabuleiro[3]=='O' && tabuleiro[3]==tabuleiro[4]&&tabuleiro[4]==tabuleiro[5]){resultado = true;}
        if(tabuleiro[6]=='O' && tabuleiro[6]==tabuleiro[7]&&tabuleiro[7]==tabuleiro[8]){resultado = true;}
        if(tabuleiro[0]=='O' && tabuleiro[0]==tabuleiro[3]&&tabuleiro[3]==tabuleiro[6]){resultado = true;}
        if(tabuleiro[1]=='O' && tabuleiro[1]==tabuleiro[4]&&tabuleiro[4]==tabuleiro[7]){resultado = true;}
        if(tabuleiro[2]=='O' && tabuleiro[2]==tabuleiro[5]&&tabuleiro[5]==tabuleiro[8]){resultado = true;}
        if(tabuleiro[0]=='O' && tabuleiro[0]==tabuleiro[4]&&tabuleiro[4]==tabuleiro[8]){resultado = true;}
        if(tabuleiro[6]=='O' && tabuleiro[6]==tabuleiro[4]&&tabuleiro[4]==tabuleiro[2]){resultado = true;}
        return resultado;
    }

    /* metodo que retorna o progresso que um jogador esta para vencer para cada linha/coluna e diagonal
     * @param char simbolo
     * @return int calcularLivres
     */
    public int calcularVitoria(char simbolo) {
        int prog = 0;
        int vit = 0;
        int n = tamTabuleiro;
        //primeiro laco calcula linhas
        for (int i = 0; i < n*n ; i = i + n){
            prog = 0;
            for(int j = i; j < i+n; j++){
                if (tabuleiro[j] == simbolo){   //se encontrou o simbolo incrementa o progresso
                    prog++;
                }else if (tabuleiro[j] != ' '){ //se for um espaço segue buscando, se for o simbolo do oponente, nao tem progresso nessa linha, sai do laço
                    prog = 0;
                    break;
                }
            }
            if (prog == 3) return 20;
            vit = vit + prog;
        }
        //segundo laco calcula colunas
        for (int i = 0; i < n ; i++){
            prog = 0;
            for(int j = i; j < n*n; j = j+n){
                if (tabuleiro[j] == simbolo){   //se encontrou o simbolo incrementa o progresso
                    prog++;
                }else if (tabuleiro[j] != ' '){ //se for um espaço segue buscando, se for o simbolo do oponente, nao tem progresso nessa linha, sai do laço
                    prog = 0;
                    break;
                }
            }
            if (prog == 3) return 20;
            vit = vit + prog;
        }
        //terceiro laco calcula diagonal principal
        prog = 0;
        for (int i = 0; i < n * n; i+=n+1) {
            if (tabuleiro[i] == simbolo){   //se encontrou o simbolo incrementa o progresso
                prog++;
            }else if (tabuleiro[i] != ' '){ //se for um espaço segue buscando, se for o simbolo do oponente, nao tem progresso nessa linha, sai do laço
                prog = 0;
                break;
            }
            if (prog == 3) return 20;
        }
        vit = vit + prog;
        //quarto laco calcula diagonal secundaria
        prog = 0;
        for (int i = n-1; i <= n * n -n; i+=n-1) {
            if (tabuleiro[i] == simbolo){   //se encontrou o simbolo incrementa o progresso
                prog++;
            }else if (tabuleiro[i] != ' '){ //se for um espaço segue buscando, se for o simbolo do oponente, nao tem progresso nessa linha, sai do laço
                prog = 0;
                break;
            }
            if (prog == 3) return 20;
        }
        vit = vit + prog;
        return vit;
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

        public void printTable(){
        System.out.print("---------------\n");
        for (int i = 0; i < tabuleiro.length; i++){
            System.out.print(tabuleiro[i]+"|");
            if ((i == 2) || (i == 5) || (i == 8)){
                System.out.println();
            }
        }
    }

}
