package com.ln.demo.fsm;

import cn.hutool.json.JSONUtil;
import com.alibaba.cola.statemachine.Action;
import com.alibaba.cola.statemachine.Condition;
import com.alibaba.cola.statemachine.StateMachine;
import com.alibaba.cola.statemachine.builder.StateMachineBuilder;
import com.alibaba.cola.statemachine.builder.StateMachineBuilderFactory;
import com.ln.demo.fsm.app.ExecuteContext;
import com.ln.demo.fsm.status.PaymentEvent;
import com.ln.demo.fsm.status.PaymentStatus;
import org.junit.jupiter.api.Test;



/**
 * @Author : Ln
 * @create 2024/9/18 15:31
 */
public class MyTest {
    @Test
    public void testExternalNormal() {
        StateMachineBuilder<PaymentStatus, PaymentEvent, ExecuteContext> builder = StateMachineBuilderFactory.create();
        builder.externalTransition()
                .from(PaymentStatus.INIT)
                .to(PaymentStatus.PAYING)
                .on(PaymentEvent.CREATE)
                .when(checkCondition())
                .perform(doAction());

        StateMachine<PaymentStatus, PaymentEvent, ExecuteContext> stateMachine = builder.build("ChoiceConditionMachine");
        PaymentStatus paymentStatus = stateMachine.fireEvent(PaymentStatus.INIT, PaymentEvent.CREATE, new ExecuteContext("1"));
        System.out.println(paymentStatus.name());
    }

    private Action<PaymentStatus, PaymentEvent, ExecuteContext> doAction() {
        return (from, to, event, ctx)->{
            //System.out.println(ctx.operator+" is operating "+ctx.entityId+" from:"+from+" to:"+to+" on:"+event);
            System.out.println("执行了转换"+ JSONUtil.parse(ctx));
        };
    }

    private static Condition<ExecuteContext> checkCondition() {
        System.out.println("checkCondition");
        return (ctx) -> true;
    }

}
