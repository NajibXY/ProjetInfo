/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rushhour;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Dimension2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import libplateau.model.Piece;
import libplateau.view.GridView;

/**
 *
 * @author Najib EL KHADIR
 */
public class RushHour extends Application {
    
    private Piece selectedPiece;
    
    @Override
    public void start(Stage primaryStage) {
        GridView gridView = new GridView(new Dimension2D(6, 6), Color.WHITE);
        //gridView.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.THICK)));
        
        List<GameStage> gameStages = createGameStages();
        gameStages.get(0).applyToGrid(gridView);
        Piece p = gameStages.get(0).getPieces().get(0);
        if(gridView.movePiece(p, new Dimension2D(0, 0)))
            System.out.println("ca marche");
        
        gridView.getModel().print();
        
        gridView.setOnMousePressed((MouseEvent event) -> {
            Node node = event.getPickResult().getIntersectedNode();
            Dimension2D pos = new Dimension2D((int)(node.getLayoutY()/100), (int)(node.getLayoutX()/100));
            System.out.println("pos = " + pos);
            selectedPiece = gridView.getModel().getPiece(pos);
            System.out.println("p = " + selectedPiece);
        });
        
        gridView.setOnMouseReleased((MouseEvent event) -> {
            Node node = event.getPickResult().getIntersectedNode();
            Dimension2D pos = new Dimension2D((int)(node.getLayoutY()/100), (int)(node.getLayoutX()/100));
            System.out.println("pos = " + pos);
            if(selectedPiece != null) {
                boolean isRow = (selectedPiece.getSize().getHeight() > selectedPiece.getSize().getWidth());
                if(isRow && selectedPiece.getPos().getWidth() == pos.getWidth() || !isRow && selectedPiece.getPos().getHeight() == pos.getHeight())
                    gridView.movePiece(selectedPiece, pos);
            }
            
            selectedPiece = null;
        });
        
        final Scene scene = new Scene(gridView, 700, 700);
        primaryStage.setTitle("Rush Hour !");
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
