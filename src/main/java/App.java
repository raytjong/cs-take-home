public class App {
    public static void main(String[] args) {
        OrderCalculator calculator = new OrderCalculatorImpl();
        calculator.calculateOrderTotal(null);
    }
}
