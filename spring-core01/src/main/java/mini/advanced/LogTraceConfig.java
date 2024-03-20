package mini.advanced;

import mini.advanced.trace.logtrace.FiledLogTrace;
import mini.advanced.trace.logtrace.LogTrace;
import mini.advanced.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace() {
//        return new FiledLogTrace();
        return new ThreadLocalLogTrace();
    }
}
