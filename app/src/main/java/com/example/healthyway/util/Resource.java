package com.example.healthyway.util;

import org.jetbrains.annotations.Nullable;

public abstract class Resource {
    @Nullable
    private final Object data;
    @Nullable
    private final String message;

    @Nullable
    public final Object getData() {
        return this.data;
    }

    @Nullable
    public final String getMessage() {
        return this.message;
    }

    private Resource(Object data, String message) {
        this.data = data;
        this.message = message;
    }
    public static final class Success extends Resource {
        public Success(Object data, String message) {
            super(data, message);
        }
    }

    public static final class Loading extends Resource {
        private Loading(Object data, String message) {
            super(data, message);
        }
    }
    public static final class Error extends Resource {
        public Error(Object data, String message) {
            super(data, message);
        }
    }

}


