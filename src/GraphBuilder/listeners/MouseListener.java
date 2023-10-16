package GraphBuilder.listeners;

import GraphBuilder.Panel;
import GraphBuilder.models.*;

import java.awt.event.MouseEvent;
import java.awt.Point;

public class MouseListener implements javax.swing.event.MouseInputListener {

    Panel panel;
    boolean isDragging;
    Node start;
    Node end;
    int draggedNodeIndex;

    public MouseListener(Panel panel) {
        this.panel = panel;
        isDragging = false;
        start = null;
        end = null;
        draggedNodeIndex = -1;
    }

    public void mousePressed(MouseEvent e) {

        // if start null is then attempt to select a node, otherwise attempt to select an end node

        if (start == null) {

            //if a node was clicked, select it
            for (Node node : panel.nodeManager.nodeList)
                if (node.containsPoint(e.getPoint())) {
                    start = node;
                    node.setColor(panel.nodeManager.nodeHightlightColor);
                    draggedNodeIndex = panel.nodeManager.nodeList.indexOf(node);
                    break;
                }

        } else {
            //if selected node is clicked again, deselect
            for (Node node : panel.nodeManager.nodeList)
                if (node.containsPoint(e.getPoint())) {
                    if (node == start) {
                        start = null;
                        node.setColor(panel.nodeManager.nodeColor);
                    } else {
                        end = node;
                    }

                    break;
                }
        }

        //if both start and end aren't null, draw or delete edge between them

        if (start != null && end != null) {

            //if edge already exists, delete it, otherwise add edge

            if (panel.adjancencyMatrix.getEdge(start.getKey(), end.getKey()) == 1) {

                panel.adjancencyMatrix.updateEdge(start.getKey(), end.getKey(), 0);
                panel.edgeManager.removeEdge(start, end);

                //if the graph is undirected remove opposite edge

                if (!panel.isDirectedGraph) {
                    panel.adjancencyMatrix.updateEdge(end.getKey(), start.getKey(), 0);
                    panel.edgeManager.removeEdge(end, start);
                }

            } else {

                panel.edgeManager.addEdge(start, end);
                panel.adjancencyMatrix.updateEdge(start.getKey(), end.getKey(), 1);

                if (!panel.isDirectedGraph) {
                    panel.adjancencyMatrix.updateEdge(end.getKey(), start.getKey(), 1);
                    panel.edgeManager.addEdge(end, start);
                }

            }

            // color start to default color and reset start and end

            panel.nodeManager.getNode(start.getKey()).setColor(panel.nodeManager.nodeColor);

            start = end = null;

            panel.adjancencyMatrix.printMatrix();
        }

        panel.repaint();
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

        if (start == null) {

            boolean canPlace = true;

            for (Node node : panel.nodeManager.nodeList)
                if (e.getPoint().distance(new Point(node.getX() + Node.radius, node.getY() + Node.radius)) <= Node.minNodeDistance) {
                    canPlace = false;
                    break;
                }

            if (canPlace) {
                panel.nodeManager.addNode(e.getX() - Node.radius, e.getY() - Node.radius, Panel.nodeCount++);
                panel.adjancencyMatrix.addNode();
            }

            panel.adjancencyMatrix.printMatrix();
        }

        isDragging = false;

        panel.repaint();
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e) {

        if (start != null && !isDragging) {

            if (panel.nodeManager.getNode(start.getKey()).containsPoint(e.getPoint()))
                isDragging = true;

        } else if (isDragging) {

            for (Node node : panel.nodeManager.nodeList)
                if (node.getKey() != panel.nodeManager.getNode(draggedNodeIndex).getKey()
                        && e.getPoint().distance(new Point(node.getX() + Node.radius, node.getY() + Node.radius)) < Node.minNodeDistance) {

                    panel.repaint();
                    panel.setFocusable(true);
                    panel.requestFocusInWindow();

                    return;
                }

            panel.nodeManager.getNode(draggedNodeIndex).setPosition(e.getX() - Node.radius, e.getY() - Node.radius);
        }

        panel.repaint();
        panel.setFocusable(true);
        panel.requestFocusInWindow();
    }

    public void mouseMoved(MouseEvent e) {

    }
}
