package com.graph.contract.common;

public enum ResponseStatusConstant {
    // 1xx Informational    1xx信息类的
    //继续
    CONTINUE(100, "Continue"),

    //交换协议
    SWITCHING_PROTOCOLS(101, "Switching Protocols"),

    //正在处理中
    PROCESSING(102, "Processing"),

    CHECKPOINT(103, "Checkpoint"),

    // 2xx Success 成功
    OK(200, "OK"),
    //创建
    CREATED(201, "Created"),
    //接受
    ACCEPTED(202, "Accepted"),

    NON_AUTHORITATIVE_INFORMATION(203, "Non-Authoritative Information"),

    NO_CONTENT(204, "No Content"),

    RESET_CONTENT(205, "Reset Content"),

    PARTIAL_CONTENT(206, "Partial Content"),

    MULTI_STATUS(207, "Multi-Status"),

    ALREADY_REPORTED(208, "Already Reported"),

    IM_USED(226, "IM Used"),

    // 3xx Redirection 重定向
    MULTIPLE_CHOICES(300, "Multiple Choices"),

    //永久移除掉了
    MOVED_PERMANENTLY(301, "Moved Permanently"),

    FOUND(302, "Found"),

    //暂时移除掉了 已经废弃了
    @Deprecated
    MOVED_TEMPORARILY(302, "Moved Temporarily"),

    SEE_OTHER(303, "See Other"),

    NOT_MODIFIED(304, "Not Modified"),

    //使用代理 已经废除掉了
    @Deprecated
    USE_PROXY(305, "Use Proxy"),

    //暂时的重定向了
    TEMPORARY_REDIRECT(307, "Temporary Redirect"),

    //永久的重定向了
    PERMANENT_REDIRECT(308, "Permanent Redirect"),

    // --- 4xx Client Error --- 4xx 客户端错误
    BAD_REQUEST(400, "Bad Request"),

    //没有认证权限
    UNAUTHORIZED(401, "Unauthorized"),

    PAYMENT_REQUIRED(402, "Payment Required"),

    //禁止
    FORBIDDEN(403, "Forbidden"),

    NOT_FOUND(404, "Not Found"),

    //方法不允许
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),

    NOT_ACCEPTABLE(406, "Not Acceptable"),

    PROXY_AUTHENTICATION_REQUIRED(407, "Proxy Authentication Required"),

    //请求超时
    REQUEST_TIMEOUT(408, "Request Timeout"),

    //冲突
    CONFLICT(409, "Conflict"),

    GONE(410, "Gone"),

    LENGTH_REQUIRED(411, "Length Required"),

    PRECONDITION_FAILED(412, "Precondition Failed"),

    PAYLOAD_TOO_LARGE(413, "Payload Too Large"),

    @Deprecated
    REQUEST_ENTITY_TOO_LARGE(413, "Request Entity Too Large"),

    URI_TOO_LONG(414, "URI Too Long"),

    @Deprecated
    REQUEST_URI_TOO_LONG(414, "Request-URI Too Long"),

    UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type"),

    REQUESTED_RANGE_NOT_SATISFIABLE(416, "Requested range not satisfiable"),

    EXPECTATION_FAILED(417, "Expectation Failed"),

    I_AM_A_TEAPOT(418, "I'm a teapot"),

    @Deprecated
    INSUFFICIENT_SPACE_ON_RESOURCE(419, "Insufficient Space On Resource"),

    @Deprecated
    METHOD_FAILURE(420, "Method Failure"),

    @Deprecated
    DESTINATION_LOCKED(421, "Destination Locked"),

    UNPROCESSABLE_ENTITY(422, "Unprocessable Entity"),

    LOCKED(423, "Locked"),

    FAILED_DEPENDENCY(424, "Failed Dependency"),

    UPGRADE_REQUIRED(426, "Upgrade Required"),

    PRECONDITION_REQUIRED(428, "Precondition Required"),

    TOO_MANY_REQUESTS(429, "Too Many Requests"),

    REQUEST_HEADER_FIELDS_TOO_LARGE(431, "Request Header Fields Too Large"),

    UNAVAILABLE_FOR_LEGAL_REASONS(451, "Unavailable For Legal Reasons"),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

    NOT_IMPLEMENTED(501, "Not Implemented"),

    BAD_GATEWAY(502, "Bad Gateway"),

    SERVICE_UNAVAILABLE(503, "Service Unavailable"),

    GATEWAY_TIMEOUT(504, "Gateway Timeout"),

    HTTP_VERSION_NOT_SUPPORTED(505, "HTTP Version not supported"),

    VARIANT_ALSO_NEGOTIATES(506, "Variant Also Negotiates"),

    INSUFFICIENT_STORAGE(507, "Insufficient Storage"),

    LOOP_DETECTED(508, "Loop Detected"),

    BANDWIDTH_LIMIT_EXCEEDED(509, "Bandwidth Limit Exceeded"),

    NOT_EXTENDED(510, "Not Extended"),

    NETWORK_AUTHENTICATION_REQUIRED(511, "Network Authentication Required");
    ;

    // 成员变量
    private int code;
    private String message;

    private ResponseStatusConstant(int code , String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
