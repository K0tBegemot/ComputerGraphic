package View;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileOpenChooser extends JFileChooser
{
    public FileOpenChooser()
    {
        setFileSelectionMode(JFileChooser.FILES_ONLY);
        removeChoosableFileFilter(getAcceptAllFileFilter());
        setFileFilter(new FileNameExtensionFilter("Images (.png, .jpeg, .bmp, .gif, .jpg)", "png", "jpeg", "bmp", "gif", "jpg")); //new PNGFilter()
        setMultiSelectionEnabled(false);
    }
    @Override
    public void approveSelection() {
        if(getFileFilter().accept(getSelectedFile()))
        {
            super.approveSelection();
        }else {
            JOptionPane.showMessageDialog(this,
                    "Incorrect file name. Add extension : " + (getFileFilter().getDescription()), "File name error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
