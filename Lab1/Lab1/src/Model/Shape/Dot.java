package Model.Shape;

public class Dot
{
    public int xCoord;
    public int yCoord;

    public Dot(int x, int y) {
        xCoord = x;
        yCoord = y;
    }

    public boolean equals(Dot obj) {
        if(!super.equals(obj))
        {
            return false;
        }
        return (xCoord == obj.xCoord) && (yCoord == obj.yCoord);
    }
}
