package View;

import Model.MainFrameModel;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class PNGSaveChooser extends JFileChooser
{
    private class Utils
    {
        public static String getExtension(File f) {
            String ext = null;
            String s = f.getName();
            int i = s.lastIndexOf('.');
            if (i > 0 &&  i < s.length() - 1) {
                ext = s.substring(i+1).toLowerCase();
            }
            return ext;
        }
    }
    private class PNGFilter extends FileFilter
    {
        @Override
        public boolean accept(File file) {
            if(file.isDirectory())
            {
                return true;
            }else {
                String extension = Utils.getExtension(file);
                if(extension != null && extension.equals("png"))
                {
                    return true;
                }
                return false;
            }
        }

        @Override
        public String getDescription() {
            return "PNG format images";
        }
    }
    public PNGSaveChooser()
    {
        setFileSelectionMode(JFileChooser.FILES_ONLY);
        removeChoosableFileFilter(getAcceptAllFileFilter());
        setFileFilter(new FileNameExtensionFilter("PNG Images (.png)", "png")); //new PNGFilter()
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
