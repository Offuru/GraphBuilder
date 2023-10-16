package GraphBuilder.listeners;

import GraphBuilder.Panel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {

    final Panel panel;

    public KeyboardListener(Panel panel) {
        this.panel = panel;
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {

        //press space for toggling directed graph on/off
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            panel.isDirectedGraph = !panel.isDirectedGraph;
            panel.setFocusable(true);
            panel.requestFocusInWindow();
            panel.repaint();
        }

        //if graph is undirected make sure all edges have an opposite
        if (!panel.isDirectedGraph) {

            for (int i = 0; i < Panel.nodeCount; i++)
                for (int j = 0; j < Panel.nodeCount; j++)
                    if (!panel.adjancencyMatrix.getEdge(i, j).equals(panel.adjancencyMatrix.getEdge(j, i))) {

                        if (panel.adjancencyMatrix.getEdge(i, j) == 0) {

                            panel.adjancencyMatrix.updateEdge(i, j, 1);
                            panel.edgeManager.addEdge(panel.nodeManager.getNode(i), panel.nodeManager.getNode(j));

                        } else {

                            panel.adjancencyMatrix.updateEdge(j, i, 1);
                            panel.edgeManager.addEdge(panel.nodeManager.getNode(j), panel.nodeManager.getNode(i));

                        }
                    }
            panel.adjancencyMatrix.printMatrix();
        }

        //delete node if Q key is pressed
        if (e.getKeyCode() == KeyEvent.VK_Q && panel.mouseListener.start != null) {
            int key = panel.mouseListener.start.getKey();

            panel.mouseListener.start = null;

            panel.edgeManager.deleteNode(key);
            panel.adjancencyMatrix.deleteNode(key);
            panel.nodeManager.deleteNode(key);
            panel.adjancencyMatrix.printMatrix();

            Panel.nodeCount--;

            panel.setFocusable(true);
            panel.requestFocusInWindow();
            panel.repaint();

            panel.adjancencyMatrix.printMatrix();
        }

        if (e.getKeyCode() == KeyEvent.VK_C) {

            for (int i = 0; i < Panel.nodeCount; i++)
                for (int j = 0; j < Panel.nodeCount; j++)
                    if (i != j && panel.adjancencyMatrix.getEdge(i, j) == 0) {
                        panel.adjancencyMatrix.updateEdge(i, j, 1);
                        panel.edgeManager.addEdge(panel.nodeManager.getNode(i), panel.nodeManager.getNode(j));
                    }

            panel.adjancencyMatrix.printMatrix();

            panel.setFocusable(true);
            panel.requestFocusInWindow();
            panel.repaint();
        }
    }

    public void keyReleased(KeyEvent e) {

    }
}
