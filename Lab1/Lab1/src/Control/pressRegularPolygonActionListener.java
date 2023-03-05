package Control;

import Model.MainFrameModel;
import Model.Shape.Dot;
import Model.Shape.RegularPolygon;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class pressRegularPolygonActionListener extends AbstractAction
{
    private MainFrameModel model;

    public pressRegularPolygonActionListener(MainFrameModel model)
    {
        super();
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        model.setTmpShape(new RegularPolygon(new Dot(0, 0), model.getCurrentRadius(),
                model.getCurrentNumberOfVertices(), model.getRotationAngle(), model.getCurrentDrawingColor(), model.getCurrentLineThickness(), model.getType()));
        model.notifyAllListeners();
    }
}
