package com.scnoh.java8to11;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Runner {

    public static void main(String[] args) {
        stream();
    }

    /**
     * TODO 함수형 인터페이스
     */
    private void javaApi() {


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

    /**
     * TODO 로컬 클래스 & 익명 클래스 스코프
     */
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

    /**
     * TODO Default Method & Static Method
     */
    private void method() {
        Person person = new DefaultPerson();
        System.out.println(person.getNameToUpperCase("class method"));
        person.printMessage("default method !");

        // static method
        System.out.println(Person.getMethod());
    }

    /**
     * TODO Stream API
     */
    public static void stream() {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring batch", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "spring mvc", false));

        List<OnlineClass> javaClasses = new ArrayList<>();
        javaClasses.add(new OnlineClass(6, "The Java, Test", true));
        javaClasses.add(new OnlineClass(7, "The Java, Code manipulation", true));
        javaClasses.add(new OnlineClass(8, "The Java, 8 to 11", false));

        List<List<OnlineClass>> scnohEvents = new ArrayList<>();
        scnohEvents.add(springClasses);
        scnohEvents.add(javaClasses);

        System.out.println("\nspring 으로 시작하는 수업");
        // TODO
        springClasses.stream()
                .filter(c -> c.getTitle().startsWith("spring"))
                .forEach(c -> System.out.println(c.getTitle()));

        System.out.println("\nclose 되지 않은 수업");
        // TODO
        scnohEvents.stream()
                .flatMap(Collection::stream)
                .filter(Predicate.not(OnlineClass::isClosed))
                .forEach(c -> System.out.println(c.getTitle()));

        System.out.println("\n수업 이름만 모아서 스트림 만들기");
        // TODO
        Stream<String> stringTitles = scnohEvents.stream()
                .flatMap(Collection::stream)
                .map(c -> c.getTitle());
        stringTitles.forEach(t -> System.out.println(t));


        System.out.println("\n두 수업 목록에 들어있는 모든 수업 아이디 출력");
        // TODO
        scnohEvents.stream()
                .flatMap(Collection::stream)
                .forEach(c -> System.out.println(c.getId()));

        System.out.println("\n10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 빼고 최대 10개 까지만");
        // TODO
        Stream.iterate(10, i -> i + 1)
                .skip(10)
                .limit(10)
                .forEach(System.out::println);

        System.out.println("\n자바 수업 중에 Test가 들어 있는 수업이 있는지 확인");
        // TODO
        boolean test = javaClasses.stream().anyMatch(c -> c.getTitle().contains("Test"));
        System.out.println(test);

        System.out.println("\n스프링 수업 중에 제목에 spring이 들어간 제목만 모아서 List로 만들기");
        List<String> stringList = springClasses.stream()
                .filter(c -> c.getTitle().contains("spring"))
                .map(c -> c.getTitle())
                .collect(Collectors.toList());
        stringList.forEach(s -> System.out.println(s));
    }

}
