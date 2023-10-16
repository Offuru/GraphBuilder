package GraphBuilder.models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Node {

    private int x;
    private int y;
    private int key;

    public static final int radius = 15;
    public static final int minNodeDistance = 2 * radius + 5;
    private Color color;
    private final Color borderColor = Color.black;

    public Node(int x, int y, int key) {
        this.x = x;
        this.y = y;
        this.key = key;
        this.color = Color.lightGray;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getKey() {
        return this.key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;

        if (this.x < 10)
            this.x = 10;
        if (this.x > 750)
            this.x = 750;
        if (this.y < 10)
            this.y = 10;
        if (this.y > 750)
            this.y = 750;
    }

    public boolean containsPoint(Point p) {
        return p.distance(new Point(x + radius, y + radius)) <= radius;
    }

    public void drawNode(Graphics g) {
        //draw center first
        g.setColor(color);
        g.fillOval(x, y, radius * 2, radius * 2);
        //then border
        g.setColor(borderColor);
        g.drawOval(x, y, radius * 2, radius * 2);

        //align text for double digit keys

        if (key < 10)
            g.drawString(((Integer) key).toString(), x + radius, y + radius);
        else
            g.drawString(((Integer) key).toString(), x + radius - 5, y + radius);
    }
}
