import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

    public NodeManager nodeManager;
    public EdgeManager edgeManager;
    public int nodeCount;

    public int nodeRadius = 15;
    public int minNodeDistance = 10;

    public Panel() {
        nodeManager = new NodeManager();
        edgeManager = new EdgeManager();
        addMouseListener(new MouseListener(this));
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        edgeManager.draw(graphics);
        nodeManager.draw(graphics);
    }
}
