package libplateau.view;

import java.util.Observable;
import java.util.Observer;
import javafx.geometry.Dimension2D;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import libplateau.model.Grid;
import libplateau.model.Piece;

public class GridView extends GridPane implements Observer {
    public GridView(Dimension2D dim, Color color)
    {
        gridModel = new Grid(dim, color);
        
        for(int i = 0; i < dim.getHeight(); i++)
        {
            for(int j = 0; j < dim.getWidth(); j++)
            {
                Rectangle rect = new Rectangle(100, 100);
                rect.setFill(color);
                rect.setStroke(Color.BLACK);
                this.add(rect, i, j);
            }
        }
        
        gridModel.addObserver(this);
    }
    
    public boolean addPiece(Piece piece, Dimension2D pos) {
        return gridModel.addPiece(piece, pos);
    }
    
    public boolean movePiece(Piece piece, Dimension2D npos) {
        return gridModel.movePiece(piece, npos);
    }
    
    public Grid getModel() {
        return this.gridModel;
    }

    @Override
    public void update(Observable o, Object arg) {
        for(int i = 0; i < gridModel.getDim().getWidth(); i++)
        {
            for(int j = 0; j < gridModel.getDim().getHeight(); j++)
            {
                ((Rectangle)(this.getChildren().get((int)gridModel.getDim().getWidth()*j + i))).setFill(gridModel.getGrid()[i][j].getColor());
            }
        }
    }
    
    private Grid gridModel;
}
