package com.ln.demo.fsm.app;

import lombok.Data;

/**
 * @Author : Ln
 * @create 2024/9/18 15:39
 * 传递数据
 */
@Data
public class ExecuteContext {
    private String orderId;

    public ExecuteContext(String orderId) {
        this.orderId = orderId;
    }
}
