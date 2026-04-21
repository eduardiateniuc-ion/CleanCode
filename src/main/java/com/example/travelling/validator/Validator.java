package com.example.travelling.validator;

public interface Validator<T> {
    boolean isValid(T object);
}
