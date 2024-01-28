package Subject;

import Observer.Observer;

public interface Subject {
    public void registerObserver(Observer observer);
    public void unregisterObserver(Observer observer);
    public void notifyObservers(String observer);
}
