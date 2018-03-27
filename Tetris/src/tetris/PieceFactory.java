/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetris;

import javafx.geometry.Dimension2D;
import javafx.scene.paint.Color;
import libplateau.model.Piece;

/**
 *
 * @author Axel
 */
public class PieceFactory
{
    public static Piece generate(PieceEnum pe)
    {
        Piece piece = null;
        switch(pe)
        {
            case I_PIECE :
                piece = new Piece(new String[] {
                    "XXXX"
                }, Color.SKYBLUE, new Dimension2D(0, 3));
                break;
            case J_PIECE :
                piece = new Piece(new String[] {
                    "X  ",
                    "XXX"
                }, Color.BLUE, new Dimension2D(0, 3));
                break;
            case L_PIECE :
                piece = new Piece(new String[] {
                    "XXX",
                    "X  "
                }, Color.ORANGE, new Dimension2D(0, 3));
                break;
            case O_PIECE :
                piece = new Piece(new String[] {
                    "XX",
                    "XX"
                }, Color.YELLOW, new Dimension2D(0, 3));
                break;
            case S_PIECE :
                piece = new Piece(new String[] {
                    " XX",
                    "XX "
                }, Color.GREEN, new Dimension2D(0, 3));
                break;
            case T_PIECE :
                piece = new Piece(new String[] {
                    " X ",
                    "XXX"
                }, Color.PURPLE, new Dimension2D(0, 3));
                break;
            case Z_PIECE :
                piece = new Piece(new String[] {
                    "XX ",
                    " XX"
                }, Color.RED, new Dimension2D(0, 3));
                break;
        }
        
        return piece;
    }
}
