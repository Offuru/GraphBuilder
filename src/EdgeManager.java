import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;

public class EdgeManager {
    List<Edge> edgeList;
    Color color;

    public EdgeManager() {
        this.edgeList = new ArrayList<>();
        this.color = Color.blue;
    }

    public void addEdge(Node start, Node end) {

        this.edgeList.add(new Edge(start, end, this.color));
    }

    public void draw(Graphics g) {

        for (Edge edge : edgeList)
            edge.draw(g);
    }
}
