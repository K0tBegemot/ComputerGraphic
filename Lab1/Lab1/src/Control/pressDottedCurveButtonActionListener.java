package Control;

import Model.MainFrameModel;
import Model.Shape.Dot;
import Model.Shape.DottedCurve;
import Model.Shape.GraphicShape;
import View.PaintFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class pressDottedCurveButtonActionListener extends AbstractAction
{
    private MainFrameModel model;
    private PaintFrame frame;
    public pressDottedCurveButtonActionListener(MainFrameModel newModel)
    {
        model = newModel;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        model.setTmpShape(new DottedCurve(new Dot(0, 0), false, model.getCurrentDrawingColor(), model.getCurrentLineThickness()));
        model.notifyAllListeners();
    }
}
