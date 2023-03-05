package View;

import Control.NumberListener;
import Model.MainFrameModel;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import java.awt.*;

public class NumberEditorPane extends JTextField
{
    private NumberListener listener;
    private int minValue;
    private int maxValue;
    public NumberEditorPane(int newMinValue, int newMaxValue, String toolTipText, NumberListener listener)
    {
        super();
        this.minValue = newMinValue;
        this.maxValue = newMaxValue;
        this.listener = listener;
        setToolTipText(toolTipText);
        setPreferredSize(new Dimension(200, 25));
        setDocument(createDefaultModel());
        setText(Integer.toString(newMinValue));
    }

    public void setPairSlider(JSlider slider)
    {
        ((NumberTextFieldDoc)getDocument()).setPairSlider(slider);
    }

    @Override
    protected Document createDefaultModel() {
        return new NumberTextFieldDoc(minValue, maxValue, listener);
    }
}