package Control;

import Model.MainFrameModel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class pressSaveButtonActionListener extends AbstractAction
{
    private MainFrameModel model;
    public pressSaveButtonActionListener(MainFrameModel model)
    {
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        model.addTmpToImage();
    }
}
