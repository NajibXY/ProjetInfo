package libplateau.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javafx.geometry.Dimension2D;
import javafx.scene.paint.Color;

public class Grid extends Observable {

    public Grid(Dimension2D dim) {
        this(dim, Color.WHITE);
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
    
    public Piece getPiece(Dimension2D pos)
    {
        for(Piece p : pieces) {
            if(p.isOnPosition(pos))
                return p;
        }
        
        return null;
    }

    public Dimension2D getDim() {
        return dim;
    }

    public boolean addPiece(Piece piece, Dimension2D pos) {
        if (this.checkCollision(piece, pos))
            return false;
        
        piece.setPos(pos);
        this.pieces.add(piece);
        boolean[][] tab = piece.getTab();
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                if (tab[i][j] == true) {
                    Tile tile = this.grid[(int) pos.getWidth() + i][(int) pos.getHeight() + j];
                    tile.setVal(piece.getNum());
                    tile.setColor(piece.getColor());
                }
            }
        }
        
        setChanged();
        notifyObservers();
        
        return true;
    }

    public Tile[][] getGrid() {
        return this.grid;
    }
    
    public boolean movePiece(Piece p, Dimension2D npos) {
        boolean[][] tab = p.getTab();
        Dimension2D pos = p.getPos();
        
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                if (tab[i][j] == true) {
                    Tile oldTile = this.grid[(int) pos.getWidth() + i][(int) pos.getHeight() + j];
                    oldTile.setVal(0);
                    oldTile.setColor(this.backroundColor);
                }
            }
        }
        
        if(this.checkCollision(p, npos))
        {
            for (int i = 0; i < tab.length; i++) {
                for (int j = 0; j < tab[i].length; j++) {
                    if (tab[i][j] == true) {
                        Tile oldTile = this.grid[(int) pos.getWidth() + i][(int) pos.getHeight() + j];
                        oldTile.setVal(p.getNum());
                        oldTile.setColor(p.getColor());
                    }
                }
            }
            
            return false;
        }
        
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                if (tab[i][j] == true) {
                    Tile newTile = this.grid[(int) npos.getWidth() + i][(int) npos.getHeight() + j];
                    newTile.setVal(p.getNum());
                    newTile.setColor(p.getColor());
                }
            }
        }

        p.setPos(npos);
        setChanged();
        notifyObservers();
        
        return true;
    }
    
    public void removePiece(Piece p)
    {
        boolean[][] tab = p.getTab();
        Dimension2D pos = p.getPos();
        
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                if (tab[i][j] == true) {
                    Tile oldTile = this.grid[(int) pos.getWidth() + i][(int) pos.getHeight() + j];
                    oldTile.setVal(0);
                    oldTile.setColor(this.backroundColor);
                }
            }
        }
        
        pieces.remove(p);
        
        setChanged();
        notifyObservers();
    }
    
    public boolean rotatePiece(Piece p, boolean clockWise)
    {
        boolean[][] tab = p.getTab();
        Dimension2D pos = p.getPos();
        
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                if (tab[i][j] == true) {
                    Tile oldTile = this.grid[(int) pos.getWidth() + i][(int) pos.getHeight() + j];
                    oldTile.setVal(0);
                    oldTile.setColor(this.backroundColor);
                }
            }
        }
        
        p.rotate(clockWise);
        
        if(this.checkCollision(p, p.getPos()))
        {
            for (int i = 0; i < tab.length; i++) {
                for (int j = 0; j < tab[i].length; j++) {
                    if (tab[i][j] == true) {
                        Tile oldTile = this.grid[(int) pos.getWidth() + i][(int) pos.getHeight() + j];
                        oldTile.setVal(p.getNum());
                        oldTile.setColor(p.getColor());
                    }
                }
            }
            
            p.rotate(!clockWise);
            
            return false;
        }
        
        tab = p.getTab();
        pos = p.getPos();
        
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                if (tab[i][j] == true) {
                    Tile oldTile = this.grid[(int) pos.getWidth() + i][(int) pos.getHeight() + j];
                    oldTile.setVal(p.getNum());
                    oldTile.setColor(p.getColor());
                }
            }
        }
        
        setChanged();
        notifyObservers();
        
        return true;
    }

    public boolean checkCollision(Piece p, Dimension2D npos)
    {
        Dimension2D size = p.getSize();
        boolean[][] tab = p.getTab();
        
        for (int i = 0; i < size.getWidth(); i++) {
            for (int j = 0; j < size.getHeight(); j++) {
                if(tab[i][j] == true) {
                    if(npos.getWidth() + i < dim.getWidth() && npos.getHeight() + j < dim.getHeight()) {
                        if (grid[(int) npos.getWidth() + i][(int) npos.getHeight() + j].getVal() != 0) {
                            return true;
                        }
                    }
                    else
                    {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    public void clearGrid()
    {
        if(pieces.isEmpty())
            return;
        
        pieces.clear();
        for(int i = 0; i < dim.getWidth(); i++) {
            for(int j = 0; j < dim.getHeight(); j++) {
                grid[i][j].setColor(backroundColor);
                grid[i][j].setVal(0);
            }
        }
    }
    
    public void print()
    {
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
