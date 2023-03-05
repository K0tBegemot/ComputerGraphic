package Control;

import Model.MainFrameModel;

public class LineThicknessListener implements NumberListener
{
    private MainFrameModel model;
    public LineThicknessListener(MainFrameModel model)
    {
        this.model = model;
    }

    @Override
    public void update(int number) {
        model.setCurrentLineThickness(number);
    }
}
