package com.ln.demo.fsm.context;

import com.alibaba.cola.statemachine.Action;
import com.alibaba.cola.statemachine.Condition;
import com.alibaba.cola.statemachine.StateMachine;
import com.alibaba.cola.statemachine.builder.StateMachineBuilder;
import com.alibaba.cola.statemachine.builder.StateMachineBuilderFactory;
import com.ln.demo.fsm.app.ExecuteContext;
import com.ln.demo.fsm.status.PaymentEvent;
import com.ln.demo.fsm.status.PaymentStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author : Ln
 * @create 2024/9/18 16:24
 * 初始化状态机
 */
@Slf4j
@Configuration
public class InitStatus {

    @Bean("orderOperaMachine")
    public StateMachine initialize() {
        StateMachineBuilder<PaymentStatus, PaymentEvent, ExecuteContext> builder = StateMachineBuilderFactory.create();
        /**
         * 外部状态流转
         */
        builder.externalTransition()
                .from(PaymentStatus.INIT)
                .to(PaymentStatus.ING)
                .on(PaymentEvent.CREATE)
                .when(checkOrder())
                .perform(createOrderAction());
        /**
         * 内部状态流转
         */
        builder.internalTransition()
                .within(PaymentStatus.ING)
                .on(PaymentEvent.GPT)
                .when(checkOrder())
                .perform(gptAction());

        builder.internalTransition()
                .within(PaymentStatus.ING)
                .on(PaymentEvent.AUDIT)
                .when(checkOrder())
                .perform(auditAction());


        return builder.build("order_opera");
    }

    private Action<PaymentStatus, PaymentEvent, ExecuteContext> auditAction() {
        return (from, to, event, ctx)-> log.info("audit执行,传进来的参数为{}",ctx.getOrderId());
    }

    private Action<PaymentStatus, PaymentEvent, ExecuteContext> gptAction() {
        try {
            return (from, to, event, ctx)-> {
                // select * from xxx where id = 1
                // update xxx set status where version = 1
                log.info("gpt执行,传进来的参数为{}",ctx.getOrderId());
            };
        } catch (Exception e) {
            // 外部抓取异常返回失败
            throw new RuntimeException(e);
        }
    }

    private Action<PaymentStatus, PaymentEvent, ExecuteContext> createOrderAction() {
        return (from, to, event, ctx)-> {
            // select * from xxx where id = 1
            // update xxx set status where version = 1
            log.info("开始创建,传进来的参数为{}",ctx.getOrderId());
        };
    }

    private Condition<ExecuteContext> checkOrder() {
        return (ctx) -> {
            // 校验订单状态
            String orderId = ctx.getOrderId();
            log.info("获取到的orderid{}",orderId);
            return true;
        };
    }

}
