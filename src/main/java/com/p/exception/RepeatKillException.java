package com.p.exception;

/**
 * 重复秒杀异常,是一个运行期间异常,不需要手动try catch
 * mysql之支持运行期间异常的回滚操作
 */
public class RepeatKillException extends SeckillException {

    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
