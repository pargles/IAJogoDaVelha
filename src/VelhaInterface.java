
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author pargles
 * @version 6.0
 */


public class VelhaInterface extends JFrame{
    private enum heuristica{MinMax, MinMaxAB,Random;}
    private String tipoBusca ="MinMax";//default
    private JComboBox listaAlgoritmos;//para colocar os algoritmos
    private JButton[] botoes;//contem os botoes da matriz 3x3 do jogo
    private JButton iniciar;
    private JRadioButton pcXvc, vcXpc,pcXpc,vcXele;
    private ButtonGroup group;//junta todos os radio buttons, pcxeu, euxpc ...
    private JPanel painelJogadas,painelConfiguracoes;
    private JTextField profundidadeMaxima;
    private JLabel labelProfundidade,labelTempo,labelVazio;
    private int IDbotaoClicado;//armazeba o numero do botao clicado da matriz de botoes
    Velha jogoDaVelha;
    private long tempo;//armazena tempo que demorou para calcular em segundos
    private int TEMPO = 1000;//1 segundo entre as jogadas do PC
    private Jogador jogadorAtual;
    private Thread processo;
    JSplitPane split;

  //metodo construtor
  public VelhaInterface()
  {
      
      jogoDaVelha = new Velha(new Jogador('X',"VC", tipoBusca),new Jogador('O',"PC", tipoBusca));//default vcXpc
      jogadorAtual = jogoDaVelha.jogador1;
      Dimension boardSize = new Dimension(300, 300);
      setTitle("Jogo da Velha");
      painelJogadas = new JPanel();
      painelJogadas.setBackground(Color.black);
      painelConfiguracoes = new JPanel();
      painelJogadas.setLayout(new GridLayout(3, 3));// 3x3 botoes, 3 horizontal e 3 vertical
      painelConfiguracoes.setLayout(new GridLayout(10,0));// 5 botoes verticalmente
      botoes = new JButton[9];
      painelJogadas.setPreferredSize(boardSize);
      insereBotoesNoLayout();//metodo que insere os 3x3 botoes no painelJogadas
      insereConfiguracoes();
      //split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,painelConfiguracoes,painelJogadas);
      //add(split, BorderLayout.CENTER);
      getContentPane().add(painelJogadas, BorderLayout.EAST);
      getContentPane().add(painelConfiguracoes, BorderLayout.WEST);

  }

  /* metodo que instancia os nove botoes da matriz de botoes
   * e insere eles no painel painelJogadas
   * @param void
   * @return void
   */
    public void insereBotoesNoLayout() {
        for (int i = 0; i < 9; i++) {
            botoes[i] = new JButton();
            //botoes[i].setFont(new java.awt.Font("URW Chancery L", 0, 24));
            botoes[i].setFont(new java.awt.Font("Arial", 0, 24));
            botoes[i].setName(String.valueOf(i));//seta o ID do botao
            botoes[i].setBackground(Color.WHITE);
            botoes[i].setOpaque(true);//botao fica opaco
            botoes[i].setEnabled(false);
            botoes[i].setText("");
            botoes[i].setBorder(javax.swing.BorderFactory.createEtchedBorder());//seta borda mais bonita
            botoes[i].addActionListener(new clicouMatrizDeBotoes());//coloca evento vijiando ele
            painelJogadas.add(botoes[i]);//adiciona o botao no painel das jogadas
        }
    }

    /* metodo que coloca todos os botoes do tabuleiro com o char vazio
     * @param boolean mostrar
     * @return void
     */
    public void limparBotoesMatriz(boolean mostrar) {
        for (int i = 0; i < 9; i++) {
            botoes[i].setEnabled(mostrar);
            botoes[i].setText("");
         }
    }

    /* metodo que insere todos os botoes no Painel de configuracoes
     * @param void
     * @return void
     */
    public void insereConfiguracoes() {
        iniciar = new JButton("Iniciar");
        iniciar.setFocusable(false);
        iniciar.addActionListener(new botaoIniciar());

        labelProfundidade = new JLabel("Profundidade:");
        labelTempo = new JLabel("tempo: ");
        labelVazio = new JLabel("");

        profundidadeMaxima = new JTextField();
        profundidadeMaxima.setText("2");//default
        //profundidadeMaxima.setEnabled(false);//so vai aparecer quando o

        listaAlgoritmos = new JComboBox();
        listaAlgoritmos.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"MinMax", "CorteAB","Random"}));
        listaAlgoritmos.addActionListener(new selecionaAlgoritmo());

        pcXvc = new JRadioButton("PC X VC");
        pcXvc.setFocusable(false);
        pcXvc.addActionListener(new tipoDoJogo());

        vcXpc = new JRadioButton("VC X PC");
        vcXpc.setSelected(true);//default
        vcXpc.setFocusable(false);
        vcXpc.addActionListener(new tipoDoJogo());

        pcXpc = new JRadioButton("PC X PC");
        pcXpc.setFocusable(false);
        pcXpc.addActionListener(new tipoDoJogo());

        vcXele = new JRadioButton("VC X ELE");
        vcXele.setFocusable(false);
        vcXele.addActionListener(new tipoDoJogo());

        group = new ButtonGroup();
        group.add(pcXvc);
        group.add(vcXpc);
        group.add(pcXpc);
        group.add(vcXele);
    
        painelConfiguracoes.add(pcXvc);
        painelConfiguracoes.add(vcXpc);
        painelConfiguracoes.add(pcXpc);
        painelConfiguracoes.add(vcXele);
        painelConfiguracoes.add(listaAlgoritmos);
        painelConfiguracoes.add(labelVazio);
        painelConfiguracoes.add(iniciar);
        painelConfiguracoes.add(labelProfundidade);
        painelConfiguracoes.add(profundidadeMaxima);
        painelConfiguracoes.add(labelTempo);
    }

   /* classe para o evento que cuida do bota iniciar
   * @param void
   * @return void
   */
    public class botaoIniciar implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            limparBotoesMatriz(true);//preenche a matriz de botoes com vazios e habilita eles
            mostrarBotoesConfiguracoes(false);//se iniciou o jogo deve terminar para habilitar as configuracoes novamente
            jogadorAtual = jogoDaVelha.jogador1;// necessario pois o jogo anterior pode ter terminado no jogador 2
            jogoDaVelha.jogador1.setCor(Color.red);
            jogoDaVelha.jogador2.setCor(Color.blue);
            jogoDaVelha.jogador1.minMaxAB.depth = Integer.parseInt(profundidadeMaxima.getText());
            jogoDaVelha.jogador2.minMaxAB.depth = Integer.parseInt(profundidadeMaxima.getText());
            long tempoInicio = System.currentTimeMillis();
            if(pcXvc.isSelected())//computador comeca jogando
            {
                int posicao = jogoDaVelha.fazerJogadaPC(jogadorAtual).lastPos;
                botoes[posicao].setText(""+jogadorAtual.getSimbolo());
                botoes[posicao].setForeground(jogadorAtual.getCor());
                jogadorAtual = jogoDaVelha.jogador2;//pc ja jogou e a vez do jogador2
            }
            if(pcXpc.isSelected())//funcao especial para 
            {
                computadorXcomputador();
            }

            tempo = (System.currentTimeMillis() - tempoInicio) / 1000;

        }
    }

      /* classe para o evento que identifica o numero do botao clicado
   * na matriz de botoes, armazenando essa informacao
   * @param void
   * @return void
   */
    public class clicouMatrizDeBotoes implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            JButton botaoClicado = (JButton) e.getSource();
            if(botaoClicado.getText().equals(""))//se o campo estiver vazio entao processa a jogada
            {
                processarJogada(botaoClicado,jogadorAtual);
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Posicao ja contem elemento", "Aviso", 2);
            }
        }
    }

    /* metodo que vai processar a jogada e printar o resultado
     * @param void
     * @return void
     */
    public void processarJogada(JButton botaoClicado, Jogador jogadorDaVez) {
        jogadorAtual = jogadorDaVez;
        int posicaoJogada;
        if (!pcXpc.isSelected())//as demais configuracoes alguma pessoa joga, sendo necessario pegar o botao clicado
        {
            IDbotaoClicado = Integer.parseInt(botaoClicado.getName());//cada  botao tem um nome que vai de 0 ate 8
            if (!jogoDaVelha.tabuleiro.posicaoLivre(IDbotaoClicado)) {
                System.err.println("posicao " + IDbotaoClicado + " ja esta ocupada");
            } else {
                botaoClicado.setText("" + jogadorAtual.getSimbolo());
                botaoClicado.setForeground(jogadorAtual.getCor());
                jogoDaVelha.computaJogadaHumano(jogadorAtual, IDbotaoClicado);
                if (jogoDaVelha.vencedor(jogadorAtual)) {
                    mensagemVencedor(jogadorAtual);
                    return;
                }
                if (jogoDaVelha.tabuleiro.tabuleiroEstaCheio()) {
                    mensagemEmpate();
                    return;
                }
                jogadorAtual = (jogoDaVelha.jogador1 == jogadorAtual ? jogoDaVelha.jogador2 : jogoDaVelha.jogador1);// esta na vez do computador ou da outra pessoa jogar
                if (!vcXele.isSelected()) {// se for a vez do computador jogar
                    posicaoJogada = jogoDaVelha.fazerJogadaPC(jogadorAtual).lastPos;
                    botoes[posicaoJogada].setText("" + jogadorAtual.getSimbolo());
                    botoes[posicaoJogada].setForeground(jogadorAtual.getCor());
                    if (jogoDaVelha.vencedor(jogadorAtual)) {
                        mensagemVencedor(jogadorAtual);
                        return;
                    }
                    if (jogoDaVelha.tabuleiro.tabuleiroEstaCheio()) {
                        mensagemEmpate();
                        return;
                    }
                    jogadorAtual = (jogoDaVelha.jogador1 == jogadorAtual ? jogoDaVelha.jogador2 : jogoDaVelha.jogador1);//na proxima chamada e a pessoa que joga
                }
            }
        } else//faz a jogada do pc
        {
            int jogada = jogoDaVelha.fazerJogadaPC(jogadorAtual).lastPos;
            //executaProcesso();
            //processo = null;//pronto para outro processo
            botoes[jogada].setText("" + jogadorAtual.getSimbolo());
            botoes[jogada].setForeground(jogadorAtual.getCor());
            Teste.printTable(jogoDaVelha.tabuleiro);
        }
    }



    /* Metodo que inicia a partida pcxpc e vai printando
     * na tela as posicoes selecionadas por ambos
     * @param void
     * @return void
     */
    public void computadorXcomputador() {
    //enquanto que o tabuleiro nao estiver cheio
        while (!jogoDaVelha.tabuleiro.tabuleiroEstaCheio()) {
            jogadorAtual = jogoDaVelha.jogador1;
            processarJogada(new JButton("vazio"), jogadorAtual);
            if (jogoDaVelha.vencedor(jogadorAtual)) {
                mensagemVencedor(jogadorAtual); return;
            }
            jogadorAtual = jogoDaVelha.jogador2;
            if(jogoDaVelha.tabuleiro.tabuleiroEstaCheio()){
                mensagemEmpate(); return;
            }
            processarJogada(new JButton("vazio"), jogadorAtual);
            if (jogoDaVelha.vencedor(jogadorAtual)) {
                mensagemVencedor(jogadorAtual);  return;
            }
        }
    }

    /* metodo que abre uma mensagem indicando o vencedor
     * @param Jogador jogadorAtual
     * @return void
     */
    public void mensagemVencedor(Jogador jogadoAtual) {
        JOptionPane.showMessageDialog(null, jogadorAtual.getNome() + " venceu !");
        habilitarNovoJogo();
    }

    /* metodo que abre uma mensagem indicando o empate
     * @param void
     * @return void
     */
    public void mensagemEmpate() {
        JOptionPane.showMessageDialog(null, " Houve Empate !");
        habilitarNovoJogo();
    }

    /* classe que contem evento que cuida dos botoes do radioButton
     * eles que selecionam o tipo de jogo pcxpc, euxpc, pcxeu ..
     * @param void
     * @return void
     */
    public class tipoDoJogo implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            listaAlgoritmos.setEnabled(true);
            profundidadeMaxima.setEnabled(true);
            //System.out.println(profundidadeMaxima.getText());
            if (pcXvc.isSelected()) {
                jogoDaVelha.setJogador1(new Jogador('X',"PC",tipoBusca));
                jogoDaVelha.setJogador2(new Jogador('O',"VC"));
            }
            if (vcXpc.isSelected()) {
                jogoDaVelha.setJogador1(new Jogador('X',"VC"));
                jogoDaVelha.setJogador2(new Jogador('O',"PC",tipoBusca));
            }
            if (pcXpc.isSelected()) {
                jogoDaVelha.setJogador1(new Jogador('X',"PC1",tipoBusca));
                jogoDaVelha.setJogador2(new Jogador('O',"PC2",tipoBusca));
            }
            if (vcXele.isSelected()) {
                jogoDaVelha.setJogador1(new Jogador('X',"VC"));
                jogoDaVelha.setJogador2(new Jogador('O',"ELE"));
                listaAlgoritmos.setEnabled(false);
                profundidadeMaxima.setEnabled(false);
            }
        }
    }

    /* metodo que recebe um boolean para mostrar ou ocultar os botoes
     * de configuracao
     * @param boolean mostrar
     * @return void
     */
    public void mostrarBotoesConfiguracoes(boolean mostrar) {
        iniciar.setEnabled(mostrar);
        pcXvc.setEnabled(mostrar);
        vcXpc.setEnabled(mostrar);
        pcXpc.setEnabled(mostrar);
        vcXele.setEnabled(mostrar);
        listaAlgoritmos.setEnabled(mostrar);
    }

    /* evento que cuida da caixa para selecionar o nome
     * do algoritmo a ser executado
     * @param void
     * @return void
     */
    public class selecionaAlgoritmo implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            profundidadeMaxima.setEnabled(false);
            tipoBusca = (String) listaAlgoritmos.getSelectedItem();
            if (tipoBusca.compareTo("MinMax") == 0) {
                jogoDaVelha.jogador1.setEstrategia("MinMax");
                jogoDaVelha.jogador2.setEstrategia("MinMax");
                profundidadeMaxima.setEnabled(true);
                return;
            }
            if (tipoBusca.compareTo("CorteAB") == 0) {
                jogoDaVelha.jogador1.setEstrategia("CorteAB");
                jogoDaVelha.jogador2.setEstrategia("CorteAB");
                profundidadeMaxima.setEnabled(true);
                return;
            }
            jogoDaVelha.jogador1.setEstrategia("Random");
            jogoDaVelha.jogador2.setEstrategia("Random");
        }
    }

     /* metodo que habilita os botoes para um novo jogo
     * e tambem inicia um tabuleiro vazio
     * @param void
     * @return void
     */
    public void habilitarNovoJogo() {
        mostrarBotoesConfiguracoes(true);//habilita a aba configurocoes para um novo jogo
        limparBotoesMatriz(false);//preenche a matriz de botoes com vazios e desabilita eles
        jogoDaVelha.tabuleiro = new Tabuleiro();//cria um tabuleiro vazio

    }
}
