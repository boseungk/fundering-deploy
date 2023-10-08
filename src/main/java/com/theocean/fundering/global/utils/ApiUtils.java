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
<<<<<<< HEAD
=======

>>>>>>> 45dab9e9340f43682c8926b4c4df690d0fefcfa8
=======

>>>>>>> fae78ac4509cc55d2823a68b4152a52e02c500dd
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
<<<<<<< HEAD
}
=======
}
>>>>>>> 45dab9e9340f43682c8926b4c4df690d0fefcfa8
=======

}
>>>>>>> fae78ac4509cc55d2823a68b4152a52e02c500dd
