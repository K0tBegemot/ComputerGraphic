package Control;

import Model.MainFrameModel;

public class NumberOfVerticesListener implements NumberListener{
    private MainFrameModel model;

    public NumberOfVerticesListener(MainFrameModel model)
    {
        this.model = model;
    }
    @Override
    public void update(int number) {
        model.setCurrentNumberOfVertices(number);
    }
}
