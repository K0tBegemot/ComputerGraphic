package Control;

import Model.MainFrameModel;
import Model.Shape.Dot;
import Model.Shape.SpanFill;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class pressFillButtonActionListener extends AbstractAction {
    private MainFrameModel model;

    public pressFillButtonActionListener(MainFrameModel model)
    {
        super();
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        model.setTmpShape(new SpanFill(model.getCurrentDrawingColor(), new Dot(0, 0)));
        model.notifyAllListeners();
    }
}
