import javax.swing.*;
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
        start = e.getPoint();

        for (Node node : panel.nodeManager.nodeList)
            if (start.distance(new Point(node.getX() + radius, node.getY() + radius)) < 2 * radius + panel.minNodeDistance) {
                startNode = node;
                break;
            }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

        for (Node node : panel.nodeManager.nodeList)
            if (start.distance(new Point(node.getX() + radius, node.getY() + radius)) < 2 * radius + panel.minNodeDistance && node!=startNode) {
                endNode = node;
                break;
            }

        if(startNode!=null && endNode!=null) {
            panel.edgeManager.addEdge(startNode, endNode);
            System.out.format("%d %d - %d %d", startNode.getX(),startNode.getY(),endNode.getX(),endNode.getY());
        }
        else if(start!=null && end==null)
            panel.nodeManager.addNode(start.x - radius,start.y - radius, panel.nodeCount++);

        isDragging = false;
        start = null;
        end = null;
        startNode=null;
        endNode=null;

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
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
