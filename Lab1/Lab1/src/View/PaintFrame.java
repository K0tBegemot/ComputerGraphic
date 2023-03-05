package View;

import Control.LineThicknessListener;
import Control.ModelActionListener;
import Control.pressColorButtonActionListener;
import Control.pressColorPickerButtonActionListener;
import Model.MainFrameModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaintFrame extends JFrame
{
    public static enum STATES{OBSERVATION, EDITING};

    private STATES currentState;
    private static final Level loggerErrorLevel = Level.SEVERE;
    private static final Level loggerDebugLevel = Level.FINE;
    private static final int minFrameLength = 640;
    private static final int minFrameHeight = 480;
    private static final int optimFrameLength = 1920;
    private static final int optimFrameHeight = 1080;
    private MainFrameModel model;
    private JPanel optionPanel;
    private InstrumentPanel drawingToolsPanel;
    private ParameterPanel lineThicknessPanel;
    private ColorPanel colorPanel;
    private AboutPanel aboutPanel;
    private JMenuBar mainMenu;
    public DrawingArea editorPanel;
    public JInternalFrame currentEditShapeWindow;
    public JInternalFrame dottedCurveEditWindow;
    public JInternalFrame spanFillEditWindow;
    public JInternalFrame regularPolygonEditWindow;
    public PNGSaveChooser pngSaveChooser;
    public FileOpenChooser fileOpenChooser;
    private Logger logger;

    public PaintFrame(MainFrameModel newModel)
    {
        super("Paint clone (HEH)");
        model = newModel;
        model.addModelListener(new ModelActionListener(this, model));
        currentState = STATES.OBSERVATION;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        logger = Logger.getLogger("PaintFrameLogger");
        logger.setLevel(loggerDebugLevel);//////////////////////////////////////////////////////////////Log Debug
        try {
            setIconImage(ImageIO.read(new File("src/Resources/icon.png")));
        }catch(IOException e)
        {
            logger.log(loggerErrorLevel, "There is no image icon!");
        }
        setMinimumSize(new Dimension(minFrameLength, minFrameHeight));
        setLocationRelativeTo(null);
        setSize(new Dimension(optimFrameLength, optimFrameHeight));
        optionPanel = new JPanel();
        editorPanel  = new DrawingArea(model);
        colorPanel = new ColorPanel(this, model);
        aboutPanel = new AboutPanel(model, this);
        pngSaveChooser = new PNGSaveChooser();
        fileOpenChooser = new FileOpenChooser();
        dottedCurveEditWindow = new DottedCurveEditWindow(model);
        regularPolygonEditWindow = new RegularPolygonEditWindow(model);
        drawingToolsPanel = new InstrumentPanel(model, this, ((RegularPolygonEditWindow)regularPolygonEditWindow).polygonButton, ((RegularPolygonEditWindow)regularPolygonEditWindow).starButton);
        lineThicknessPanel = new ParameterPanel(new LineThicknessListener(model), "Line Thickness",
                "Press ENTER to set current value of this field. Max value - 26px, Min value - 1px, Step - 1px", 1, 26, 1);
        currentEditShapeWindow = null;
        spanFillEditWindow = new SpanFillEditWindow(model);
        mainMenu = new JMenuBar();

        initialisePaintFrame();
        setVisible(true);
    }

    private void initialisePaintFrame()
    {
        Border blackLineBorder = BorderFactory.createLineBorder(Color.BLACK);
        optionPanel.setBorder(blackLineBorder);
        optionPanel.setPreferredSize(new Dimension(this.getWidth(), this.getHeight() / 10));
        optionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        optionPanel.setBackground(new Color(37, 198, 247));
        optionPanel.add(drawingToolsPanel);
        optionPanel.add(lineThicknessPanel);
        optionPanel.add(colorPanel);
        optionPanel.add(aboutPanel);
        this.add(optionPanel, BorderLayout.NORTH);
        JScrollPane paintAreaScroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        paintAreaScroll.setViewportView(editorPanel);
        this.add(paintAreaScroll, BorderLayout.CENTER);
        JPanel internalFramePanel = new JPanel();
        internalFramePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        internalFramePanel.add(dottedCurveEditWindow);
        internalFramePanel.add(spanFillEditWindow);
        internalFramePanel.add(regularPolygonEditWindow);
        add(internalFramePanel, BorderLayout.SOUTH);

        mainMenu.add(drawingToolsPanel.instrumentMenu);
        mainMenu.add(colorPanel.colorMenu);
        mainMenu.add(aboutPanel.aboutItem);
        setJMenuBar(mainMenu);
    }

    public void setCurrentState(STATES currentState) {
        this.currentState = currentState;
    }

    public STATES getCurrentState() {
        return currentState;
    }
}
