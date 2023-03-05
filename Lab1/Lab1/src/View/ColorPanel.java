package View;

import Control.pressColorButtonActionListener;
import Control.pressColorPickerButtonActionListener;
import Model.MainFrameModel;

import javax.swing.*;
import java.awt.*;

public class ColorPanel extends JPanel
{
    public JMenu colorMenu;
    public ColorPanel(PaintFrame frame, MainFrameModel model)
    {
        ColorButton customColorButton = new ColorButton(new GradientPaint(10, 10, Color.BLUE, 39, 39, Color.ORANGE));
        customColorButton.addActionListener(new pressColorPickerButtonActionListener(frame, model));
        ColorButton redButton = new ColorButton(Color.RED, "Red");
        redButton.addActionListener(new pressColorButtonActionListener(model, Color.RED));
        ColorButton rgButton = new ColorButton(new Color(Color.RED.getRGB() + Color.GREEN.getRGB()), "Yellow");
        rgButton.addActionListener(new pressColorButtonActionListener(model, new Color(Color.RED.getRGB() + Color.GREEN.getRGB())));
        ColorButton greenButton = new ColorButton(Color.GREEN, "Green");
        greenButton.addActionListener(new pressColorButtonActionListener(model, Color.GREEN));
        ColorButton gbButton = new ColorButton(new Color(Color.GREEN.getRGB() + Color.BLUE.getRGB()), "Turquoise");
        gbButton.addActionListener(new pressColorButtonActionListener(model, new Color(Color.GREEN.getRGB() + Color.BLUE.getRGB())));
        ColorButton blueButton = new ColorButton(Color.BLUE, "Blue");
        blueButton.addActionListener(new pressColorButtonActionListener(model, Color.BLUE));
        ColorButton brButton = new ColorButton(new Color(Color.RED.getRGB() + Color.BLUE.getRGB()), "Violet");
        brButton.addActionListener(new pressColorButtonActionListener(model, new Color(Color.RED.getRGB() + Color.BLUE.getRGB())));
        ColorButton blackButton = new ColorButton(Color.BLACK, "Black");
        blackButton.addActionListener(new pressColorButtonActionListener(model, Color.BLACK));
        blackButton.setSelected(true);
        setLayout(new GridLayout(2, 4));
        setPreferredSize(new Dimension(210, 100));
        ButtonGroup colorGroup = new ButtonGroup();
        colorGroup.add(customColorButton);
        colorGroup.add(redButton);
        colorGroup.add(rgButton);
        colorGroup.add(greenButton);
        colorGroup.add(gbButton);
        colorGroup.add(blueButton);
        colorGroup.add(brButton);
        colorGroup.add(blackButton);

        add(customColorButton);
        add(redButton);
        add(rgButton);
        add(greenButton);
        add(gbButton);
        add(blueButton);
        add(brButton);
        add(blackButton);

        ButtonGroup menuColorGroup = new ButtonGroup();
        menuColorGroup.add(customColorButton.buttonItem);
        menuColorGroup.add(redButton.buttonItem);
        menuColorGroup.add(rgButton.buttonItem);
        menuColorGroup.add(greenButton.buttonItem);
        menuColorGroup.add(gbButton.buttonItem);
        menuColorGroup.add(blueButton.buttonItem);
        menuColorGroup.add(brButton.buttonItem);
        menuColorGroup.add(blackButton.buttonItem);

        colorMenu = new JMenu("Current Color");

        colorMenu.add(customColorButton.buttonItem);
        colorMenu.add(redButton.buttonItem);
        colorMenu.add(rgButton.buttonItem);
        colorMenu.add(greenButton.buttonItem);
        colorMenu.add(gbButton.buttonItem);
        colorMenu.add(blueButton.buttonItem);
        colorMenu.add(brButton.buttonItem);
        colorMenu.add(blackButton.buttonItem);
        blackButton.buttonItem.setSelected(true);
    }
}
