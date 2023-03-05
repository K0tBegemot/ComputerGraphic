package View;

import Control.pressSaveButtonActionListener;
import Model.MainFrameModel;

import javax.swing.*;
import java.awt.*;

public class SpanFillEditWindow extends JInternalFrame
{
    MainFrameModel model;
    SpanFillEditWindow(MainFrameModel model)
    {
        super("Span Fill Parameter Window", true, false, false, false);
        this.model = model;
        getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT)); //new BoxLayout(getContentPane(), BoxLayout.X_AXIS)
        setPreferredSize(new Dimension(1920, 150));
        setMinimumSize(new Dimension(1920, 150));
        setMaximumSize(new Dimension(1920, 150));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        JPanel saveButtonPanel = new JPanel();
        CustomButton saveButton = new CustomButton();
        saveButton.setText("Save");
        saveButton.addActionListener(new pressSaveButtonActionListener(model));
        saveButtonPanel.add(saveButton);
        saveButtonPanel.setPreferredSize(new Dimension(300, 150));
        add(saveButtonPanel);
    }
}