/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.util.List;
import java.util.Random;

/**
 *
 * @author Axel
 */
public enum PieceEnum {
    I_PIECE,
    J_PIECE,
    L_PIECE,
    O_PIECE,
    S_PIECE,
    T_PIECE,
    Z_PIECE;
    
    public static PieceEnum getRandomPieceEnum()
    {
        Random random = new Random();
        PieceEnum pe = I_PIECE;
        
        switch(random.nextInt(VALUES.length))
        {
            case 0 :
                pe = I_PIECE;
                break;
            case 1 :
                pe = J_PIECE;
                break;
            case 2 :
                pe = L_PIECE;
                break;
            case 3 :
                pe = O_PIECE;
                break;
            case 4 :
                pe = S_PIECE;
                break;
            case 5 :
                pe = T_PIECE;
                break;
            case 6 :
                pe = Z_PIECE;
                break;
        }
        
        return pe;
    }
        
    private static final PieceEnum[] VALUES = PieceEnum.values();
}
