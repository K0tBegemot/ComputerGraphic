package View;

import Control.NumberListener;
import Model.MainFrameModel;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class NumberTextFieldDoc extends PlainDocument
{
    private NumberListener listener;
    private int minValue;
    private int maxValue;
    private JSlider pairSlider;
    public NumberTextFieldDoc(int newMinValue, int newMaxValue, NumberListener listener)
    {
        super();
        this.minValue = newMinValue;
        this.maxValue = newMaxValue;
        this.listener = listener;
    }

    public void setPairSlider(JSlider pairSlider) {
        this.pairSlider = pairSlider;
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        char[] source = str.toCharArray();
        StringBuilder newString = new StringBuilder();
        newString.append(getText(0, getLength()));
        String oldText = getText(0, getLength());
        StringBuilder goodString = new StringBuilder();
        for(int i = 0; i < str.length(); i++)
        {
            if(Character.isDigit(source[i]))
            {
                newString.append(source[i]);
                int newNumber = Integer.parseInt(newString.toString());
                if (newNumber >= minValue && newNumber <= maxValue) {
                    goodString.append(source[i]);
                } else {
                    if (newNumber < minValue) {
                        goodString.append(source[i]);
                        continue;
                    } else {
                        break;
                    }
                }
            }
        }
        if(!goodString.isEmpty()) {
            super.insertString(offs, goodString.toString(), a);
            if (pairSlider != null) {
                pairSlider.setValue(Integer.parseInt(oldText + goodString));
            }
            if (listener != null) {
                listener.update(Integer.parseInt(oldText + goodString));
            }
        }
    }

    @Override
    public void remove(int offs, int len) throws BadLocationException {
        super.remove(offs, len);
    }
}