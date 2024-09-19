package com.ln.demo.fsm.status;

/**
 * @Author : Ln
 * @create 2024/9/18 11:40
 */
public enum PaymentEvent {
    CREATE("CREATE", "支付创建"),
    GPT("GPT", "GPT执行中"),
    AUDIT("AUDIT", "AUDIT执行中"),
    SUBTITLE("SUBTITLE", "SUBTITLE执行中"),
    MERGE("MERGE", "MERGE执行中"),
    SUCCESS("SUCCESS", "执行完成"),
    PAY_FAIL("FAIL", "执行失败");

    /**
     * 事件
     */
    private String event;
    /**
     * 事件描述
     */
    private String description;

    PaymentEvent(String event, String description) {
        this.event = event;
        this.description = description;
    }
}
