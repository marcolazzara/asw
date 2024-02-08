package asw.ordermanager.orderservice.domain;

public class OrderCreatedEvent {
    private final Order order;

    public OrderCreatedEvent(Order order) {
        this.order = order;
    }

    // Getter per accedere ai dettagli dell'ordine
    public Order getOrder() {
        return order;
    }
}
