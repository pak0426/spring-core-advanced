package spring.aop.pointcut;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import spring.aop.member.MemberServiceImpl;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ArgsTest {
    Method helloMethod;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
    }

    private AspectJExpressionPointcut pointcut(String expression) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(expression);
        return pointcut;
    }

    @Test
    void args() {
        // hello(String)과 매칭
        Assertions.assertThat(pointcut("a  rgs(String)").matches(helloMethod, MemberServiceImpl.class)).isTrue();
        Assertions.assertThat(pointcut("args(Object)").matches(helloMethod, MemberServiceImpl.class)).isTrue();
        Assertions.assertThat(pointcut("args()").matches(helloMethod, MemberServiceImpl.class)).isFalse();
        Assertions.assertThat(pointcut("args(..)").matches(helloMethod, MemberServiceImpl.class)).isTrue();
        Assertions.assertThat(pointcut("args(*)").matches(helloMethod, MemberServiceImpl.class)).isTrue();
        Assertions.assertThat(pointcut("args(String, ..)").matches(helloMethod, MemberServiceImpl.class)).isTrue();
        Assertions.assertThat(pointcut("args(String, *)").matches(helloMethod, MemberServiceImpl.class)).isFalse();
    }

    /**
     * execution(* *(java.io.Serializable)): 메서드의 시그니처로 판단 (정적)
     * args(java.io.Serializable): 런타임에 전달된 인수로 판단 (동적)
     */
    @Test
    void argsVsExecution() {
        //Args
        assertThat(pointcut("args(String)").matches(helloMethod, MemberServiceImpl.class)).isTrue();
        assertThat(pointcut("args(java.io.Serializable)").matches(helloMethod, MemberServiceImpl.class)).isTrue();
        assertThat(pointcut("args(Object)").matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

}
