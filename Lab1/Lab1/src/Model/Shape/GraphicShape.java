package Model.Shape;

import java.awt.*;

public interface GraphicShape {
    public abstract boolean contain(Dot dot);
    public void setLineColor(Color color);
    public Color getLineColor();
    public int getCurrentLineThickness();
    public void setCurrentLineThickness(int currentLineThickness);
    public void setClosedCurve(boolean closedCurve);
    public boolean isClosedCurve();
    public void setRadius(int radius);
    public int getRadius();
    public void setNumberOfVertices(int numberOfVertices);
    public int getNumberOfVertices();
    public void setRotationAngleInDegree(int rotationAngleInDegree);
    public int getRotationAngleInDegree();
    public void setType(GShape.TYPE type);
    public GShape.TYPE getType();
}
