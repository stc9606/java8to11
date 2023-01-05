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

    private void person() {
        Person person = new DefaultPerson();
        System.out.println(person.getNameToUpperCase("class method"));
        person.printMessage("default method !");

        // static method
        System.out.println(Person.getMethod());
    }

    private void run() {

        int baseNumber = 10; // effective final

        // 로컬, 익명 클래스는 run class 와 다른 scope
        // 람다는 run 클래스와 같은 scope, 대신 변수에 대한 참조는 effective final 변수만 쉐도잉 가능

        // 로컬 클래스
        class LocalClass {
            void printBaseNumber() {
                System.out.println(baseNumber);
            }
        }

        // 익명 클래스
        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(baseNumber);
            }
        };

        // 람다
        IntConsumer intConsumer = (i) -> {
            System.out.println(i + baseNumber);
        };

        intConsumer.accept(10);

    }

}
