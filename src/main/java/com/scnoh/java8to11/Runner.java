package com.scnoh.java8to11;

import java.util.function.*;

public class Runner {

    public static void main(String[] args) {

        /**
         * TODO 함수형 인터페이스
         */

        Function<Integer, Integer> funAdd = (i) -> i + 1;
        System.out.println("Function: " + funAdd.apply(1));

        BiFunction<Integer, Integer, Integer> funTotal = (i, j) -> i + j;
        System.out.println("BiFunction: " + funTotal.apply(1, 2));

        Consumer<String> getStr = (s) -> System.out.println(s);
        getStr.accept("Consumer: Java Function !");

        Supplier<Integer> getNum = () -> 10;
        System.out.println("Supplier: " + getNum.get());

        BooleanSupplier isOrigin = () -> getNum.get() == 10;
        System.out.println("Boolean Supplier: " + isOrigin.getAsBoolean());

        Predicate<String> isJava = (str) -> str.startsWith("Java");
        System.out.println(isJava.test("Java Function"));
    }

}
