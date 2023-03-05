package Control;

import Model.MainFrameModel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class pressIsClosedButtonActionListener extends AbstractAction
{
    private MainFrameModel model;
    boolean isClosedDottedCurve;
    public pressIsClosedButtonActionListener(MainFrameModel model, boolean isClosedDottedCurve)
    {
        this.model = model;
        this.isClosedDottedCurve = isClosedDottedCurve;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        model.setClosedDottedCurve(isClosedDottedCurve);
    }
}
