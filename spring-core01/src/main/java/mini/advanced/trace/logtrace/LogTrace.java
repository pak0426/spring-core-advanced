package mini.advanced.trace.logtrace;

import mini.advanced.trace.TraceStatus;

public interface LogTrace {

    TraceStatus begin(String message);

    void end(TraceStatus traceStatus);

    void exception(TraceStatus status, Exception e);
}
