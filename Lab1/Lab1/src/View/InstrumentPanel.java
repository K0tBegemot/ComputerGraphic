package View;

import Control.*;
import Model.MainFrameModel;
import Model.Shape.Dot;
import Model.Shape.DottedCurve;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class InstrumentPanel extends JPanel
{
    public JMenu instrumentMenu;
    InstrumentPanel(MainFrameModel model, PaintFrame mainFrame, CustomRadioButton truePolygon, CustomRadioButton star)
    {
        super();
        instrumentMenu = new JMenu("Instrument Panel");
        Border blackLineBorder = BorderFactory.createLineBorder(Color.BLACK);
        ButtonGroup instrumentGroup = new ButtonGroup();
        CustomRadioButton dottedCurve = new CustomRadioButton("src/Resources/dotted_curve.png");
        dottedCurve.setToolTipText("Dotted Curve");
        dottedCurve.addActionListener(new pressDottedCurveButtonActionListener(model));
        CustomRadioButton polygon = new CustomRadioButton("src/Resources/polygon.png");
        polygon.setToolTipText("Polygon");
        polygon.addActionListener(new pressRegularPolygonActionListener(model));
        CustomRadioButton fill = new CustomRadioButton("src/Resources/fill.png");
        fill.setToolTipText("Fill");
        fill.addActionListener(new pressFillButtonActionListener(model));
        CustomButton erase = new CustomButton("src/Resources/erase.png");
        erase.setToolTipText("Erase all");
        erase.addActionListener(new pressEraseButtonActionListener(model, erase, mainFrame.editorPanel));
        CustomButton loadImage = new CustomButton("src/Resources/import.png");
        loadImage.setToolTipText("Import Image");
        loadImage.addActionListener(new pressImportImageButtonActionListener(mainFrame.fileOpenChooser, mainFrame.editorPanel, mainFrame));
        CustomButton exportImage = new CustomButton("src/Resources/save_as.png");
        exportImage.setToolTipText("Export Image");
        exportImage.addActionListener(new pressExportImageButtonActionListener(mainFrame.pngSaveChooser, mainFrame.editorPanel, mainFrame));
        instrumentGroup.add(dottedCurve);
        instrumentGroup.add(polygon);
        instrumentGroup.add(fill);
        setBorder(blackLineBorder);
        setLayout(new GridLayout(1, 5));
        add(dottedCurve);
        add(polygon);
        add(fill);
        add(erase);
        add(loadImage);
        add(exportImage);

        ButtonGroup instrumentMenuGroup = new ButtonGroup();
        JRadioButtonMenuItem dotCurItem = new CustomRadioButtonMenuItem("DottedCurve", new ArrayList<>(Arrays.asList(dottedCurve)));
        JRadioButtonMenuItem regPolItem = new CustomRadioButtonMenuItem("Regular Polygon", new ArrayList<>(Arrays.asList(polygon, truePolygon)));
        JRadioButtonMenuItem starItem = new CustomRadioButtonMenuItem("Star", new ArrayList<>(Arrays.asList(polygon, star)));
        JRadioButtonMenuItem fillItem = new CustomRadioButtonMenuItem("Fill", new ArrayList<>(Arrays.asList(fill)));
        JMenuItem eraseItem = new CustomMenuItem("Erase", new ArrayList<>(Arrays.asList(erase)));
        JMenuItem importItem = new CustomMenuItem("Import from", new ArrayList<>(Arrays.asList(loadImage)));
        JMenuItem exportItem = new CustomMenuItem("Export to", new ArrayList<>(Arrays.asList(exportImage)));

        instrumentMenuGroup.add(dotCurItem);
        instrumentMenuGroup.add(regPolItem);
        instrumentMenuGroup.add(starItem);
        instrumentMenuGroup.add(fillItem);

        instrumentMenu.add(dotCurItem);
        instrumentMenu.add(regPolItem);
        instrumentMenu.add(starItem);
        instrumentMenu.add(fillItem);
        instrumentMenu.addSeparator();
        instrumentMenu.add(eraseItem);
        instrumentMenu.add(importItem);
        instrumentMenu.add(exportItem);
    }
}
