import Model.MainFrameModel;
import View.PaintFrame;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() ->{
            MainFrameModel model = new MainFrameModel();
            JFrame mainFrame = new PaintFrame(model);
        });
    }
}