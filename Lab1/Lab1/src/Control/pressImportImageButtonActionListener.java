package Control;

import View.DrawingArea;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class pressImportImageButtonActionListener extends AbstractAction
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
    private JFileChooser chooser;
    private DrawingArea area;
    private JFrame frame;
    private FileFilter filter;
    public pressImportImageButtonActionListener(JFileChooser chooser, DrawingArea area, JFrame frame)
    {
        this.chooser = chooser;
        this.filter = chooser.getFileFilter();
        this.area = area;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        int retValue = chooser.showOpenDialog(frame);
        File file = chooser.getSelectedFile();
        if(filter.accept(file)) {
            try {

                area.setImage(ImageIO.read(file));
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
