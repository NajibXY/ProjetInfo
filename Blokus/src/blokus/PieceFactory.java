/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package blokus;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Dimension2D;
import javafx.scene.paint.Color;
import libplateau.model.Piece;

/**
 *
 * @author Axel
 */
public class PieceFactory
{
    public static List<Piece> generateDeck(Color color)
    {
        ArrayList<Piece> pieceList = new ArrayList<>();
        
        for(PieceEnum pe : PieceEnum.values())
        {
            pieceList.add(generate(pe, color));
        }
        
        return pieceList;
    }
    
    private static Piece generate(PieceEnum pe, Color color)
    {
        Piece piece = null;
        switch(pe)
        {
            case P1 :
                piece = new Piece(new String[] {
                    "X"
                }, color, new Dimension2D(14, 0));
                break;
            case P2 :
                piece = new Piece(new String[] {
                    "XX"
                }, color, new Dimension2D(9, 9));
                break;
            case P3 :
                piece = new Piece(new String[] {
                    "XXX"
                }, color, new Dimension2D(0, 12));
                break;
            case P4 :
                piece = new Piece(new String[] {
                    "XX",
                    "X "
                }, color, new Dimension2D(5, 0));
                break;
            case P5 :
                piece = new Piece(new String[] {
                    "XXXX"
                }, color, new Dimension2D(3, 7));
                break;
            case P6 :
                piece = new Piece(new String[] {
                    "XXX",
                    "X  "
                }, color, new Dimension2D(10, 5));
                break;
            case P7 :
                piece = new Piece(new String[] {
                    "XXX",
                    " X "
                }, color, new Dimension2D(13, 11));
                break;
            case P8 :
                piece = new Piece(new String[] {
                    "XX ",
                    " XX"
                }, color, new Dimension2D(2, 12));
                break;
            case P9 :
                piece = new Piece(new String[] {
                    "XX",
                    "XX"
                }, color, new Dimension2D(2,0));
                break;
            case P10 :
                piece = new Piece(new String[] {
                    "XXXXX"
                }, color, new Dimension2D(0, 0));
                break;
            case P11 :
                piece = new Piece(new String[] {
                    "XXXX",
                    "X   "
                }, color, new Dimension2D(8, 0));
                break;
            case P12 :
                piece = new Piece(new String[] {
                    "XXXX",
                    " X  "
                }, color, new Dimension2D(0, 7));
                break;
            case P13 :
                piece = new Piece(new String[] {
                    "XX  ",
                    " XXX"
                }, color, new Dimension2D(13, 6));
                break;
            case P14 :
                piece = new Piece(new String[] {
                    "XXX",
                    "X  ",
                    "X  "
                }, color, new Dimension2D(4, 3));
                break;
            case P15 :
                piece = new Piece(new String[] {
                    "XX ",
                    " XX",
                    "  X"
                }, color, new Dimension2D(11, 0));
                break;
            case P16 :
                piece = new Piece(new String[] {
                    " XX",
                    " X ",
                    "XX "
                }, color, new Dimension2D(6, 6));
                break;
            case P17 :
                piece = new Piece(new String[] {
                    " XX",
                    "XX ",
                    " X "
                }, color, new Dimension2D(9, 12));
                break;
            case P18 :
                piece = new Piece(new String[] {
                    " X ",
                    "XXX",
                    " X "
                }, color, new Dimension2D(8, 2));
                break;
            case P19 :
                piece = new Piece(new String[] {
                    "XXX",
                    " X ",
                    " X "
                }, color, new Dimension2D(5, 10));
                break;
            case P20 :
                piece = new Piece(new String[] {
                    "XXX",
                    "X X"
                }, color, new Dimension2D(0, 2));
                break;
            case P21 :
                piece = new Piece(new String[] {
                    "XXX",
                    "XX "
                }, color, new Dimension2D(0, 11));
                break;
        }
        
        return piece;
    }
}
