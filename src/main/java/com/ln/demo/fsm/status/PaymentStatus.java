package com.ln.demo.fsm.status;

/**
 * @Author : Ln
 * @create 2024/9/18 11:38
 */

public enum PaymentStatus {
    INIT("INIT", "初始化"),
    ING("ING", "执行中"),
    SUCCESS("SUCCESS", "执行成功"),
    FAILED("FAI", "执行失败"),
    ;

    private String code;
    private String desc;
    private PaymentStatus(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
