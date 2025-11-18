public class PixPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Pagando R$" + amount + " com Pix ⚡");
    }
}
