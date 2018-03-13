/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rushhour;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Dimension2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
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
            
            final Scene scene = new Scene(gridView, 600,600);
            primaryStage.setTitle("Ruuuuuuuuuush Houuuuuuuuuuuuuur !");
            primaryStage.setScene(scene);
            primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
