package mini.advanced.trace.strategy.code;

import lombok.extern.slf4j.Slf4j;
import mini.advanced.trace.strategy.code.strategy.ContextV2;
import mini.advanced.trace.strategy.code.strategy.Strategy;
import mini.advanced.trace.strategy.code.strategy.StrategyLogic1;
import mini.advanced.trace.strategy.code.strategy.StrategyLogic2;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {

    @Test
    void strategyV1() {
        ContextV2 context = new ContextV2();
        context.execute(new StrategyLogic1());
        context.execute(new StrategyLogic2());
    }

    @Test
    void strategyV2() {
        ContextV2 context = new ContextV2();
        context.execute(() -> log.info("비즈니스 로직1 실행"));
        context.execute(() -> log.info("비즈니스 로직2 실행"));
    }
}
