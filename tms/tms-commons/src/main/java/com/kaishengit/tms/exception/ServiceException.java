package com.kaishengit.tms.exception;


/*
 *   业务异常
 * @date 2018/4/12
 * @param
 * @return
 */
public class ServiceException extends RuntimeException  {

    public ServiceException(){}

    public ServiceException(Throwable th) {
        super(th);
    }

    public ServiceException(String message,Throwable th) {
        super(message,th);
    }

    public ServiceException(String message) {
        super(message);
    }
}
