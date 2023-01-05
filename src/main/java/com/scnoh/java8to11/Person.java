package com.scnoh.java8to11;

public interface Person {

    String getNameToUpperCase(String name);
    String getNameToLowerCase(String name);

    default void printMessage(String message) {
        System.out.println(message);
    }

    static String getMethod() {
        return "static method !";
    }
}
