package mini.advanced.app.v5;

import lombok.RequiredArgsConstructor;
import mini.advanced.trace.callback.TraceCallback;
import mini.advanced.trace.callback.TraceTemplate;
import mini.advanced.trace.logtrace.LogTrace;
import mini.advanced.trace.template.AbstractTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceV5 {
    private final OrderRepositoryV5 orderRepository;
    private final TraceTemplate template;

    public OrderServiceV5(OrderRepositoryV5 orderRepository, LogTrace logTrace) {
        this.orderRepository = orderRepository;
        this.template = new TraceTemplate(logTrace);
    }

    public void orderItem(String itemId) {
        template.execute("OrderServiceV5.orderItem()", () -> {
            orderRepository.save(itemId);
            return null;
        });
    }
}
