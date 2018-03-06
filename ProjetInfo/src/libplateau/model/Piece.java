package libplateau.model;

import javafx.geometry.Dimension2D;

public class Piece {
    public Piece(boolean[][] tab)
    {
        this.tab = tab;
        this.num = ++maxNum;
    }

    public Piece(String[] tab)
    {
        int h = tab.length;
        int w = tab[0].length();
        this.tab = new boolean[h][w];
        for(int i = 0; i < h; i++)
        {
            for(int j = 0; j < w; j++)
            {
                char c = tab[i].charAt(j);
                if(c == 'X')
                {
                    this.tab[i][j] = true;
                }
            }
        }
        this.num = ++maxNum;
    }

    public boolean[][] getTab()
    {
            return this.tab;
    }
    
    public Dimension2D getSize()
    {
        int h = tab.length;
        int w = tab[0].length;
        
        return new Dimension2D(h, w);
    }

    public Dimension2D getPos() {
        return pos;
    }

    public void setPos(Dimension2D pos) {
        this.pos = pos;
    }
    
    public int getNum()
    {
        return this.num;
    }

    private boolean[][] tab;
    private Dimension2D pos;
    private int num;
    private static int maxNum = 0;
}
