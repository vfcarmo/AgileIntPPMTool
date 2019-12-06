package br.com.vfc.ppmtool.exceptions;

public interface ErrorCode {

    String MISSING_REQUEST_PARAMETER = "missingRequestParameter.message";
    String MEDIA_TYPE_NOT_ACCEPTABLE = "mediaTypeNotAcceptable.message";
    String MEDIA_TYPE_NOT_SUPPORTABLE = "mediaTypeNotSupported.message";
    String JSON_REQUEST_MALFORMED = "jsonRequestMalformed.message";

    String INTERNAL_SERVER_ERROR = "internalServerError.message";
    String SERVICE_UNAVAILABLE = "serviceUnavailable.message";
    String ERROR_PAGE = "errorPage";

    String DECIMAL_MAX = "decimalMax.message";
    String DECIMAL_MIN = "decimalMin.message";
    String MAX = "max.message";
    String MIN = "min.message";
    String POSITIVE = "positive.message";
    String POSITIVE_OR_ZERO = "positiveOrZero.message";
    String EMAIL = "email.message";
    String PATTERN = "pattern.message";

    String FUTURE = "future.message";
    String FUTURE_OR_PRESENT = "futureOrPresent.message";
    String PAST = "past.message";
    String PAST_OR_PRESENT = "pastOrPresent.message";

    String SIZE = "size.message";
    String LENGTH = "length.message";
    String NEGATIVE = "negative.message";
    String NEGATIVE_OR_ZERO = "negativeOrZero.message";

    String NOT_BLANK = "notBlank.message";
    String NOT_EMPTY = "notEmpty.message";
    String NOT_NULL = "notNull.message";
    String NULL = "null.message";

    String PROJECT_NOT_FOUND = "projectNotFound.message";
    String PROJECT_CONFLICT = "projectConflict.message";
}
