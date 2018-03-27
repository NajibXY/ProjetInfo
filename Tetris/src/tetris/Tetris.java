package tetris;
  
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import javafx.application.Application; 
import javafx.event.EventHandler;
import javafx.geometry.Dimension2D;
import javafx.scene.Scene; 
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage; 
import libplateau.model.Piece;
import libplateau.view.GridView;
  
public class Tetris extends Application { 
    
    private GridView gridView;
    private Piece currentPiece;
    private int interval = 1;
    private boolean isGameFinished = false;
  
    @Override 
    public void start(Stage primaryStage) throws Exception { 

        gridView = new GridView(new Dimension2D(20, 10), 30);

        currentPiece = PieceFactory.generate(PieceEnum.getRandomPieceEnum());
        gridView.addPiece(currentPiece, currentPiece.getPos());
        
        TetrisThread thread = new TetrisThread();
        thread.run();
        
        gridView.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                System.out.println(event.getCode());
                switch(event.getCode())
                {
                    case DOWN :
                        gridView.movePiece(currentPiece, new Dimension2D(currentPiece.getPos().getWidth() + 1, currentPiece.getPos().getHeight()));
                        break;
                    case LEFT :
                        gridView.movePiece(currentPiece, new Dimension2D(currentPiece.getPos().getWidth(), currentPiece.getPos().getHeight() - 1));
                        break;
                    case RIGHT :
                        gridView.movePiece(currentPiece, new Dimension2D(currentPiece.getPos().getWidth(), currentPiece.getPos().getHeight() + 1));
                        break;
                    case UP :
                        while(gridView.movePiece(currentPiece, new Dimension2D(currentPiece.getPos().getWidth() + 1, currentPiece.getPos().getHeight())));
                        break;
                    case SPACE :
                        // rotate
                        break;
                }
            }
        });

        final Scene scene = new Scene(gridView);
        primaryStage.setTitle("Tetris");
        primaryStage.setScene(scene);
        primaryStage.show();
        gridView.requestFocus();
    }
    
    private class TetrisThread extends Thread
    {
        @Override
        public void run()
        {
            int timeSinceLastUpdate = 0;
            do
            {
                int elapsedTime = LocalDateTime.now().getNano()/1000000;
                timeSinceLastUpdate += elapsedTime;
                
                if(timeSinceLastUpdate > interval*1000)
                {
                    timeSinceLastUpdate -= interval*1000;
                    
                    if(!gridView.movePiece(currentPiece, new Dimension2D(currentPiece.getPos().getWidth() + 1, currentPiece.getPos().getHeight()))) {
                        currentPiece = PieceFactory.generate(PieceEnum.getRandomPieceEnum());
                        if(!gridView.addPiece(currentPiece, currentPiece.getPos()))
                            isGameFinished = true;
                    }
                }
            }
            while(!isGameFinished);
        }
    }

    public static void main(String... args) { 
        Application.launch(args); 
    }
}