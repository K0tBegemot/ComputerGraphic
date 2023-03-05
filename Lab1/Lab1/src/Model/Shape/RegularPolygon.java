package Model.Shape;

import java.awt.*;

public class RegularPolygon extends GShape
{
    private Dot center;

    public RegularPolygon(Dot newCenter, int newRadius, int newNumberOfVertices, int newRotationAngle, Color color, int lineThickness, TYPE type)
    {
        super(color, lineThickness);
        center = newCenter;
        setRadius(newRadius);
        setNumberOfVertices(newNumberOfVertices);
        setRotationAngleInDegree(newRotationAngle);
        setType(type);
    }

    @Override
    public boolean contain(Dot dot)
    {
        return center.equals(dot);
    }

    public void setCenter(Dot newCenter)
    {
        center = newCenter;
    }

    public Dot getCenter() {
        return center;
    }
}
