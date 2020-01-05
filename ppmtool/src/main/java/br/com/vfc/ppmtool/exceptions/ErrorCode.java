package br.com.vfc.ppmtool.exceptions;

public interface ErrorCode {

    String MISSING_REQUEST_PARAMETER = "error.missingRequestParameter.message";
    String MEDIA_TYPE_NOT_ACCEPTABLE = "error.mediaTypeNotAcceptable.message";
    String MEDIA_TYPE_NOT_SUPPORTABLE = "error.mediaTypeNotSupported.message";
    String JSON_REQUEST_MALFORMED = "error.jsonRequestMalformed.message";
    String MESSAGE_NOT_ACCEPTABLE = "error.messageNotAcceptable.message";
    String UNAUTHORIZED = "error.unauthorized.message";
    String USERNAME_INVALID = "error.usernameInvalid.message";
    String PASSWORD_INVALID = "error.passwordInvalid.message";

    String INTERNAL_SERVER_ERROR = "error.internalServerError.message";
    String SERVICE_UNAVAILABLE = "error.serviceUnavailable.message";
    String ERROR_PAGE = "error.errorPage";

    String DECIMAL_MAX = "error.default.decimalMax.message";
    String DECIMAL_MIN = "error.default.decimalMin.message";
    String MAX = "error.default.max.message";
    String MIN = "error.default.min.message";
    String POSITIVE = "error.default.positive.message";
    String POSITIVE_OR_ZERO = "error.default.positiveOrZero.message";
    String EMAIL = "error.default.email.message";
    String PATTERN = "error.default.pattern.message";

    String FUTURE = "error.default.future.message";
    String FUTURE_OR_PRESENT = "error.default.futureOrPresent.message";
    String PAST = "error.default.past.message";
    String PAST_OR_PRESENT = "error.default.pastOrPresent.message";
    String DURATION_MAX = "error.default.durationMax.message";
    String DURATION_MIN = "error.default.durationMin.message";

    String SIZE = "error.default.size.message";
    String LENGTH = "error.default.length.message";
    String NEGATIVE = "error.default.negative.message";
    String NEGATIVE_OR_ZERO = "error.default.negativeOrZero.message";

    String NOT_BLANK = "error.default.notBlank.message";
    String NOT_EMPTY = "error.default.notEmpty.message";
    String NOT_NULL = "error.default.notNull.message";
    String NULL = "error.default.null.message";

    String PASSWORD_CONFIRM = "error.passwordConfirm.message";
    String RESOURCE_NOT_FOUND = "error.notFound.message";
    String PROJECT_CONFLICT = "error.projectConflict.message";
    String USER_CONFLICT = "error.userConflict.message";
}
