package mini.advanced.app.v4;

import lombok.RequiredArgsConstructor;
import mini.advanced.trace.TraceStatus;
import mini.advanced.trace.logtrace.LogTrace;
import mini.advanced.trace.template.AbstractTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {
    private final OrderRepositoryV4 orderRepository;
    private  final LogTrace trace;

    public void orderItem(String itemId) {
        AbstractTemplate<Void> template = new AbstractTemplate<>(trace) {
            @Override
            protected Void call() {
                orderRepository.save(itemId);
                return null;
            }
        };
        template.execute("OrderServiceV4.orderItem()");
    }
}
