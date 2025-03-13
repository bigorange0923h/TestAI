package org.bigorange.testai.common;

import lombok.Getter;

@Getter
public class ExecutionResult {
    private boolean success; // 执行是否成功
    private String output;   // 执行输出（日志或错误信息）
    private long duration;   // 执行耗时（毫秒）

    // 构造函数
    public ExecutionResult(boolean success, String output, long duration) {
        this.success = success;
        this.output = output;
        this.duration = duration;
    }


    public static ExecutionResult success(String output, long duration) {
        return new ExecutionResult(true, output, duration);
    }

    // 静态工厂方法（失败）
    public static ExecutionResult fail(String errorMessage, long duration) {
        return new ExecutionResult(false, errorMessage, duration);
    }


}