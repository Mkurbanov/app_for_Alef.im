package com.mkurbanov.appforalefim.global;

import androidx.annotation.Nullable;

public class GlobalResult {
    @Nullable
    private String errorText;

    public GlobalResult() {
    }

    public GlobalResult(@Nullable String errorText) {
        this.errorText = errorText;
    }

    @Nullable
    public String getErrorText() {
        return errorText;
    }
}
