package View;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CustomMenuedRadioButton extends JRadioButton implements setCustomMenuInterface
{
    private class pressCustomRadioButtonMenuItem extends AbstractAction
    {
        CustomRadioButtonMenuItem item;
        public pressCustomRadioButtonMenuItem(CustomRadioButtonMenuItem item)
        {
            this.item = item;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(item != null) {
                if (item.isEnabled()) {
                    item.setSelected(true);
                }
            }
        }
    }

    public CustomMenuedRadioButton()
    {
        super();
    }

    public void setCustomRadioButtonMenuItem(CustomRadioButtonMenuItem item)
    {
        addActionListener(new CustomMenuedRadioButton.pressCustomRadioButtonMenuItem(item));
    }
}
