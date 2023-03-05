package Control;

import Model.MainFrameModel;
import Model.Shape.Dot;
import Model.Shape.DottedCurve;
import Model.Shape.RegularPolygon;
import Model.Shape.SpanFill;
import View.ParameterPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PaintAreaMouseListener extends MouseAdapter {
    MainFrameModel model;
    public PaintAreaMouseListener(MainFrameModel newModel)
    {
        model = newModel;
    }
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if(model.getTmpShape() != null) {
            if (model.getTmpShape() instanceof DottedCurve) {
                if (model.getCurrentState() == MainFrameModel.STATES.CHOOSING_NEW_SHAPE) {
                    ((DottedCurve) model.getTmpShape()).changeDot(0, mouseEvent.getX(), mouseEvent.getY());
                    model.showTmpShape();
                } else if (model.getCurrentState() == MainFrameModel.STATES.EDITING_NEW_SHAPE) {
                    ((DottedCurve) model.getTmpShape()).addDot(new Dot(mouseEvent.getX(), mouseEvent.getY()));
                }
                model.notifyAllListeners();
            }
            if (model.getTmpShape() instanceof SpanFill) {
                if(model.getCurrentState() == MainFrameModel.STATES.CHOOSING_NEW_SHAPE)
                {
                    ((SpanFill)model.getTmpShape()).setStartDot(mouseEvent.getX(), mouseEvent.getY());
                    model.showTmpShape();
                } else if (model.getCurrentState() == MainFrameModel.STATES.EDITING_NEW_SHAPE) {
                    ((SpanFill)model.getTmpShape()).setStartDot(mouseEvent.getX(), mouseEvent.getY());
                }
                model.notifyAllListeners();
            }
            if(model.getTmpShape() instanceof RegularPolygon)
            {
                if(model.getCurrentState() == MainFrameModel.STATES.CHOOSING_NEW_SHAPE)
                {
                    ((RegularPolygon)model.getTmpShape()).setCenter(new Dot(mouseEvent.getX(), mouseEvent.getY()));
                    model.showTmpShape();
                } else if (model.getCurrentState() == MainFrameModel.STATES.EDITING_NEW_SHAPE) {
                    ((RegularPolygon)model.getTmpShape()).setCenter(new Dot(mouseEvent.getX(), mouseEvent.getY()));
                }
                model.notifyAllListeners();
            }
        }
    }
}
