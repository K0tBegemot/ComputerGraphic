package Control;

import Model.MainFrameModel;
import Model.Shape.DottedCurve;
import Model.Shape.GShape;
import Model.Shape.RegularPolygon;
import Model.Shape.SpanFill;
import View.PaintFrame;

import javax.swing.*;

public class ModelActionListener implements ModelListener
{
    PaintFrame mainFrame;
    MainFrameModel model;

    public ModelActionListener(PaintFrame frame, MainFrameModel model)
    {
        mainFrame = frame;
        this.model = model;
    }

    @Override
    public void update(MainFrameModel.STATES state) {
        //System.err.println("-> " + mainFrame.getCurrentState().toString() + " " + state.toString());
        switch(mainFrame.getCurrentState())
        {
            case EDITING : {
                if(state == MainFrameModel.STATES.EDITING_NEW_SHAPE)
                {
                    mainFrame.repaint();
                } else if (state == MainFrameModel.STATES.CHOOSING_NEW_SHAPE) {
                    mainFrame.repaint();
                    mainFrame.setCurrentState(PaintFrame.STATES.OBSERVATION);
                } else if (state == MainFrameModel.STATES.WAITING) {
                    if(mainFrame.currentEditShapeWindow != null)
                    {
                        mainFrame.currentEditShapeWindow.setVisible(false);
                        mainFrame.currentEditShapeWindow = null;
                    }
                    mainFrame.repaint();
                    mainFrame.setCurrentState(PaintFrame.STATES.OBSERVATION);
                }
                //here mustn't be break!!!
            }
            case OBSERVATION : {
                if(state == MainFrameModel.STATES.EDITING_NEW_SHAPE || state == MainFrameModel.STATES.CHOOSING_NEW_SHAPE)
                {
                    mainFrame.setCurrentState(PaintFrame.STATES.EDITING);
                    if(model.getTmpShape() instanceof DottedCurve)
                    {
                        if((mainFrame.currentEditShapeWindow == null) || !(mainFrame.currentEditShapeWindow.equals(mainFrame.dottedCurveEditWindow)))
                        {
                            if(mainFrame.currentEditShapeWindow != null)
                            {
                                mainFrame.currentEditShapeWindow.setVisible(false);
                            }
                            mainFrame.dottedCurveEditWindow.setVisible(true);
                            mainFrame.currentEditShapeWindow = mainFrame.dottedCurveEditWindow;
                        }
                    } else if (model.getTmpShape() instanceof SpanFill) {
                        if((mainFrame.currentEditShapeWindow == null) || !(mainFrame.currentEditShapeWindow.equals(mainFrame.spanFillEditWindow)))
                        {
                            if(mainFrame.currentEditShapeWindow != null)
                            {
                                mainFrame.currentEditShapeWindow.setVisible(false);
                            }
                            mainFrame.spanFillEditWindow.setVisible(true);
                            mainFrame.currentEditShapeWindow = mainFrame.spanFillEditWindow;
                        }
                    } else if (model.getTmpShape() instanceof RegularPolygon) {
                        if((mainFrame.currentEditShapeWindow == null) || !(mainFrame.currentEditShapeWindow.equals(mainFrame.regularPolygonEditWindow)))
                        {
                            if(mainFrame.currentEditShapeWindow != null)
                            {
                                mainFrame.currentEditShapeWindow.setVisible(false);
                            }
                            mainFrame.regularPolygonEditWindow.setVisible(true);
                            mainFrame.currentEditShapeWindow = mainFrame.regularPolygonEditWindow;
                        }
                    }
                    mainFrame.repaint();
                }
                break;
            }
        };
    }
}
