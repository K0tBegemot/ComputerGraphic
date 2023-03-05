package Control;

import Model.MainFrameModel;
import View.PaintFrame;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;

public class pressColorPickerButtonActionListener extends AbstractAction
{
    private class colorChangeListener implements ChangeListener
    {
        private MainFrameModel model;
        public colorChangeListener(MainFrameModel model)
        {
            this.model = model;
        }

        @Override
        public void stateChanged(ChangeEvent changeEvent) {
            model.setCurrentDrawingColor(chooser.getColor());
        }
    }
    private PaintFrame frame;
    private JDialog dialog;
    private JColorChooser chooser;
    public pressColorPickerButtonActionListener(PaintFrame frame, MainFrameModel model)
    {
        this.frame = frame;
        this.dialog = new JDialog(frame);
        dialog.setVisible(false);
        dialog.setLayout(new BorderLayout());
        dialog.setAlwaysOnTop(true);
        dialog.setPreferredSize(new Dimension(800, 400));
        dialog.setMinimumSize(new Dimension(800, 400));
        dialog.setLocation(1100, 175);
        chooser = new JColorChooser(Color.BLACK);
        chooser.getSelectionModel().addChangeListener(new colorChangeListener(model));
        dialog.add(chooser);
        dialog.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        dialog.setVisible(true);
    }
}
