import javax.swing.*;

public class Main {
    protected static final int width = 700;
    protected static final int height = 700;

    private static Panel panel;

    public static void InitUI(){

        JFrame frame = new JFrame("GraphBuilder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel = new Panel());
        frame.setSize(width, height);
        frame.setVisible(true);
        //limit testing
        /*
        int key =1;
        for(int x = 20; x<=600;x+=40)
            for(int y=20;y<=600;y+=40)
                panel.nodeManager.addNode(x,y,key++);

        for(int i=0;i<panel.nodeManager.nodeList.size() - 10;i++)
            for(int j=0;j<panel.nodeManager.nodeList.size() - 10;j++)
                if(i!=j)
                    panel.edgeManager.addEdge(panel.nodeManager.nodeList.get(i),panel.nodeManager.nodeList.get(j));

         */
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                InitUI();
            }
        });
    }

}