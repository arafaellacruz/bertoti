
public class Main {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();
        processor.setStrategy(new PixPayment());
        processor.process(150);
        processor.setStrategy(new CreditCardPayment());
        processor.process(300);
    }
}
