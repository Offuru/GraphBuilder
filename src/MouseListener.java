import java.awt.*;
import java.awt.event.MouseEvent;

public class MouseListener implements javax.swing.event.MouseInputListener {

    Panel panel;
    Point start;
    Point end;
    boolean isDragging;

    Node startNode;
    Node endNode;
    int draggedNodeIndex;

    int radius;

    public MouseListener(Panel panel) {
        this.panel = panel;
        this.start = null;
        this.end = null;
        this.isDragging = false;
        this.radius = panel.nodeRadius;
    }

    @Override
    public void mousePressed(MouseEvent e) {

        if (startNode == null) {
            for (Node node : panel.nodeManager.nodeList)
                if (node.containsPoint(e.getPoint())) {
                    startNode = node;
                    node.setColor(Color.orange);
                    draggedNodeIndex = panel.nodeManager.nodeList.indexOf(node);
                    break;
                }
        } else {
            for (Node node : panel.nodeManager.nodeList)
                if (node.containsPoint(e.getPoint())) {
                    if (node == startNode) {
                        startNode = null;
                        node.setColor(Color.lightGray);
                        System.out.println("Clicked startNode");
                    } else
                        endNode = node;
                    break;
                }
        }

        if (startNode != null && endNode != null) {
            panel.edgeManager.addEdge(startNode, endNode);

            for (Node node : panel.nodeManager.nodeList)
                if (node == startNode)
                    node.setColor(Color.lightGray);

            startNode = null;
            endNode = null;
        }

        panel.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if (startNode == null) {

            boolean canPlace = true;

            for (Node node : panel.nodeManager.nodeList)
                if (e.getPoint().distance(new Point(node.getX() + radius, node.getY() + radius)) <= 2 * radius + panel.minNodeDistance) {
                    canPlace = false;
                    break;
                }
            if (canPlace) {
                panel.nodeManager.addNode(e.getX() - radius, e.getY() - radius, panel.nodeCount++);
            }
        }

        isDragging = false;

        panel.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

        if (startNode != null && !isDragging) {

            if (panel.nodeManager.nodeList.get(draggedNodeIndex).containsPoint(e.getPoint()))
                isDragging = true;

        } else if (isDragging) {
            panel.nodeManager.nodeList.get(draggedNodeIndex).setPosition(e.getX() - radius, e.getY() - radius);
        }
        panel.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
