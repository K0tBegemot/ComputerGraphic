package View;

import Control.*;
import Model.MainFrameModel;

import javax.swing.*;
import java.awt.*;

public class RegularPolygonEditWindow extends JInternalFrame
{
    private MainFrameModel model;
    public CustomRadioButton polygonButton;
    public CustomRadioButton starButton;

    RegularPolygonEditWindow(MainFrameModel model)
    {
        super("Regular Polygon Parameter Window", true, false, false, false);
        this.model = model;
        getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT)); //new BoxLayout(getContentPane(), BoxLayout.X_AXIS)
        setPreferredSize(new Dimension(1920, 150));
        setMinimumSize(new Dimension(1920, 150));
        setMaximumSize(new Dimension(1920, 150));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JPanel binPanel = new JPanel();
        ButtonGroup group = new ButtonGroup();
        CustomRadioButton polygonButton = new CustomRadioButton();
        polygonButton.setText("Polygon");
        polygonButton.addActionListener(new pressPolygonButtonActionListener(model));
        group.add(polygonButton);
        binPanel.add(polygonButton);
        this.polygonButton = polygonButton;
        CustomRadioButton starButton = new CustomRadioButton();
        starButton.setText("Star");
        starButton.addActionListener(new pressStarButtonActionListener(model));
        group.add(starButton);
        binPanel.add(starButton);
        this.starButton = starButton;
        binPanel.setPreferredSize(new Dimension(400, 150));
        add(binPanel);
        add(new ParameterPanel(new RadiusListener(model), "Radius", "Radius of Polygon: min - 10 px; max - 1920 px; step - 1 px", 10, 1920, 1));
        add(new ParameterPanel(new NumberOfVerticesListener(model), "Number of vertices",
                "Number of vertices : min - 3; max - 16; step - 1", 3, 16, 1));
        add(new ParameterPanel(new AngleRotationListener(model), "Angle rotation",
                "Number of degrees: min - 0 degree; max - 359 degree; step - 1 degree", 0, 359, 1));
        JPanel saveButtonPanel = new JPanel();
        CustomButton saveButton = new CustomButton();
        saveButton.setText("Save");
        saveButton.addActionListener(new pressSaveButtonActionListener(model));
        saveButtonPanel.add(saveButton);
        saveButtonPanel.setPreferredSize(new Dimension(300, 150));
        add(saveButtonPanel);
    }
}
