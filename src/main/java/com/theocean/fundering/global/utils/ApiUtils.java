package com.theocean.fundering.global.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

public class ApiUtils {

    public static <T> ApiResult<T> success(T response) {
        return new ApiResult<>(true, response, null);
    }

    public static ApiResult<?> error(String message, HttpStatus status) {
        return new ApiResult<>(false, null, new ApiError(message, status.value()));
    }

<<<<<<< HEAD
=======

>>>>>>> 45dab9e9340f43682c8926b4c4df690d0fefcfa8
    @Getter @Setter @AllArgsConstructor
    public static class ApiResult<T> {
        private final boolean success;
        private final T response;
        private final ApiError error;
    }

    @Getter @Setter @AllArgsConstructor
    public static class ApiError {
        private final String message;
        private final int status;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 45dab9e9340f43682c8926b4c4df690d0fefcfa8
