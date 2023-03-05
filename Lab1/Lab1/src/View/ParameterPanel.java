package View;

import Control.LineThicknessListener;
import Control.NumberListener;
import Model.MainFrameModel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class ParameterPanel extends JPanel
{
    private NumberEditorPane lineThicknessPane;
    private NumberEditorSlider lineThicknessSlider;
    private int sliderMin;
    private int sliderMax;
    private int sliderStep;
    private NumberListener listener;
    ParameterPanel(NumberListener listener, String headerText, String toolTipText, int sliderMin, int sliderMax, int sliderStep)
    {
        super();
        this.listener = listener;
        Border blackLineBorder = BorderFactory.createLineBorder(Color.BLACK);
        setBorder(blackLineBorder);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(350, 108));
        this.sliderMin = sliderMin;
        this.sliderMax = sliderMax;
        this.sliderStep = sliderStep;
        lineThicknessPane = new NumberEditorPane(sliderMin, sliderMax, toolTipText, listener);
        //lineThicknessPane.setText(Integer.toBinaryString(sliderMin));
        lineThicknessSlider = new NumberEditorSlider(sliderMin, sliderMax, sliderMin, lineThicknessPane);
        lineThicknessPane.setPairSlider(lineThicknessSlider);
        JLabel lineThicknessHeader = new JLabel(headerText);
        add(lineThicknessHeader, BorderLayout.NORTH);
        add(lineThicknessPane, BorderLayout.WEST);
        add(lineThicknessSlider, BorderLayout.CENTER);
    }
}
