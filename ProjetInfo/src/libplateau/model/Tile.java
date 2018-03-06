package libplateau.model;

import javafx.scene.paint.Color;

public class Tile {

    public Tile(Color color) {
        this.color = color;
        this.val = 0;
    }

    public Color getColor() {
        return this.color;
    }

    public int getVal() {
        return this.val;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setVal(int val) {
        this.val = val;
    }
    
    private int val;
    private Color color;
}
