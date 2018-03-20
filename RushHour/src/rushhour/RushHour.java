/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rushhour;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Dimension2D;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import libplateau.model.Piece;
import libplateau.view.GridView;

/**
 *
 * @author Najib EL KHADIR
 */
public class RushHour extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        GridView gridView = new GridView(new Dimension2D(6, 6), Color.WHITE);
        //gridView.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.THICK)));
        
        List<GameStage> gameStages = createGameStages();
        
        GameStage currentGameStage = gameStages.get(0);
        
        for(Piece p : currentGameStage.getPieces())
        {
            gridView.addPiece(p, p.getPos());
        }
        
        gridView.getModel().print();
        
        
        final Scene scene = new Scene(gridView, 700, 700);
        primaryStage.setTitle("Ruuuuuuuuuush Houuuuuuuuuuuuuur !");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public List<GameStage> createGameStages()
    {
        List<GameStage> gameStages = new ArrayList<>();
        
        List<Piece> pieceList = new ArrayList<>();
        pieceList.add(new Piece(new boolean[][] {{true, true}}, Color.RED, new Dimension2D(2, 0)));
        pieceList.add(new Piece(new boolean[][] {{true}, {true}}, Color.BLUE, new Dimension2D(3, 0)));
        pieceList.add(new Piece(new boolean[][] {{true, true}}, Color.YELLOW, new Dimension2D(0, 0)));
        gameStages.add(new GameStage(pieceList));
        
        return gameStages;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
