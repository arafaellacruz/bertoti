public class CreditCardPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Pagando R$" + amount + " com cartão de crédito 💳");
    }
}
