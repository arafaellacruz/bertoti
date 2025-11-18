package observador.antipadrao;

public class StoreBad {
    private Customer c1, c2;

    public StoreBad(Customer c1, Customer c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    public void notifyPromo(String promo) {
        c1.update(promo);
        c2.update(promo);
    }

    public static void main(String[] args) {
        Customer rafa = new Customer("Rafa");
        Customer lucas = new Customer("Lucas");
        StoreBad store = new StoreBad(rafa, lucas);
        store.notifyPromo("Promoção imperdível!");
    }
}
