package libplateau.model;

import javafx.geometry.Dimension2D;
import javafx.scene.paint.Color;

public class Piece {
    public Piece(boolean[][] tab, Color color, Dimension2D pos)
    {
        this.tab = tab;
        this.num = ++maxNum;
        this.color = color;
        this.pos = pos;
    }
    
    public Piece(boolean[][] tab, Color color)
    {
        this(tab, color, null);
    }

    public Piece(String[] tab, Color color, Dimension2D pos)
    {
        int w = tab.length;
        int h = tab[0].length();
        this.tab = new boolean[w][h];
        for(int i = 0; i < w; i++)
        {
            for(int j = 0; j < h; j++)
            {
                char c = tab[i].charAt(j);
                if(c == 'X')
                {
                    this.tab[i][j] = true;
                }
            }
        }
        this.num = ++maxNum;
        this.color = color;
        this.pos = pos;
    }
    
    public Piece(String[] tab, Color color)
    {
        this(tab, color, null);
    }

    public boolean[][] getTab()
    {
            return this.tab;
    }
    
    public Dimension2D getSize()
    {
        int w = tab.length;
        int h = tab[0].length;
        
        return new Dimension2D(w, h);
    }

    public Dimension2D getPos() {
        return pos;
    }

    public void setPos(Dimension2D pos) {
        this.pos = pos;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    public int getNum()
    {
        return this.num;
    }
    
    public void rotate(){
        boolean[][] tab = new boolean[(int)this.getSize().getHeight()][(int)this.getSize().getWidth()];
        int ni = 0; int nj;
        int i,j;
        for(j=0;j<(int)this.getSize().getHeight();j++){
            nj=0;
            for(i=(int)this.getSize().getWidth()-1;i>-1;i--){
                tab[ni][nj]=this.tab[i][j];
                nj++;
            }
            ni++;
        }
        this.tab = tab;
    }
    
    public boolean isOnPosition(Dimension2D pos)
    {
        if(this.pos == null)
            return false;
        
        for(int i = 0; i < getSize().getWidth(); i++) {
            for(int j = 0; j < getSize().getHeight(); j++) {
                if(tab[i][j] == true) {
                    if(pos.equals(new Dimension2D(this.pos.getWidth() + i, this.pos.getHeight() + j))) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    private boolean[][] tab;
    private Dimension2D pos;
    private int num;
    private Color color;
    private static int maxNum = 0;
}
