import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Observer> subscribers = new ArrayList<>();

    public void addSubscriber(Observer obs) {
        subscribers.add(obs);
    }

    public void notifySubscribers(String promo) {
        for (Observer obs : subscribers) {
            obs.update(promo);
        }
    }
}
