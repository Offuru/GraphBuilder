import java.awt.*;
import javax.swing.*;

public class Edge {

    Node start;
    Node end;
    Color color;

    public Edge(Node start, Node end, Color color) {
        this.start = start;
        this.end = end;
        this.color = color;
    }

    public Node getStart(){
        return this.start;
    }

    public Node getEnd(){
        return this.end;
    }

    public void draw(Graphics g){

        g.setColor(color);
        g.drawLine(start.getX(),start.getY(),end.getX(),end.getY());

    }

}
