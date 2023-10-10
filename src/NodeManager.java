import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;

public class NodeManager {

    public List<Node> nodeList;
    public Color color;

    public NodeManager() {
        nodeList = new ArrayList<>();
    }

    public void addNode(int x, int y, int key) {

        nodeList.add(new Node(x, y, key));

    }

    public void removeNode(int i){

    }

    public void draw(Graphics g) {

        for (Node node : nodeList)
            node.drawNode(g);

    }
}
