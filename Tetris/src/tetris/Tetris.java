package tetris;
  
import javafx.application.Application; 
import javafx.geometry.Dimension2D;
import javafx.scene.Scene; 
import javafx.scene.paint.Color; 
import javafx.stage.Stage; 
import libplateau.model.Piece;
import libplateau.view.GridView;
  
public class Tetris extends Application { 
  
	@Override 
	public void start(Stage primaryStage) throws Exception { 
            
            GridView gridView = new GridView(new Dimension2D(8, 8), Color.GRAY);
            Piece piece = new Piece(new boolean[][]{{true, true}, {true, false}});
            Piece p2 = new Piece(new boolean[][]{{false, true}, {true, true}});
            gridView.addPiece(piece, new Dimension2D(0, 0));
            gridView.addPiece(p2, new Dimension2D(1,0));
            final Scene scene = new Scene(gridView, 500, 500);
            primaryStage.setTitle("Test dans Netbeans");
            primaryStage.setScene(scene);
            primaryStage.show();
            gridView.getModel().print();
            gridView.movePiece(piece, new Dimension2D(0,1));
            System.out.println("--");
            gridView.getModel().print();
	} 
  
	public static void main(String... args) { 
            Application.launch(args); 
	} 
}