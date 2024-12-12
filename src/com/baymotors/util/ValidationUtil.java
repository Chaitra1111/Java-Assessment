package com.baymotors.util;

import java.util.regex.Pattern;

public class ValidationUtil {

    // Regex for name validation (only letters and spaces)
    private static final String NAME_REGEX = "^[a-zA-Z ]+$";
    private static final Pattern NAME_PATTERN = Pattern.compile(NAME_REGEX);

    // Regex for email validation (standard email format)
    private static final String EMAIL_REGEX = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    // Regex for phone number validation (10 digits, optional country code)
    private static final String PHONE_REGEX = "^(\\+\\d{1,3}\\s?)?\\d{10}$"; // Allows optional country code

    // Regex for license plate validation (e.g., ABC-1234 or ABC 1234)
    private static final String LICENSE_PLATE_REGEX = "^[A-Z0-9]{1,3}[-\\s]?[0-9]{1,4}$"; // License plate with optional hyphen or space

    // Validates name (only letters and spaces)
    public static boolean isValidName(String name) {
        return name != null && NAME_PATTERN.matcher(name).matches();
    }

    // Validates email using regex
    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    // Validates phone number (only 10-digit phone numbers, optional country code)
    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches(PHONE_REGEX);
    }

    // Validates license plate (alphanumeric characters, optional space or hyphen)
    public static boolean isValidLicensePlate(String licensePlate) {
        return licensePlate != null && licensePlate.matches(LICENSE_PLATE_REGEX);
    }
    
    // Validates model (alphanumeric and spaces allowed)
    public static boolean isValidModel(String model) {
        // Example for alphanumeric validation with spaces
        return model != null && model.matches("^[a-zA-Z0-9 ]+$");
    }
}