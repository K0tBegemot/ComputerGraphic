package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

public class CustomRadioButton extends CustomMenuedRadioButton
{
    CustomRadioButton()
    {
        super();
        setIcon(new ImageIcon("src/Resources/radiobutton_icon.png"));
        setSelectedIcon(new ImageIcon("src/Resources/radiobutton_selected_icon.png"));
        setRolloverIcon(new ImageIcon("src/Resources/radiobutton_rollover_icon.png"));
    }
    CustomRadioButton(String path)
    {
        super();
            ImageIcon customIcon = null;
            if(path != null)
            {
                customIcon = new ImageIcon(path);
            }
            ImageIcon main_tmp1 = new ImageIcon("src/Resources/radiobutton_icon.png");
            BufferedImage tmp1 = new BufferedImage(main_tmp1.getIconWidth(), main_tmp1.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
            tmp1.getGraphics().drawImage(main_tmp1.getImage(), 0, 0, null);
            if(customIcon != null) {
                tmp1.getGraphics().drawImage(customIcon.getImage(), 0, 0, null);
            }
            setIcon(new ImageIcon(tmp1));
            ImageIcon main_tmp2 = new ImageIcon("src/Resources/radiobutton_selected_icon.png");
            BufferedImage tmp2 = new BufferedImage(main_tmp2.getIconWidth(), main_tmp2.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
            tmp2.getGraphics().drawImage(main_tmp2.getImage(), 0, 0, null);
            if(customIcon != null) {
                tmp2.getGraphics().drawImage(customIcon.getImage(), 0, 0, null);
            }
            setSelectedIcon(new ImageIcon(tmp2));
            ImageIcon main_tmp3 = new ImageIcon("src/Resources/radiobutton_rollover_icon.png");
            BufferedImage tmp3 = new BufferedImage(main_tmp3.getIconWidth(), main_tmp3.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
            tmp3.getGraphics().drawImage(main_tmp3.getImage(), 0, 0, null);
            if(customIcon != null) {
                tmp3.getGraphics().drawImage(customIcon.getImage(), 0, 0, null);
            }
            setRolloverIcon(new ImageIcon(tmp3));
    }
}
