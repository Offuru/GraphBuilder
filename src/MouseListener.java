import java.awt.event.MouseEvent;
import java.awt.Point;

public class MouseListener implements javax.swing.event.MouseInputListener {

    Panel panel;
    Point start;
    Point end;
    boolean isDragging;

    Node startNode;
    Node endNode;

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
                if (e.getPoint().distance(new Point(node.getX() + radius, node.getY() + radius)) <= radius)
                    startNode = node;
        } else {
            for (Node node : panel.nodeManager.nodeList)
                if (node != startNode && e.getPoint().distance(new Point(node.getX() + radius, node.getY() + radius)) <= radius)
                    endNode = node;
        }

        if (startNode != null && endNode != null) {
            panel.edgeManager.addEdge(startNode, endNode);
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
                    canPlace=false;
                    break;
                }
            if(canPlace){
                panel.nodeManager.addNode(e.getX() - radius, e.getY() - radius, panel.nodeCount++);
            }
        }

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
        isDragging = true;
        end = e.getPoint();

        panel.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
