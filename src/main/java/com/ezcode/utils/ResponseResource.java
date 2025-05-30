package com.ezcode.utils;

import java.io.Serializable;
import org.slf4j.MDC;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * Class Name : ResponseResource.java
 * Description : Custom Rest Response class
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseResource<T> implements Serializable {

    private static final long serialVersionUID = -6616071132691675957L;

    public ResponseResource() {
        this.message = ResponseResource.R_MSG_EMPTY;
        this.responseCode = ResponseResource.R_CODE_OK;
        this.status = Status.SUCCESS;
        this.ticket = MDC.get(CommonConstants.MDC_TOKEN_KEY) != null ? (String) MDC.get(CommonConstants.MDC_TOKEN_KEY)
                : CommonConstants.EMPTY;
        this.setData(data);
    }

    public static final String R_MSG_EMPTY = "";
    public static final Integer R_CODE_OK = 200;
    public static final Integer R_CODE_CONFLICT = 409;// conflict with the current state of the target resource.
    public static final Integer R_CODE_ERROR = 500;
    public static final Integer R_CODE_NOT_FOUND = 404;
    public static final Integer R_CODE_BAD_REQUEST = 400;
    public static final Integer R_CODE_INTERNAL_SERVER_ERROR = 500;
    public static final Integer R_CODE_UNAUTH_ERROR = 401;
    public static final Integer R_CODE_FORBIDDEN_ERROR = 403;
    public static final Integer R_CODE_VALIDATION_ERROR = 422;
    public static final Integer R_CODE_INTERNAL_ERROR = 500;
    public static final String RES_SUCCESS = "OK";
    public static final String RES_FAILURE = "OPERATION FAILED";
    public static final String RES_NO_DATA = "NO DATA";
    public static final String RES_NO_TRUNKS_DATA = "NO TRUNKS DATA";
    private final Integer responseCode;
    private final String message;
    private final Status status;
    private final String ticket;
    private T data;

    /**
     * Creates a new instance of ResponseResource
     *
     * @param code
     * @param message
     * @param status
     */
    public ResponseResource(final Integer code, final String message, final Status status) {
        this.message = message == null ? ResponseResource.R_MSG_EMPTY : message;
        this.responseCode = code == null ? ResponseResource.R_CODE_OK : code;
        this.status = status == null ? Status.SUCCESS : status;
        this.ticket = MDC.get(CommonConstants.MDC_TOKEN_KEY) != null ? (String) MDC.get(CommonConstants.MDC_TOKEN_KEY)
                : CommonConstants.EMPTY;
    }

    public ResponseResource(final Integer code, final String message, T data, final Status status) {
        this.message = message == null ? ResponseResource.R_MSG_EMPTY : message;
        this.responseCode = code == null ? ResponseResource.R_CODE_OK : code;
        this.status = status == null ? Status.SUCCESS : status;
        this.ticket = MDC.get(CommonConstants.MDC_TOKEN_KEY) != null ? (String) MDC.get(CommonConstants.MDC_TOKEN_KEY)
                : CommonConstants.EMPTY;
        this.setData(data);
    }

    public ResponseResource(T data) {
        this.message = ResponseResource.R_MSG_EMPTY;
        this.responseCode = ResponseResource.R_CODE_OK;
        this.ticket = MDC.get(CommonConstants.MDC_TOKEN_KEY) != null ? (String) MDC.get(CommonConstants.MDC_TOKEN_KEY)
                : CommonConstants.EMPTY;
        this.data = data;
        this.status = Status.SUCCESS;
    }

    public String getMessage() {

        return this.message;
    }

    public Integer getResponseCode() {

        return this.responseCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Status getStatus() {
        return status;
    }

    public String getTicket() {
        return ticket;
    }

}