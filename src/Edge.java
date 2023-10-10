import java.awt.*;

public class Edge {

    Node start;
    Node end;
    Color color;

    int radius = 15;

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
        g.drawLine(start.getX() + radius,start.getY() + radius,end.getX() + radius,end.getY() + radius);

    }

}
