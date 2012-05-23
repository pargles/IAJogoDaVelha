
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 *
 * @author pargles
 * @version 2.0
 */


public class VelhaInterface extends JFrame{
    private enum heuristica{MinMaX, CorteAB;}
    private String tipoBusca ="MinMax";//default
    private JComboBox listaAlgoritmos;//para colocar os algoritmos
    private JButton[] botoes;//contem os botoes da matriz 3x3 do jogo
    private JButton iniciar;
    private JRadioButton pcXeu, euXpc,pcXpc,euXvc;
    private ButtonGroup group;//junta todos os radio buttons, pcxeu, euxpc ...
    private JPanel painelJogadas,painelConfiguracoes;
    private JTextField profundidadeMaxima;
    private JLabel labelProfundidade,labelTempo,labelVazio;
    private int IDbotaoClicado;//armazeba o numero do botao clicado da matriz de botoes
    private long tempo;//armazena tempo que demorou para calcular em segundos
    JSplitPane split;
    private String imagem = "/home/pargles/NetBeansProjects/white.png";

  //metodo construtor
  public VelhaInterface()
  {
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
            botoes[i].setName(String.valueOf(i));//seta o ID do botao
            botoes[i].setBackground(Color.WHITE);
            botoes[i].setOpaque(true);//botao fica opaco
            botoes[i].setBorder(javax.swing.BorderFactory.createEtchedBorder());//seta borda mais bonita
            botoes[i].addActionListener(new clicouMatrizDeBotoes());//coloca evento vijiando ele
            painelJogadas.add(botoes[i]);//adiciona o botao no painel das jogadas

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
        profundidadeMaxima.setText("5");//default
        //profundidadeMaxima.setEnabled(false);//so vai aparecer quando o

        listaAlgoritmos = new JComboBox();
        listaAlgoritmos.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"MinMax", "CorteAB"}));
        listaAlgoritmos.addActionListener(new selecionaAlgoritmo());

        pcXeu = new JRadioButton("PC X EU");
        pcXeu.setFocusable(false);

        euXpc = new JRadioButton("EU X PC");
        euXpc.setSelected(true);//default
        euXpc.setFocusable(false);

        pcXpc = new JRadioButton("PC X PC");
        pcXpc.setFocusable(false);

        euXvc = new JRadioButton("EU X VC");
        euXvc.setFocusable(false);

        group = new ButtonGroup();
        group.add(pcXeu);
        group.add(euXpc);
        group.add(pcXpc);
        group.add(euXvc);


        
        painelConfiguracoes.add(pcXeu);
        painelConfiguracoes.add(euXpc);
        painelConfiguracoes.add(pcXpc);
        painelConfiguracoes.add(euXvc);
        painelConfiguracoes.add(listaAlgoritmos);
        painelConfiguracoes.add(labelVazio);
        painelConfiguracoes.add(iniciar);
        painelConfiguracoes.add(labelProfundidade);
        painelConfiguracoes.add(profundidadeMaxima);
        painelConfiguracoes.add(labelTempo);


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
            IDbotaoClicado = Integer.parseInt(botaoClicado.getName());
            System.out.println("o botao: "+IDbotaoClicado);

        }

    }

   /* classe para o evento que cuida do bota iniciar
   * @param void
   * @return void
   */
    public class botaoIniciar implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            long tempoInicio = System.currentTimeMillis();
            if (pcXeu.isSelected()) {
                System.err.println("Not yet developed ");

            }
            if (euXpc.isSelected()) {
                System.err.println("Not yet developed");

            }
            if (pcXpc.isSelected()) {
                System.err.println("Not yet developed");

            }
            if (euXvc.isSelected()) {
                System.err.println("Not yet developed");

            }
            tempo = (System.currentTimeMillis() - tempoInicio) / 1000;

        }
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
            if(tipoBusca.compareTo("MinMax")==0)
            {
                profundidadeMaxima.setEnabled(true);
            }
        }
    }


  public static void main(String[] args) {
  selecionarInterface(0);
  JFrame frame = new VelhaInterface();
  frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
  frame.pack(); //ajusta o tamanho da janela ao dos componentes
  frame.setResizable(false);//nao deixa o usuario aumentar o tamanho da tela
  frame.setLocationRelativeTo( null );
  frame.setVisible(true);//torna visivel a interface
 }

      /**
     * Seleciona o padrão de visualização da interface GUI
     * @param tipo um inteiro GTK,METAL,MOTIF,WINDOWS, WINDOWS_CLASSIC,MAC
     */
    public static void selecionarInterface( int tipo ) {
        String[] newLookAndFeel = {
        "com.sun.java.swing.plaf.gtk.GTKLookAndFeel",
        "javax.swing.plaf.metal.MetalLookAndFeel",
        "com.sun.java.swing.plaf.windows.WindowsLookAndFeel",
        "com.sun.java.swing.plaf.motif.MotifLookAndFeel",
        "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel",
        "javax.swing.plaf.mac.MacLookAndFeel"};

        tipo = tipo < 0 || tipo > 5 ? 5 : tipo;

        try {
            UIManager.setLookAndFeel( newLookAndFeel[ tipo ] );
        } catch (Exception e) { System.err.println("TIPO: "+newLookAndFeel[ tipo ]+" NAO INSTALADO, MUDE O TIPO DA INTERFACE");}
    }
}
