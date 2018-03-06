package libplateau.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javafx.geometry.Dimension2D;
import javafx.scene.paint.Color;

public class Grid extends Observable{

    public Grid(Dimension2D dim)
    {
        this(dim, Color.GRAY);
    }
    
    public Grid(Dimension2D dim, Color color) {
        this.pieces = new ArrayList<>();
        this.dim = dim;
        this.grid = new Tile[(int)dim.getWidth()][(int)dim.getHeight()];
        for(int i=0;i<(int)dim.getWidth();i++){
            for(int j=0;j<(int)dim.getHeight();j++){
                this.grid[i][j] = new Tile(color);
            }
        }
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public Dimension2D getDim() {
        return dim;
    }

    public void addPiece(Piece piece, Dimension2D pos) {
        piece.setPos(pos);
        this.pieces.add(piece);
        boolean[][] tab = piece.getTab();
        for(int i = 0; i < tab.length; i++)
        {
            for(int j = 0; j < tab[i].length; j++)
            {
                this.grid[(int) pos.getHeight() + i][(int) pos.getWidth() + j].setVal(piece.getNum());
            }
        }
    }

    public Tile[][] getGrid() {
        return this.grid;
    }

    public boolean checkCollision(Piece p) {
        //needs Tile
        Dimension2D size = p.getSize();
        for(int i = 0; i < size.getHeight(); i++)
        {
            for(int j = 0; j < size.getWidth(); j++)
            {
                if(grid[(int) size.getHeight() + i][(int) size.getWidth() + j].getVal() != 0)
                    return false;
            }
        }
        
        return true;
    }

    private List<Piece> pieces;
    private Dimension2D dim;
    private Tile[][] grid;
}
