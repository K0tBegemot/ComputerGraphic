package Model.Shape;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DottedCurve extends GShape
{
    private List<Dot> arrayOfDots;
    private Color currentLineColor;
    public DottedCurve(Dot firstDot, boolean isClosedCurve, Color color, int lineThickness)
    {
        super(color, lineThickness);
        setClosedCurve(isClosedCurve);
        this.arrayOfDots = new ArrayList<>();
        this.arrayOfDots.add(firstDot);
    }

    @Override
    public boolean contain(Dot dot)
    {
        boolean retValue = false;
        for (Dot dotInArray : arrayOfDots)
        {
            if (dotInArray.equals(dot))
            {
                retValue = true;
                break;
            }
        }
        return retValue;
    }

    public void addDot(Dot newDot)
    {
        arrayOfDots.add(newDot);
    }
    public void deleteDot(int index)
    {
        arrayOfDots.remove(index);
    }
    public void changeDot(int index, int newXCoord, int newYCoord)
    {
        Dot currentDot = arrayOfDots.get(index);
        currentDot.xCoord = newXCoord;
        currentDot.yCoord = newYCoord;
    }

    public final List<Dot> getArrayOfDots() {
        return arrayOfDots;
    }
}
