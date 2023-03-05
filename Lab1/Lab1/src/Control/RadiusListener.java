package Control;

import Model.MainFrameModel;

public class RadiusListener implements NumberListener
{
    private MainFrameModel model;
    public RadiusListener(MainFrameModel model)
    {
        this.model = model;
    }

    @Override
    public void update(int number) {
        model.setCurrentRadius(number);
    }
}
