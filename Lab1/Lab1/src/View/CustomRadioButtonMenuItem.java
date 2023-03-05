package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class CustomRadioButtonMenuItem extends JRadioButtonMenuItem
{
    private class pressMenuActionListener extends AbstractAction
    {
        private List<CustomMenuedRadioButton> buttonList;
        pressMenuActionListener(List<CustomMenuedRadioButton> buttonList)
        {
            this.buttonList = buttonList;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            for(CustomMenuedRadioButton i : buttonList)
            {
                i.doClick();
            }
        }
    }
    public CustomRadioButtonMenuItem(String name, List<CustomMenuedRadioButton> buttonList)
    {
        super(name);
        buttonList.get(buttonList.size() - 1).setCustomRadioButtonMenuItem(this);
        addActionListener(new pressMenuActionListener(buttonList));
    }

}
