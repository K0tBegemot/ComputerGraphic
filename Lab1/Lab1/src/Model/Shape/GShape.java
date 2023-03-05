package Model.Shape;

import java.awt.*;

public abstract class GShape implements GraphicShape
{
    public enum TYPE{POLY, STAR};
    private Color currentLineColor;
    private int currentLineThickness;
    private boolean isClosedCurve;
    private int radius;
    private int numberOfVertices;

    private int rotationAngleInDegree;
    private TYPE type;

    @Override
    public void setType(TYPE type) {
        this.type = type;
    }

    @Override
    public TYPE getType() {
        return type;
    }

    public GShape(Color color, int newCurrentLineThickness)
    {
        currentLineColor = color;
        currentLineThickness = newCurrentLineThickness;
    }

    @Override
    public void setRotationAngleInDegree(int rotationAngleInDegree) {
        this.rotationAngleInDegree = rotationAngleInDegree;
    }

    @Override
    public int getRotationAngleInDegree() {
        return rotationAngleInDegree;
    }

    @Override
    public void setNumberOfVertices(int numberOfVertices) {
        this.numberOfVertices = numberOfVertices;
    }

    @Override
    public int getNumberOfVertices() {
        return numberOfVertices;
    }

    @Override
    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public int getRadius() {
        return radius;
    }

    @Override
    public void setLineColor(Color newColor) {
        if(newColor != null) {
            currentLineColor = newColor;
        }
    }

    @Override
    public Color getLineColor()
    {
        return currentLineColor;
    }

    @Override
    public int getCurrentLineThickness() {
        return currentLineThickness;
    }

    @Override
    public void setCurrentLineThickness(int currentLineThickness) {
        this.currentLineThickness = currentLineThickness;
    }
    @Override
    public void setClosedCurve(boolean closedCurve) {
        isClosedCurve = closedCurve;
    }
    @Override
    public boolean isClosedCurve() {
        return isClosedCurve;
    }
}
