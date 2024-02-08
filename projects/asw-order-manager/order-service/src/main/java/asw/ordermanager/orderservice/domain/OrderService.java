package asw.ordermanager.orderservice.domain;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public Order createOrder(String customer, String address, List<OrderItem> orderItems, double total) {
        Order order = new Order(customer, address, orderItems, total);
        order = orderRepository.save(order);

        OrderCreatedEvent event = new OrderCreatedEvent(order);
        kafkaTemplate.send("order-created-topic", order.toString());
        return order;
    }

    public Order getOrder(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        return order;
    }

    public Collection<Order> getOrders() {
        Collection<Order> orders = orderRepository.findAll();
        return orders;
    }

    public Collection<Order> getOrdersByCustomer(String customer) {
        Collection<Order> orders = orderRepository.findByCustomer(customer);
        return orders;
    }

    public Collection<Order> getOrdersByProduct(String product) {
        Collection<Order> orders = orderRepository.findByOrderItems_Product(product);
        return orders;
    }

}
