package com.ezcode.utils;

public class ResponseConstants {

    // HTTP Response Codes
    public static final int RESPONSE_CODE_OK = 200;
    public static final int RESPONSE_CODE_CREATED = 201;
    public static final int RESPONSE_CODE_ACCEPTED = 202;
    public static final int RESPONSE_CODE_NO_CONTENT = 204;
    public static final int RESPONSE_CODE_BAD_REQUEST = 400;
    public static final int RESPONSE_CODE_UNAUTHORIZED = 401;
    public static final int RESPONSE_CODE_FORBIDDEN = 403;
    public static final int RESPONSE_CODE_NOT_FOUND = 404;
    public static final int RESPONSE_CODE_CONFLICT = 409;
    public static final int RESPONSE_CODE_INTERNAL_ERROR = 500;
    public static final int RESPONSE_CODE_SERVICE_UNAVAILABLE = 503;

    // General Messages
    public static final String MESSAGE_SUCCESS = "Operation completed successfully";
    public static final String MESSAGE_CREATED = "Resource created successfully";
    public static final String MESSAGE_UPDATED = "Resource updated successfully";
    public static final String MESSAGE_DELETED = "Resource deleted successfully";
    public static final String MESSAGE_NOT_FOUND = "Resource not found";
    public static final String MESSAGE_BAD_REQUEST = "Invalid request parameters";
    public static final String MESSAGE_UNAUTHORIZED = "Unauthorized access";
    public static final String MESSAGE_FORBIDDEN = "Access forbidden";
    public static final String MESSAGE_CONFLICT = "Resource conflict detected";
    public static final String MESSAGE_INTERNAL_ERROR = "Internal server error";
    public static final String MESSAGE_SERVICE_UNAVAILABLE = "Service is temporarily unavailable";
    public static final String MESSAGE_VALIDATION_FAILED = "Validation failed";
    public static final String MESSAGE_OPERATION_FAILED = "Operation failed";

    // Status Constants
    public static final String STATUS_SUCCESS = "SUCCESS";
    public static final String STATUS_FAILURE = "FAILURE";

    // Stock-specific Messages
    public static final String MESSAGE_STOCK_SAVED = "Stock saved successfully";
    public static final String MESSAGE_STOCK_RETRIEVED = "Stock retrieved successfully";
    public static final String MESSAGE_STOCK_UPDATED = "Stock updated successfully";
    public static final String MESSAGE_STOCK_DELETED = "Stock deleted successfully";
    public static final String MESSAGE_STOCK_NOT_FOUND = "Stock not found";

    // User-specific Messages
    public static final String MESSAGE_USER_CREATED = "User created successfully";
    public static final String MESSAGE_USER_UPDATED = "User updated successfully";
    public static final String MESSAGE_USER_DELETED = "User deleted successfully";
    public static final String MESSAGE_USER_NOT_FOUND = "User not found";

    // Authentication and Authorization
    public static final String MESSAGE_LOGIN_SUCCESS = "Login successful";
    public static final String MESSAGE_LOGIN_FAILED = "Login failed, please check your credentials";
    public static final String MESSAGE_LOGOUT_SUCCESS = "Logout successful";
    public static final String MESSAGE_ACCESS_DENIED = "Access denied, insufficient permissions";

    // Validation Errors
    public static final String MESSAGE_MISSING_REQUIRED_FIELDS = "Required fields are missing";
    public static final String MESSAGE_INVALID_FIELD_VALUES = "Invalid field values provided";
    public static final String MESSAGE_DUPLICATE_ENTRY = "Duplicate entry detected";

    // API Operation Names (for logging, documentation)
    public static class ApiOperations {
        // Stock API Operations
        public static final String SAVE_STOCK = "Save or update stock";
        public static final String GET_ALL_STOCKS = "Retrieve all stocks";
        public static final String GET_STOCK_BY_ID = "Retrieve stock by ID";
        public static final String DELETE_STOCK_BY_ID = "Delete stock by ID";
        public static final String UPDATE_STOCK = "Update stock details";

        // User API Operations
        public static final String CREATE_USER = "Create new user";
        public static final String UPDATE_USER = "Update user details";
        public static final String DELETE_USER = "Delete user by ID";
        public static final String GET_USER_BY_ID = "Retrieve user by ID";
        public static final String GET_ALL_USERS = "Retrieve all users";

        // Authentication Operations
        public static final String USER_LOGIN = "User login";
        public static final String USER_LOGOUT = "User logout";

    }

    // Custom Status Messages for Extended Responses
    public static final String TICKET_GENERATION_FAILED = "Failed to generate tracking ticket";
    public static final String MESSAGE_EXPIRED_SESSION = "Session has expired, please login again";
    public static final String MESSAGE_REQUEST_TIMEOUT = "The request timed out, please try again later";
}
