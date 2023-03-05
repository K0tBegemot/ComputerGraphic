package Control;

import Model.MainFrameModel;
import View.DrawingArea;
import View.PNGSaveChooser;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class pressExportImageButtonActionListener extends AbstractAction
{
    private JFileChooser chooser;
    private DrawingArea area;
    private JFrame frame;
    private FileFilter filter;

    public pressExportImageButtonActionListener(JFileChooser chooser, DrawingArea area, JFrame frame)
    {
        this.chooser = chooser;
        this.filter = chooser.getFileFilter();
        this.area = area;
        this.frame = frame;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        int retValue = chooser.showSaveDialog(frame);
        File file = chooser.getSelectedFile();
        if(filter.accept(file)) {
            try {
                ImageIO.write(area.getImage(), "png", file);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
