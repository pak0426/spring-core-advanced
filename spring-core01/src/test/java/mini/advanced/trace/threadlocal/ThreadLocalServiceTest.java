package mini.advanced.trace.threadlocal;

import lombok.extern.slf4j.Slf4j;
import mini.advanced.trace.threadlocal.code.ThreadLocalService;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreadLocalServiceTest {

    private ThreadLocalService threadLocalService = new ThreadLocalService();

    @Test
    void threadLocal() {
        {
            log.info("main start");
            Runnable userA = () -> {
                threadLocalService.logic("userA");
            };

            Runnable userB = () -> {
                threadLocalService.logic("userB");
            };

            Thread threadA = new Thread(userA);
            threadA.setName("thread-A");
            Thread threadB = new Thread(userB);
            threadB.setName("thread-B");

            threadA.start();
//        sleep(2000);
            sleep(100);
            threadB.start();

            sleep(3000);
            log.info("main exit");
        }
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
