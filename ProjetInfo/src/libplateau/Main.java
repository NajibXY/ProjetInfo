package libplateau; 
  
import javafx.application.Application; 
import javafx.scene.Scene; 
import javafx.scene.layout.GridPane;
import javafx.stage.Stage; 
  
public class Main extends Application { 
  
	@Override 
	public void start(Stage primaryStage) throws Exception { 
            final Scene scene = new Scene(new GridPane(), 500, 500);
            primaryStage.setTitle("Test dans Netbeans");
            primaryStage.setScene(scene);
            primaryStage.show();
	} 
  
	public static void main(String... args) { 
            Application.launch(args); 
	} 
}