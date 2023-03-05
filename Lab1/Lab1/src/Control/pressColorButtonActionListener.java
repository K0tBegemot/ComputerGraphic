package Control;

import Model.MainFrameModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class pressColorButtonActionListener extends AbstractAction
{
    private MainFrameModel model;
    private Color color;

    public pressColorButtonActionListener(MainFrameModel model, Color color)
    {
        this.model = model;
        this.color = color;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        model.setCurrentDrawingColor(color);
        model.notifyAllListeners();
    }
}
