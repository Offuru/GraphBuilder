package GraphBuilder.managers;

import GraphBuilder.Panel;
import GraphBuilder.models.Edge;
import GraphBuilder.models.Node;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class EdgeManager {

    public List<Edge> edgeList;
    Panel panel;
    Color edgeColor;

    public EdgeManager(Panel panel) {
        edgeList = new ArrayList<>();
        edgeColor = new Color(68, 152, 196);
        this.panel = panel;
    }

    public void addEdge(Node start, Node end) {
        edgeList.add(new Edge(start, end, edgeColor));
    }

    public void removeEdge(Node start, Node end) {

        for (Edge edge : edgeList)
            if (edge.getStart() == start && edge.getEnd() == end) {
                edgeList.remove(edge);
                return;
            }

    }

    public void deleteNode(int key) {
        edgeList.removeIf(node -> node.getStart().getKey() == key || node.getEnd().getKey() == key);

    }

    public void drawArcArrow(Graphics g, Edge edge) {

        Graphics2D g2d = (Graphics2D) g;
        double dx = edge.getEnd().getX() - edge.getStart().getX();
        double dy = edge.getEnd().getY() - edge.getStart().getY();
        double angle = Math.atan2(dx, dy);

        //keep initial transform for restoring later
        AffineTransform initAt = g2d.getTransform();

        //translate transform to end node coords + center offset and rotate according to edge slope angle
        AffineTransform at = AffineTransform.getTranslateInstance(edge.getEnd().getX() + Node.radius, edge.getEnd().getY() + Node.radius);
        at.concatenate(AffineTransform.getRotateInstance(2 * Math.PI - angle));
        g2d.setTransform(at);

        g2d.fillPolygon(new int[]{5, 0, -5}, new int[]{-2 * Node.radius - 7, -7, -2 * Node.radius - 7}, 3);

        g2d.setTransform(initAt);
    }

    public void drawEdges(Graphics g) {

        for (Edge edge : edgeList) {
            edge.drawEdge(g);
            if (panel.isDirectedGraph)
                drawArcArrow(g, edge);
        }
        panel.setFocusable(true);
        panel.requestFocusInWindow();

    }
}
