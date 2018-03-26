/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rushhour;

import java.util.List;
import javafx.geometry.Dimension2D;
import javafx.scene.paint.Color;
import libplateau.model.Piece;
import libplateau.view.GridView;

/**
 *
 * @author axelb
 */
public class GameStage
{
    public GameStage(List<Piece> pieces)
    {
        this.pieces = pieces;
        this.mainPiece = this.pieces.get(0);
    }
    
    public List<Piece> getPieces()
    {
        return pieces;
    }
    
    public void applyToGrid(GridView gridView)
    {
        gridView.getModel().clearGrid();
        gridView.addPiece(new Piece(new boolean[][]{
            {true, true, true, true, true, true, true, true},
            {true, false, false, false, false, false, false, true},
            {true, false, false, false, false, false, false, true},
            {true, false, false, false, false, false, false, false},
            {true, false, false, false, false, false, false, true},
            {true, false, false, false, false, false, false, true},
            {true, false, false, false, false, false, false, true},
            {true, true, true, true, true, true, true, true}
        }, Color.BLACK), new Dimension2D(0, 0));
        for(Piece p : this.getPieces())
        {
            gridView.addPiece(p, new Dimension2D(p.getPos().getWidth() + 1, p.getPos().getHeight() + 1));
        }
    }
    
    List<Piece> pieces;
    Piece mainPiece;
}
