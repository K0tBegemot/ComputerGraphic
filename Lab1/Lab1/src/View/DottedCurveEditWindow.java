package View;

import Control.pressIsClosedButtonActionListener;
import Control.pressSaveButtonActionListener;
import Model.MainFrameModel;

import javax.swing.*;
import java.awt.*;

public class DottedCurveEditWindow extends JInternalFrame
{
    MainFrameModel model;
    DottedCurveEditWindow(MainFrameModel model)
    {
        super("Dotted Curve Parameter Window", true, false, false, false);
        this.model = model;
        getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT)); //new BoxLayout(getContentPane(), BoxLayout.X_AXIS)
        setPreferredSize(new Dimension(1920, 150));
        setMinimumSize(new Dimension(1920, 150));
        setMaximumSize(new Dimension(1920, 150));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        JPanel binPanel = new JPanel();
        ButtonGroup group = new ButtonGroup();
        CustomRadioButton closedCurveButton = new CustomRadioButton();
        closedCurveButton.setText("Closed");
        closedCurveButton.addActionListener(new pressIsClosedButtonActionListener(model, true));
        group.add(closedCurveButton);
        binPanel.add(closedCurveButton);
        CustomRadioButton openCurveButton = new CustomRadioButton();
        openCurveButton.setText("Opened");
        openCurveButton.addActionListener(new pressIsClosedButtonActionListener(model, false));
        group.add(openCurveButton);
        binPanel.add(openCurveButton);
        binPanel.setPreferredSize(new Dimension(400, 150));
        add(binPanel);
        JPanel saveButtonPanel = new JPanel();
        CustomButton saveButton = new CustomButton();
        saveButton.setText("Save");
        saveButton.addActionListener(new pressSaveButtonActionListener(model));
        saveButtonPanel.add(saveButton);
        saveButtonPanel.setPreferredSize(new Dimension(300, 150));
        add(saveButtonPanel);
    }
}
