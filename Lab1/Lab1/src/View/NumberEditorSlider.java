package View;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class NumberEditorSlider extends JSlider
{
    private NumberEditorPane pairPane;
    NumberEditorSlider(int sliderMin, int sliderMax, int sliderStep, NumberEditorPane newPairPane)
    {
        super(sliderMin, sliderMax, sliderMin);
        setMinimum(sliderMin);
        setMaximum(sliderMax);
        pairPane = newPairPane;
        setPreferredSize(new Dimension(200, 108));
        int majorTick = -1;
        int minorTick = -1;
        if((sliderMax - sliderMin + 1) / 3 > 0)
        {
            majorTick = (sliderMax - sliderMin + 1) / 3;
        }else {
            majorTick = 5;
        }
        if(majorTick / 4 > 0)
        {
            minorTick = majorTick / 4;
        }else {
            minorTick = 1;
        }
        setMajorTickSpacing(majorTick);
        setMinorTickSpacing(minorTick);
        setPaintTicks(true);
        setPaintLabels(true);
        addChangeListener(new NumberSliderChangeListener());
    }

    public class NumberSliderChangeListener implements ChangeListener
    {
        @Override
        public void stateChanged(ChangeEvent changeEvent) {
            if(!pairPane.getText().equals(Integer.toString(((JSlider)changeEvent.getSource()).getValue()))) {
                pairPane.setText(Integer.toString(((JSlider) changeEvent.getSource()).getValue()));
            }
        }
    }
}
