import java.awt.*;


public class Node {
    private int x;
    private int y;
    private int key;

    private Color nodeColor;
    private final Color borderColor;

    private final int radius = 15;

    public Node(int x, int y, int key) {
        this.x = x;
        this.y = y;
        this.key = key;
        this.nodeColor = Color.lightGray;
        this.borderColor = Color.black;
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

    public void setPosition(int x, int y) {

        this.x = x;
        this.y = y;

        if(this.x<10)
            this.x=10;
        if(this.y<10)
            this.y=10;
        if(this.x>650)
            this.x=650;
        if(this.y>630)
            this.y=630;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setColor(Color color) {
        this.nodeColor = color;
    }

    public void drawNode(Graphics g) {

        g.setColor(nodeColor);
        g.fillOval(x, y, radius * 2, radius * 2);
        g.setColor(borderColor);
        g.drawOval(x, y, radius * 2, radius * 2);

        if(key<10)
            g.drawString(((Integer) key).toString(), x + radius, y + radius);
        else
            g.drawString(((Integer) key).toString(), x + radius - 5, y + radius);

    }

    public boolean containsPoint(Point p){
        return p.distance(new Point(x + radius, y + radius)) <= radius;
    }
}
