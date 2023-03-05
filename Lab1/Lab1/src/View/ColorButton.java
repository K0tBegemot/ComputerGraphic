package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public class ColorButton extends CustomMenuedRadioButton
{
    public JRadioButtonMenuItem buttonItem;
    public ColorButton(Color color, String name)
    {
        setPreferredSize(new Dimension(50, 50));
        BufferedImage icon = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
        Graphics2D iconGraphics = (Graphics2D) icon.getGraphics();
        iconGraphics.setColor(Color.WHITE);
        iconGraphics.fillRect(0, 0, 50, 50);
        iconGraphics.setColor(color);
        iconGraphics.fillRect(10, 10, 30, 30);
        setIcon(new ImageIcon(icon));
        BufferedImage icon_2 = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
        Graphics2D iconGraphics_2 = (Graphics2D) icon_2.getGraphics();
        iconGraphics_2.setColor(Color.GRAY);
        iconGraphics_2.fillRect(0, 0, 50, 50);
        iconGraphics_2.setColor(color);
        iconGraphics_2.fillRect(10, 10, 30, 30);
        setSelectedIcon(new ImageIcon(icon_2));
        setToolTipText(name);
        buttonItem = new CustomRadioButtonMenuItem(name, new ArrayList<>(Arrays.asList(this)));
    }
    public ColorButton(GradientPaint paint)
    {
        setPreferredSize(new Dimension(50, 50));
        BufferedImage icon = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
        Graphics2D iconGraphics = (Graphics2D) icon.getGraphics();
        iconGraphics.setColor(Color.WHITE);
        iconGraphics.fillRect(0, 0, 50, 50);
        GradientPaint gradient = paint;
        iconGraphics.setPaint(gradient);
        iconGraphics.fill(new Rectangle(10, 10, 30, 30));
        setIcon(new ImageIcon(icon));

        BufferedImage icon_2 = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
        Graphics2D iconGraphics_2 = (Graphics2D) icon_2.getGraphics();
        iconGraphics_2.setColor(Color.GRAY);
        iconGraphics_2.fillRect(0, 0, 50, 50);
        GradientPaint gradient_2 = paint;
        iconGraphics_2.setPaint(gradient_2);
        iconGraphics_2.fill(new Rectangle(10, 10, 30, 30));
        setSelectedIcon(new ImageIcon(icon_2));
        setToolTipText("Choose your color");
        buttonItem = new CustomRadioButtonMenuItem("Choose your color", new ArrayList<>(Arrays.asList(this)));
    }
}
