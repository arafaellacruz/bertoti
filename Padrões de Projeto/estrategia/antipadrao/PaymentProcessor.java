public class PaymentProcessor {
    public void process(double amount, String method) {
        if (method.equals("pix")) {
            System.out.println("Pagando R$" + amount + " com Pix ⚡");
        } else if (method.equals("cartao")) {
            System.out.println("Pagando R$" + amount + " com cartão 💳");
        } else {
            System.out.println("Método inválido!");
        }
    }

    public static void main(String[] args) {
        PaymentProcessorBad processor = new PaymentProcessorBad();
        processor.process(100, "pix");
        processor.process(200, "cartao");
    }
}
