package com.scnoh.java8to11;

public class DefaultPerson implements Person {

    @Override
    public String getNameToUpperCase(String name) {
        return name.toUpperCase();
    }

    @Override
    public String getNameToLowerCase(String name) {
        return name.toLowerCase();
    }
}
