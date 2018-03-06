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
        if (!this.checkCollision(piece, pos)) {
            piece.setPos(pos);
            this.pieces.add(piece);
            boolean[][] tab = piece.getTab();
            for (int i = 0; i < tab.length; i++) {
                for (int j = 0; j < tab[i].length; j++) {
                    if (tab[i][j] == true) {
                        this.grid[(int) pos.getHeight() + i][(int) pos.getWidth() + j].setVal(piece.getNum());
                    }
                }
            }
        }
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
                    this.grid[(int) pos.getHeight() + i][(int) pos.getWidth() + j].setVal(0);
                }
            }
        }
        
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                if (tab[i][j] == true) {
                    this.grid[(int) npos.getHeight() + i][(int) npos.getWidth() + j].setVal(p.getNum());
                }
            }
        }
        
        return true;
    }
    
    public void rotatePiece(Piece p) {
        Dimension2D pos = p.getPos();
        Dimension2D size = p.getSize();
        Dimension2D origin = new Dimension2D((pos.getHeight() + size.getHeight()) / 2, (pos.getWidth() + size.getWidth()) / 2);
        
        pos = new Dimension2D(pos.getHeight() - origin.getHeight(), pos.getWidth() - origin.getWidth());
    }

    public boolean checkCollision(Piece p, Dimension2D npos) {
        //needs Tile
        Dimension2D size = p.getSize();
        boolean[][] tab = p.getTab();
        for (int i = 0; i < size.getHeight(); i++) {
            for (int j = 0; j < size.getWidth(); j++) {
                if(tab[i][j] == true) {
                    if (grid[(int) npos.getHeight() + i][(int) npos.getWidth() + j].getVal() != 0) {
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
}
