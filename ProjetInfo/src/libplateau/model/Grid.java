package libplateau.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javafx.geometry.Dimension2D;
import javafx.scene.paint.Color;

public class Grid extends Observable {

    public Grid(Dimension2D dim) {
        this(dim, Color.GRAY);
    }

    public Grid(Dimension2D dim, Color color) {
        this.pieces = new ArrayList<>();
        this.dim = dim;
        this.grid = new Tile[(int) dim.getWidth()][(int) dim.getHeight()];
        this.backroundColor = color;
        for (int i = 0; i < (int) dim.getWidth(); i++) {
            for (int j = 0; j < (int) dim.getHeight(); j++) {
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
        if (this.checkCollision(piece, pos))
            return;
        
        piece.setPos(pos);
        this.pieces.add(piece);
        boolean[][] tab = piece.getTab();
        List<Tile> updatedTiles = new ArrayList<>();
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                if (tab[i][j] == true) {
                    Tile tile = this.grid[(int) pos.getWidth() + i][(int) pos.getHeight() + j];
                    tile.setVal(piece.getNum());
                    tile.setColor(piece.getColor());
                    updatedTiles.add(tile);
                }
            }
        }
        
        setChanged();
        notifyObservers(updatedTiles);
    }

    public Tile[][] getGrid() {
        return this.grid;
    }
    
    public boolean movePiece(Piece p, Dimension2D npos) {
        if(this.checkCollision(p, npos))
            return false;
        
        boolean[][] tab = p.getTab();
        Dimension2D pos = p.getPos();
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                if (tab[i][j] == true) {
                    Tile tile = this.grid[(int) pos.getWidth() + i][(int) pos.getHeight() + j];
                    tile.setVal(0);
                    tile.setColor(this.backroundColor);
                }
            }
        }
        
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                if (tab[i][j] == true) {
                    Tile tile = this.grid[(int) npos.getWidth() + i][(int) npos.getHeight() + j];
                    tile.setVal(p.getNum());
                    tile.setColor(p.getColor());
                }
            }
        }
        
        return true;
    }
    
    public void rotatePiece(Piece p) {
        Dimension2D pos = p.getPos();
        Dimension2D size = p.getSize();
        Dimension2D origin = new Dimension2D((pos.getWidth() + size.getWidth()) / 2, (pos.getHeight() + size.getHeight()) / 2);
        
        pos = new Dimension2D(pos.getWidth() - origin.getWidth(), pos.getHeight() - origin.getHeight());
    }

    public boolean checkCollision(Piece p, Dimension2D npos) {
        //needs Tile
        Dimension2D size = p.getSize();
        boolean[][] tab = p.getTab();
        for (int i = 0; i < size.getWidth(); i++) {
            for (int j = 0; j < size.getHeight(); j++) {
                if(tab[i][j] == true) {
                    if (grid[(int) npos.getWidth() + i][(int) npos.getHeight() + j].getVal() != 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public void print() {
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++){
                System.out.print(grid[i][j].getVal() + ", ");
            }
            System.out.println();
        }
    }

    private List<Piece> pieces;
    private Dimension2D dim;
    private Tile[][] grid;
    private Color backroundColor;
}
