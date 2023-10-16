package GraphBuilder;

import GraphBuilder.listeners.KeyboardListener;
import GraphBuilder.listeners.MouseListener;
import GraphBuilder.managers.EdgeManager;
import GraphBuilder.managers.NodeManager;
import GraphBuilder.models.AdjancencyMatrix;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

    public static int nodeCount = 0;
    public boolean isDirectedGraph;
    public AdjancencyMatrix adjancencyMatrix;
    public final EdgeManager edgeManager;
    public final NodeManager nodeManager;
    public final MouseListener mouseListener;
    public final KeyboardListener keyboardListener;

    public Panel() {
        edgeManager = new EdgeManager(this);
        nodeManager = new NodeManager(this);
        adjancencyMatrix = new AdjancencyMatrix("matrix.txt");
        addMouseListener(mouseListener = new MouseListener(this));
        addMouseMotionListener(mouseListener);
        addKeyListener(keyboardListener = new KeyboardListener(this));
        setBackground(new Color(102, 101, 117));

        //limit testing
        /*

        for (int x = 10; x <= 700; x += 40)
            for (int y = 10; y <= 700; y += 40) {
                nodeManager.addNode(x, y, nodeCount++);
                adjancencyMatrix.addNode();
            }
        */
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        edgeManager.drawEdges(g);
        nodeManager.drawNodes(g);

        setFocusable(true);
        requestFocusInWindow();
    }

}
