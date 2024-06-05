package uz.com.tuir.loanagreementmanager.constant;

import lombok.Getter;

@Getter
public enum EExceptionType {
    OK(200, "Ok"),
    BAD_REQUEST(400, "Bad request!"),
    UNAUTHORIZED(401, "Unauthorized!"),
    FORBIDDEN(403, "Forbidden!"),
    NOT_FOUND(404, "Could not found!"),
    METHOD_NOT_ALLOWED(405, "Method not allowed!"),
    NOT_ACCEPTABLE_STATUS(406, "Not acceptable!"),
    TIMEOUT(408, "Timeout!"),
    CONFLICT(409, "Conflict!"),
    GONE(410, "GONE!"),
    UNSUPPORTED_MEDIA_TYPE(415, "Invalid media type!"),
    EXTERNAL_ERROR(424, "External service error!"),
    INTERNAL_ERROR(500, "Internal server error!"),
    INVALID_PARAM(400, "Invalid parameters!"),
    INVALID_TARGET(400, "Invalid target!"),
    NOT_IMPLEMENTED(501, "Request method not implemented!"),
    SERVICE_UNAVAILABLE(503, "Service unavailable!"),
    TOO_MANY_REQUEST(429, "Too many request!");

    private final Integer errorCode;
    private final String errorMessage;

    private EExceptionType(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}
