package Control;

import Model.MainFrameModel;
import View.DrawingArea;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class pressEraseButtonActionListener extends AbstractAction {

    private MainFrameModel model;
    private JButton button;
    private DrawingArea area;
    public pressEraseButtonActionListener(MainFrameModel model, JButton button, DrawingArea area)
    {
        this.model = model;
        this.button = button;
        this.area = area;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        model.erase();
        area.eraseBackground();
        model.notifyAllListeners();
        button.setSelected(false);
    }
}
