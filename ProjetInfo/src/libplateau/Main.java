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
            
            /*GridView gridView = new GridView(new Dimension2D(3, 3), Color.GRAY);
            Piece piece = new Piece(new boolean[][]{{true, true}, {true, false}});
            gridView.addPiece(piece, new Dimension2D(0, 0));
            final Scene scene = new Scene(gridView, 500, 500);
            primaryStage.setTitle("Test dans Netbeans");
            primaryStage.setScene(scene);
            primaryStage.show();*/
            
            Piece p = new Piece(new boolean[][]{{true, true ,true}, {true, false,true}});
            boolean[][] tab = p.getTab();
            for(int i=0;i<tab.length;i++){
                for(int j=0; j<tab[i].length;j++){
                    System.out.print(tab[i][j]);
                }
                System.out.println("");
            }
            System.out.println("");
            p.rotate();
            boolean[][] tab2 = p.getTab();
            for(int i=0;i<tab2.length;i++){
                for(int j=0; j<tab2[i].length;j++){
                    System.out.print(tab2[i][j]);
                }
                System.out.println("");
            }
	} 
  
	public static void main(String... args) { 
            Application.launch(args); 
	} 
}