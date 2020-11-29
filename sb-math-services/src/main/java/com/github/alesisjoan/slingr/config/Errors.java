// Copyright(c) 2016 by TimeTrade Systems.  All Rights Reserved.
package com.github.alesisjoan.slingr.config;

import java.util.List;

/**
 * Object that bundles a list of errors together with standard meta data.
 */
public class Errors {
    private int statusCode;

    private List<FieldError> errorList;

    public Errors(int statusCode, List<FieldError> errorList) {
        this.statusCode = statusCode;
        this.errorList = errorList;
    }

    public int getErrorCount() {
        return errorList.size();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public List<FieldError> getErrorList() {
        return errorList;
    }

    /**
     * Error message related to a specific field.
     */
    public static class FieldError {
        private String field;
        private String errorKey;
        private String errorMessage;

        public FieldError(String errorKey, String errorMessage) {
            this.errorKey = errorKey;
            this.errorMessage = errorMessage;
        }

        public FieldError(String field, String errorKey, String errorMessage) {
            this.field = field;
            this.errorKey = errorKey;
            this.errorMessage = errorMessage;
        }

        public String getField() {
            return field;
        }

        public String getErrorKey() {
            return errorKey;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }
}
