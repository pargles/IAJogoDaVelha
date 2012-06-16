
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author pargles
 * @version 1.0
 * @references http://www.slideshare.net/iaudesc/algoritmos-de-jogos-8174709
 * http://cboard.cprogramming.com/game-programming/131140-tic-tac-toe-minimax-algorithm-problem.html
 * http://jogosonline.clickgratis.com.br/classico/jogo_da_velha-163.html
 * http://www.ocf.berkeley.edu/~yosenl/extras/alphabeta/alphabeta.html
 */
public class Main {

    public static void main(String[] args) {
        selecionarInterface(0);
        JFrame frame = new VelhaInterface();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack(); //ajusta o tamanho da janela ao dos componentes
        frame.setResizable(false);//nao deixa o usuario aumentar o tamanho da tela
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);//torna visivel a interface
    }

      /**
     * Seleciona o padrão de visualização da interface GUI
     * @param tipo um inteiro GTK,METAL,MOTIF,WINDOWS, WINDOWS_CLASSIC,MAC
     */
    public static void selecionarInterface(int tipo) {
        String[] newLookAndFeel = {
            "com.sun.java.swing.plaf.gtk.GTKLookAndFeel",
            "javax.swing.plaf.metal.MetalLookAndFeel",
            "com.sun.java.swing.plaf.windows.WindowsLookAndFeel",
            "com.sun.java.swing.plaf.motif.MotifLookAndFeel",
            "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel",
            "javax.swing.plaf.mac.MacLookAndFeel"};

        tipo = tipo < 0 || tipo > newLookAndFeel.length-1 ? newLookAndFeel.length-1 : tipo;

        try {
            UIManager.setLookAndFeel(newLookAndFeel[tipo]);
        } catch (Exception e) {
            System.err.println("TIPO: " + newLookAndFeel[tipo] + " NAO INSTALADO, MUDE O TIPO DA INTERFACE");
        }
    }

}
