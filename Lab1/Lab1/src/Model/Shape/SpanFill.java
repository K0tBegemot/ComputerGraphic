package Model.Shape;

import java.awt.*;

public class SpanFill extends GShape
{
    private Dot startDot;
    public SpanFill(Color color, Dot startDot)
    {
        super(color, -1);
        this.startDot = startDot;
    }

    public void setStartDot(int x, int y) {
        this.startDot.xCoord = x;
        this.startDot.yCoord = y;
    }

    public Dot getStartDot() {
        return startDot;
    }

    @Override
    public boolean contain(Dot dot) {
        return false;
    }
}
