package libplateau.view;

import java.util.Observable;
import java.util.Observer;
import javafx.geometry.Dimension2D;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import libplateau.model.Grid;
import libplateau.model.Piece;

public class GridView extends Parent implements Observer {
    public GridView(Dimension2D dim, Color color)
    {
        gridModel = new Grid(dim, color);
        
        GridPane grid = new GridPane();
        for(int i = 0; i < dim.getHeight(); i++)
        {
            for(int j = 0; j < dim.getWidth(); j++)
            {
                Rectangle rect = new Rectangle(100, 100);
                rect.setFill(color);
                rect.setStroke(Color.BLACK);
                grid.add(rect, i, j);
            }
        }
        
        this.getChildren().add(grid);
        gridModel.addObserver(this);
    }
    
    public void addPiece(Piece piece, Dimension2D pos) {
        gridModel.addPiece(piece, pos);
    }

    private Grid gridModel;

    @Override
    public void update(Observable o, Object arg) {
        o.notifyObservers();
    }
}
