package GraphBuilder.models;

import java.awt.Color;
import java.awt.Graphics;

public class Edge {

    private final Node start;
    private final Node end;
    private Color color;

    public Edge(Node start, Node end, Color color) {
        this.start = start;
        this.end = end;
        this.color = color;
    }

    public Node getStart() {
        return start;
    }

    public Node getEnd() {
        return end;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void drawEdge(Graphics g) {
        //draw between the centers of the nodes
        g.setColor(color);
        g.drawLine(start.getX() + Node.radius, start.getY() + Node.radius, end.getX() + Node.radius, end.getY() + Node.radius);
    }
}
