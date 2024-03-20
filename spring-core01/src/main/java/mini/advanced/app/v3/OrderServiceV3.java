package mini.advanced.app.v3;

import lombok.RequiredArgsConstructor;
import mini.advanced.trace.TraceId;
import mini.advanced.trace.TraceStatus;
import mini.advanced.trace.hellotrace.HelloTraceV2;
import mini.advanced.trace.logtrace.LogTrace;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {
    private final OrderRepositoryV3 orderRepository;
    private  final LogTrace trace;

    public void orderItem(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderServiceV3.orderItem()");
            orderRepository.save(itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
