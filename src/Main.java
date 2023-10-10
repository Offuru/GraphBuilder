import javax.swing.*;

public class Main {
    private static final int width = 700;
    private static final int height = 700;

    public static void InitUI(){

        JFrame frame = new JFrame("GraphBuilder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Panel());
        frame.setSize(width, height);
        frame.setVisible(true);

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                InitUI();
            }
        });
    }

}