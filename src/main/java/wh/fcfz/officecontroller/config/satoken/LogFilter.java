package wh.fcfz.officecontroller.config.satoken;

import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.filter.OncePerRequestFilter;

//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * OncePerRequestFilter是在一次外部请求中只过滤一次。
 * 对于服务器内部之间的forward等请求，不会再次执行过滤方法。
 */
@Configuration
public class LogFilter extends OncePerRequestFilter implements Ordered {
    private final static String MDC_TRACE_ID = "traceId";

    @Override
    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain) throws jakarta.servlet.ServletException, IOException {
        String traceIDStr = getMdcTraceId();
        MDC.put(MDC_TRACE_ID, traceIDStr);
        filterChain.doFilter(request, response);
        MDC.clear();
    }

    /**
     * 生产tranceId
     *
     * @return tranceID
     */
    private String getMdcTraceId() {
        long currentTime = System.nanoTime();
        return String.join("_", MDC_TRACE_ID, String.valueOf(currentTime));
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }


}