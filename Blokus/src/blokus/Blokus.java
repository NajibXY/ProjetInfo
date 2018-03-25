/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blokus;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Dimension2D;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
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
    
    @Override
    public void start(Stage primaryStage) {
        gridView = new GridView(new Dimension2D(20, 20), 20, Color.WHITE);
        
        BorderPane borderPane = new BorderPane(gridView);
        
        Rectangle blueRect = new Rectangle(100, 100, Color.WHITE);
        blueRect.setStroke(Color.BLUE);
        Rectangle yellowRect = new Rectangle(100, 100, Color.WHITE);
        blueRect.setStroke(Color.YELLOW);
        Rectangle greenRect = new Rectangle(100, 100, Color.WHITE);
        blueRect.setStroke(Color.GREEN);
        Rectangle redRect = new Rectangle(100, 100, Color.WHITE);
        blueRect.setStroke(Color.RED);
        
        borderPane.setLeft(new VBox(10, new ChoiceBox<>(createBlokusPieces(Color.BLUE)), yellowRect));
        borderPane.setRight(new VBox(10, greenRect, redRect));
        
        gridView.setOnMouseClicked((MouseEvent event) -> {
            
        });
        
        final Scene scene = new Scene(borderPane);
        primaryStage.setTitle("Rush Hour !");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public ObservableList<Piece> createBlokusPieces(Color color)
    {
        ArrayList<Piece> blokusPieces = new ArrayList<>();
        
        blokusPieces.add(new Piece(new boolean[][] {{true, true, true, true, true}}, color));
        blokusPieces.add(new Piece(new boolean[][] {{true, true, true, true}, {true, false, false, false}}, color));
        blokusPieces.add(new Piece(new boolean[][] {{false, true, true, true}, {true, true, false, false}}, color));
        blokusPieces.add(new Piece(new boolean[][] {{true, true, true}, {true, false, false}, {true, false, false}}, color));
        blokusPieces.add(new Piece(new boolean[][] {{false, true, true}, {true, true, false}, {true, false, false}}, color));
        
        return FXCollections.observableArrayList(blokusPieces);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
