package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class CustomMenuItem extends JMenuItem
{
    private class pressMenuActionListener extends AbstractAction
    {
        private List<CustomButton> buttonList;
        pressMenuActionListener(List<CustomButton> buttonList)
        {
            this.buttonList = buttonList;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            for(CustomButton i : buttonList)
            {
                i.doClick();
            }
        }
    }
    public CustomMenuItem(String name, List<CustomButton> buttonList)
    {
        super(name);
        buttonList.get(buttonList.size() - 1).setCustomMenuItem(this);
        addActionListener(new CustomMenuItem.pressMenuActionListener(buttonList));
    }
}
