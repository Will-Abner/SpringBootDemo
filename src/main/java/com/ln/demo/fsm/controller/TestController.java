package com.ln.demo.fsm.controller;

import com.alibaba.cola.statemachine.StateMachine;
import com.ln.demo.fsm.app.ExecuteContext;
import com.ln.demo.fsm.status.PaymentEvent;
import com.ln.demo.fsm.status.PaymentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author : Ln
 * @create 2024/9/18 16:45
 * 测试接口
 */
@Controller
public class TestController {


    @Autowired
    private StateMachine<PaymentStatus, PaymentEvent,ExecuteContext> stateMachine;

    @GetMapping("/api/test")
    public void corpusUsedCount() {
        String machineId = stateMachine.getMachineId();
        System.out.println(machineId);
        ExecuteContext context = new ExecuteContext("1");
        PaymentStatus paymentStatus = stateMachine.fireEvent(PaymentStatus.INIT, PaymentEvent.CREATE, context);
        System.out.println(paymentStatus.toString());

    }
}
