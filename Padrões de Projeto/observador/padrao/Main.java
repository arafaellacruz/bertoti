public class Main {
    public static void main(String[] args) {
        Store store = new Store();
        store.addSubscriber(new Customer("Rafa"));
        store.addSubscriber(new Customer("Lucas"));
        store.notifySubscribers("Promoção: 50% OFF em fones!");
    }
}
