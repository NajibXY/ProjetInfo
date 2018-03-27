package tetris;
  
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application; 
import javafx.event.EventHandler;
import javafx.geometry.Dimension2D;
import javafx.scene.Scene; 
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage; 
import javafx.stage.WindowEvent;
import libplateau.model.Piece;
import libplateau.view.GridView;
  
public class Tetris extends Application { 
    
    private GridView gridView;
    private Piece currentPiece;
    private int interval = 1000;
    private boolean isGameFinished = false;
    Timer timer = new Timer();
  
    @Override 
    public void start(Stage primaryStage) throws Exception { 
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                timer.cancel();
                primaryStage.close();
            }
        });
        
        gridView = new GridView(new Dimension2D(20, 10), 30);

        currentPiece = PieceFactory.generate(PieceEnum.getRandomPieceEnum());
        gridView.addPiece(currentPiece, currentPiece.getPos());
        
        timer.schedule (new TimerTask() {
            @Override
            public void run()
            {
                if(!gridView.movePiece(currentPiece, new Dimension2D(currentPiece.getPos().getWidth() + 1, currentPiece.getPos().getHeight()))) {
                    currentPiece = PieceFactory.generate(PieceEnum.getRandomPieceEnum());
                    if(!gridView.addPiece(currentPiece, currentPiece.getPos()))
                        isGameFinished = true;
                }
            }
        }, 0, interval);
        
        gridView.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
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
                        gridView.rotatePiece(currentPiece, true);
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
    
    /*private class TetrisThread extends Thread
    {
        @Override
        public void run()
        {
            Clock clock = Clock.tickSeconds(ZoneId.systemDefault());
            long timeSinceLastUpdate = 0;
            do
            {
                long elapsedTime = clock.millis();
                timeSinceLastUpdate += elapsedTime;
                
                while(timeSinceLastUpdate > interval)
                {
                    timeSinceLastUpdate -= interval;
                    
                    if(!gridView.movePiece(currentPiece, new Dimension2D(currentPiece.getPos().getWidth() + 1, currentPiece.getPos().getHeight()))) {
                        currentPiece = PieceFactory.generate(PieceEnum.getRandomPieceEnum());
                        if(!gridView.addPiece(currentPiece, currentPiece.getPos()))
                            isGameFinished = true;
                    }
                }
            }
            while(!isGameFinished);
        }
    }*/

    public static void main(String... args) { 
        Application.launch(args); 
    }
}