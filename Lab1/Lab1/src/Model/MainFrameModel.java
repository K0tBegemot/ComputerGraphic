package Model;

import Control.ModelListener;
import Control.ModelObservable;
import Model.Shape.GShape;
import Model.Shape.GraphicShape;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainFrameModel implements ModelObservable {
    public static enum STATES{WAITING, CHOOSING_NEW_SHAPE, EDITING_NEW_SHAPE};

    private STATES currentState;
    private List<ModelListener> arrayOfListeners;
    private List<GraphicShape> image;
    private Color currentDrawingColor;
    private int currentLineThickness;
    private int currentNumberOfVertices;
    private int currentRadius;
    private boolean isClosedDottedCurve;
    private int rotationAngle;
    private GShape.TYPE type;

    private GraphicShape tmpShape;
    private boolean isTmpShapeVisible;
    public static final Color defaultColor = Color.BLACK;
    public static final int defaultLineThickness = 1;
    public static final int defaultNumberOfVertices = 3;
    public static final int defaultRadius = 10;
    public static final boolean defaultClosedStatus = false;
    public static final int defaultRotationAngle = 0;
    public static final GShape.TYPE defaultType = GShape.TYPE.POLY;

    public MainFrameModel()
    {
        currentState = STATES.WAITING;
        image = new ArrayList<GraphicShape>();
        arrayOfListeners = new ArrayList<>();
        currentDrawingColor = defaultColor;
        currentLineThickness = defaultLineThickness;
        currentNumberOfVertices = defaultNumberOfVertices;
        currentRadius = defaultRadius;
        isClosedDottedCurve = defaultClosedStatus;
        rotationAngle = defaultRotationAngle;
        type = defaultType;
        tmpShape = null;
        isTmpShapeVisible = false;
    }

    public GShape.TYPE getType() {
        return type;
    }

    public void setType(GShape.TYPE type) {
        this.type = type;
        notifyAllListeners();
    }

    public int getRotationAngle() {
        return rotationAngle;
    }

    public void setRotationAngle(int rotationAngle) {
        this.rotationAngle = rotationAngle;
        notifyAllListeners();
    }

    public boolean isClosedDottedCurve() {
        return isClosedDottedCurve;
    }

    public void setClosedDottedCurve(boolean closedDottedCurve) {
        isClosedDottedCurve = closedDottedCurve;
        notifyAllListeners();
    }

    public int getImageShapesNumber()
    {
        return image.size();
    }

    public void addTmpToImage()
    {
        image.add(tmpShape);
        setTmpShape(null);
        currentState = STATES.WAITING;
        notifyAllListeners();
    }

    public void erase()
    {
        image = new ArrayList<>();
        setTmpShape(null);
        currentState = STATES.WAITING;
        notifyAllListeners();
    }

    public int getCurrentRadius() {
        return currentRadius;
    }

    public void setCurrentRadius(int currentRadius) {
        this.currentRadius = currentRadius;
        notifyAllListeners();
    }

    public int getCurrentNumberOfVertices() {
        return currentNumberOfVertices;
    }

    public void setCurrentNumberOfVertices(int currentNumberOfVertices) {
        this.currentNumberOfVertices = currentNumberOfVertices;
        notifyAllListeners();
    }

    public void setTmpShape(GraphicShape tmpShape) {
        if(isTmpShapeVisible == true)
        {
            isTmpShapeVisible = false;
        }
        this.tmpShape = tmpShape;
        currentState = STATES.CHOOSING_NEW_SHAPE;
        notifyAllListeners();
    }

    public void showTmpShape()
    {
        isTmpShapeVisible = true;
        currentState = STATES.EDITING_NEW_SHAPE;
        notifyAllListeners();
    }

    public STATES getCurrentState() {
        return currentState;
    }

    public GraphicShape getTmpShape() {
        return tmpShape;
    }

    public final List<GraphicShape> getImage()
    {
        return image;
    }

    public void setCurrentDrawingColor(Color newLineColor)
    {
        if(newLineColor != null)
        {
            currentDrawingColor = newLineColor;
        }else {
            currentDrawingColor = defaultColor;
        }
        notifyAllListeners();
    }

    public Color getCurrentDrawingColor()
    {
        return currentDrawingColor;
    }

    public void setCurrentLineThickness(int newLineThickness) {
        if(newLineThickness >= 0) {
            this.currentLineThickness = newLineThickness;
        }else {
            this.currentLineThickness = defaultLineThickness;
        }
        notifyAllListeners();
    }

    public int getCurrentLineThickness() {
        return currentLineThickness;
    }

    public boolean isTmpShapeVisible() {
        return isTmpShapeVisible;
    }

    @Override
    public void addModelListener(ModelListener listener) {
        arrayOfListeners.add(listener);
    }

    @Override
    public void deleteModelListener(int index) {
        arrayOfListeners.remove(index);
    }

    @Override
    public void notifyAllListeners() {
        for(ModelListener i : arrayOfListeners)
        {
            //System.err.println("<- " + getCurrentState().toString());
            i.update(currentState);
        }
    }
}
