package Control;

public interface ModelObservable
{
    void addModelListener(ModelListener listener);
    void deleteModelListener(int index);
    void notifyAllListeners();
}
