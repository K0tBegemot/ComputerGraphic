package Control;

import View.PaintFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class pressAboutActionListener extends AbstractAction
{
    private PaintFrame frame;
    private JDialog dialog;
    public pressAboutActionListener(PaintFrame frame)
    {
        this.frame = frame;
        dialog = new JDialog(frame, "About Program");
        dialog.setMinimumSize(new Dimension(750, 780));
        dialog.setLocation(760, 200);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        ImageIcon KotBegemotIcon = new ImageIcon("src/Resources/kot_begemot.jpg");
        panel.add(new JLabel(KotBegemotIcon), BorderLayout.NORTH);
        JTextArea text = new JTextArea("""
                
                ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                //This program was created by Bakelev Konstantin Valer'evich (aka KotBegemot)//
                //from FIT NSU group number 20207.(2023)                                                         //
                ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                
                Quick Guide
                The program interface consist of several panels: 
                1) Instrument panel(here you can choose current instrument, that will be used at edit panel 
                (click at any icon to choose it, click another one or current one to cancel drawing the current shape,
                to save changes on the Edit pane, click at Save button at corresponding Instrument options panel).
                    There is three buttons here: 
                    1) Dotted curve button(if you click at this button, then you can draw on Edit panel
                        To see line you have to mark two points on the panel with the mouse
                        There is two additional options in the corresponding options panel:
                        1) Closed curve(the first and the last dots will be connected by line)
                        2) Opened curve(the first and the last dots will not be connected by line))
                    2) Regular polygon button(if you click at this button, then you can draw on Edit panel
                        To see regular polygon you have to mark one point on the panel with the mouse
                        There is two type of regular polygons: 
                        1) Regular polygon
                        2) Star 
                        There is two additional options in the corresponding options panel:
                        1) Radius slider (to choose current radius of a shape)
                        2) Number of vertices slider (to choose current vertices number of a shape)
                        3) Angle rotation slider (to rotate your figure))
                    3) Span Fill button(to chose another color, use panel number 3) ))
                2) LineThicknessPanel (Change current line thickness value with the slider or in the field)
                3) Color panel (Choose one of the ready-made colors or choose your own with color palette)
                4) About Program panel (You are currently viewing hints using this button)
                5) Edit panel (Detailed information on use is indicated in paragraph 1))
                6) Instrument options panel(Detailed information on use is indicated in paragraph 1))
                """);
        text.setEditable(false);
        panel.add(text);
        JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setViewportView(panel);
        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        dialog.setVisible(true);
    }
}
