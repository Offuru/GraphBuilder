package GraphBuilder.managers;

import GraphBuilder.models.Node;
import GraphBuilder.Panel;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;

public class NodeManager {

    public final List<Node> nodeList;
    final Panel panel;
    public final Color nodeColor = new Color(173, 107, 112);
    public final Color nodeHightlightColor = new Color(58, 182, 107);

    public NodeManager(Panel panel) {
        this.panel = panel;
        nodeList = new ArrayList<>();
    }

    public Node getNode(int index) {
        return nodeList.get(index);
    }

    public void addNode(int x, int y, int key) {
        Node node = new Node(x, y, key);
        node.setColor(nodeColor);
        nodeList.add(node);
    }

    public void deleteNode(int key) {
        nodeList.remove(key);
        for (int i = key; i < nodeList.size(); i++)
            nodeList.get(i).setKey(nodeList.get(i).getKey() - 1);
    }

    public void drawNodes(Graphics g) {

        for (Node node : nodeList) {
            node.drawNode(g);
        }

        panel.setFocusable(true);
        panel.requestFocusInWindow();
    }
}
