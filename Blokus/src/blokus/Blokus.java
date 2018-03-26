/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blokus;

import static java.lang.System.exit;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Dimension2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import libplateau.model.Piece;
import libplateau.view.GridView;

/**
 *
 * @author Axel
 */
public class Blokus extends Application {
    
    private GridView gridView;
    private GridView pressedGrid;
    private Piece selectedPiece;
    private int currentPlayer = 0;
    private Rectangle leftRect = new Rectangle(240, 40, Color.BLUE);
    private Rectangle rightRect = new Rectangle(240, 40, Color.BLUE);
    
    @Override
    public void start(Stage primaryStage) {
        gridView = new GridView(new Dimension2D(20, 20), 25);
        
        BorderPane borderPane = new BorderPane(gridView);
        
        GridView topLeft = new GridView(new Dimension2D(15, 15), 15);
        for(Piece p : PieceFactory.generateDeck(Color.BLUE))
        {
            topLeft.addPiece(p, p.getPos());
        }
        
        GridView bottomLeft = new GridView(new Dimension2D(15, 15), 15);
        for(Piece p : PieceFactory.generateDeck(Color.GREEN))
        {
            bottomLeft.addPiece(p, p.getPos());
        }
        
        GridView topRight = new GridView(new Dimension2D(15, 15), 15);
        for(Piece p : PieceFactory.generateDeck(Color.YELLOW))
        {
            topRight.addPiece(p, p.getPos());
        }
        
        GridView bottomRight = new GridView(new Dimension2D(15, 15), 15);
        for(Piece p : PieceFactory.generateDeck(Color.RED))
        {
            bottomRight.addPiece(p, p.getPos());
        }
        
        borderPane.setLeft(new VBox(0, topLeft, leftRect, bottomLeft));
        borderPane.setRight(new VBox(0, topRight, rightRect, bottomRight));
        
        topLeft.setOnMouseClicked(new PickPieceEventHandler(topLeft));
        
        gridView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event){
                Node node = event.getPickResult().getIntersectedNode();
                Dimension2D pos = new Dimension2D((int)(node.getLayoutY()/25), (int)(node.getLayoutX()/25));
                if(selectedPiece != null)
                {
                    Dimension2D oldPos = selectedPiece.getPos();
                    if(gridView.addPiece(selectedPiece, pos)) {
                        selectedPiece.setPos(oldPos);
                        pressedGrid.removePiece(selectedPiece);
                        currentPlayer = (currentPlayer + 1) % 4;
                        
                        switch(currentPlayer)
                        {
                            case 0 :
                                topLeft.setOnMouseClicked(new PickPieceEventHandler(topLeft));
                                bottomLeft.setOnMouseClicked(null);
                                topRight.setOnMouseClicked(null);
                                bottomRight.setOnMouseClicked(null);
                                leftRect.setFill(Color.BLUE);
                                rightRect.setFill(Color.BLUE);
                                break;
                            case 1 :
                                topLeft.setOnMouseClicked(null);
                                bottomLeft.setOnMouseClicked(null);
                                topRight.setOnMouseClicked(new PickPieceEventHandler(topRight));
                                bottomRight.setOnMouseClicked(null);
                                leftRect.setFill(Color.YELLOW);
                                rightRect.setFill(Color.YELLOW);
                                break;
                            case 2 :
                                topLeft.setOnMouseClicked(null);
                                bottomLeft.setOnMouseClicked(null);
                                topRight.setOnMouseClicked(null);
                                bottomRight.setOnMouseClicked(new PickPieceEventHandler(bottomRight));
                                leftRect.setFill(Color.RED);
                                rightRect.setFill(Color.RED);
                                break;
                            case 3 :
                                topLeft.setOnMouseClicked(null);
                                bottomLeft.setOnMouseClicked(new PickPieceEventHandler(bottomLeft));
                                topRight.setOnMouseClicked(null);
                                bottomRight.setOnMouseClicked(null);
                                leftRect.setFill(Color.GREEN);
                                rightRect.setFill(Color.GREEN);
                                break;
                        }
                    }
                    selectedPiece = null;
                    if(pressedGrid.getModel().getPieces().isEmpty())
                    {
                        Alert alertEndGame = new Alert(Alert.AlertType.INFORMATION);
                        alertEndGame.setTitle("Victoire");
                        alertEndGame.setHeaderText(null);
                        alertEndGame.setContentText("Le joueur " + "" + " a gagné !!!");
                        alertEndGame.showAndWait();

                        exit(0);
                    }
                }
            }
        });
        
        final Scene scene = new Scene(borderPane);
        primaryStage.setTitle("Blokus");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private class PickPieceEventHandler implements EventHandler<MouseEvent> {
        public PickPieceEventHandler(GridView thisGrid)
        {
            currentGridView = thisGrid;
        }
        
        @Override
        public void handle(MouseEvent event){
            Node node = event.getPickResult().getIntersectedNode();
            Dimension2D pos = new Dimension2D((int)(node.getLayoutY()/15), (int)(node.getLayoutX()/15));
            selectedPiece = currentGridView.getModel().getPiece(pos);
            pressedGrid = currentGridView;
        }
        
        private final GridView currentGridView;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
