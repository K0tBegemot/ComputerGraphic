package View;

import Control.pressAboutActionListener;
import Model.MainFrameModel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class AboutPanel extends JPanel
{
    public JMenuItem aboutItem;
    private MainFrameModel model;
    public AboutPanel(MainFrameModel model, PaintFrame frame)
    {
        this.model = model;
        CustomButton aboutProgram = new CustomButton();
        aboutProgram.addActionListener(new pressAboutActionListener(frame));
        aboutProgram.setToolTipText("About Program Information and quick guide");
        add(aboutProgram);
        aboutItem = new CustomMenuItem("About Program", new ArrayList<>(Arrays.asList(aboutProgram)));
    }
}
