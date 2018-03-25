/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rushhour;

import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Dimension2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    private List<GameStage> gameStages;
    private int currentGameStage = 0;
    private GridView gridView;
    
    @Override
    public void start(Stage primaryStage) {
        gridView = new GridView(new Dimension2D(6, 6), Color.WHITE);
        //gridView.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.THICK)));
        
        gameStages = createGameStages();
        gameStages.get(currentGameStage).applyToGrid(gridView);
        
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
                {
                    gridView.movePiece(selectedPiece, pos);
                    if(gridView.getModel().getGrid()[2][5].getColor().equals(Color.RED))
                    {
                        Alert alertEndStage = new Alert(AlertType.INFORMATION);
                        alertEndStage.setTitle("Victoire");
                        alertEndStage.setHeaderText(null);
                        alertEndStage.setContentText("Bien joué !!!");
                        alertEndStage.showAndWait();
                        
                        currentGameStage++;
                        if(currentGameStage != gameStages.size()) {
                            gameStages.get(currentGameStage).applyToGrid(gridView);
                        }
                        else
                        {
                            Alert alertEndGame = new Alert(AlertType.INFORMATION);
                            alertEndGame.setTitle("Victoire");
                            alertEndGame.setHeaderText(null);
                            alertEndGame.setContentText("Vous avez terminé tous les niveaux. Bravo !!!");
                            alertEndGame.showAndWait();
                            
                            exit(0);
                        }
                    }
                }
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
        List<GameStage> gameStageList = new ArrayList<>();
        
        gameStageList.add(new GameStage(new ArrayList<>(Arrays.asList(new Piece[] {
            new Piece(new boolean[][] {{true, true}}, Color.RED, new Dimension2D(2, 0)),
            new Piece(new boolean[][] {{true}, {true}}, Color.BLUE, new Dimension2D(3, 0)),
            new Piece(new boolean[][] {{true, true}}, Color.YELLOW, new Dimension2D(0, 0))
        }))));
        
        gameStageList.add(new GameStage(new ArrayList<>(Arrays.asList(new Piece[] {
            new Piece(new boolean[][] {{true, true}}, Color.RED, new Dimension2D(2, 0)),
            new Piece(new boolean[][] {{true}, {true}, {true}}, Color.GREEN, new Dimension2D(3, 2)),
            new Piece(new boolean[][] {{true, true, true}}, Color.PURPLE, new Dimension2D(0, 0)),
            new Piece(new boolean[][] {{true}, {true}}, Color.BLUE, new Dimension2D(2, 3))
        }))));
        
        return gameStageList;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
