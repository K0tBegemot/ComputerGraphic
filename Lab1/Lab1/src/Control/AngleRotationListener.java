package Control;

import Model.MainFrameModel;

public class AngleRotationListener implements NumberListener
{
    private MainFrameModel model;
    public AngleRotationListener(MainFrameModel model)
    {
        this.model = model;
    }

    @Override
    public void update(int number) {
        model.setRotationAngle(number);
    }
}
