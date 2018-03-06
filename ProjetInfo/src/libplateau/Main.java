package libplateau; 
  
import javafx.application.Application; 
import javafx.geometry.Dimension2D;
import javafx.scene.Scene; 
import javafx.scene.paint.Color; 
import javafx.stage.Stage; 
import libplateau.model.Piece;
import libplateau.view.GridView;
  
public class Main extends Application { 
  
	@Override 
	public void start(Stage primaryStage) throws Exception { 
            
            GridView gridView = new GridView(new Dimension2D(3, 3), Color.GRAY);
            Piece piece = new Piece(new boolean[][]{{true, true}, {true, false}});
            gridView.addPiece(piece, new Dimension2D(0, 0));
            final Scene scene = new Scene(gridView, 500, 500);
            primaryStage.setTitle("Test dans Netbeans");
            primaryStage.setScene(scene);
            primaryStage.show();
	} 
  
	public static void main(String... args) { 
            Application.launch(args); 
	} 
}