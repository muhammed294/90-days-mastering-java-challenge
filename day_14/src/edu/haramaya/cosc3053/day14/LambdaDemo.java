package edu.haramaya.cosc3053.day14;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class LambdaDemo {

    // Custom functional interface (the title of today's challenge)
    @FunctionalInterface
    interface Validator<T> {
        boolean validate(T value);

        // default and static methods do NOT count as abstract methods
        default Validator<T> and(Validator<T> other) {
            return v -> this.validate(v) && other.validate(v);
        }
    }

    // Takes a behavior as a parameter: a custom filtering method
    static <T> List<T> filterList(List<T> items, Predicate<T> rule) {
        return items.stream().filter(rule).collect(Collectors.toList());
    }

    private static void runDemo() {
        List<Integer> nums = List.of(12, 45, 68, 23, 90, 11);

        System.out.println("--- Predicate ---");
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Predicate<Integer> isBig = n -> n > 50;
        System.out.println("even AND big : " + filterList(nums, isEven.and(isBig)));
        System.out.println("even OR big  : " + filterList(nums, isEven.or(isBig)));
        System.out.println("NOT even     : " + filterList(nums, isEven.negate()));

        System.out.println("--- Function ---");
        Function<Integer, Integer> square = n -> n * n;
        System.out.println("squares: " + nums.stream().map(square).collect(Collectors.toList()));

        System.out.println("--- Consumer ---");
        Consumer<String> shout = s -> System.out.println(s.toUpperCase() + "!");
        shout.accept("haramaya");

        System.out.println("--- Supplier ---");
        Supplier<String> greeting = () -> "Hello from a Supplier";
        System.out.println(greeting.get());

        System.out.println("--- Method reference ---");
        nums.stream().filter(isEven).forEach(System.out::println);

        System.out.println("--- Custom @FunctionalInterface ---");
        Validator<String> notBlank = s -> s != null && !s.isBlank();
        Validator<String> shortText = s -> s.length() <= 10;
        Validator<String> both = notBlank.and(shortText);
        System.out.println("\"Abebe\" valid?  " + both.validate("Abebe"));
        System.out.println("\"\" valid?       " + both.validate(""));
        System.out.println("long text valid? " + both.validate("this text is too long"));
    }

    public static void main(String[] args) {
        List<Integer> nums = List.of(12, 45, 68, 23, 90, 11);

        // TODO 14.1: Use Predicate<T> to filter a list of numbers
        Predicate<Integer> isEven = n -> n % 2 == 0;

        System.out.print("Even Numbers: ");
        nums.stream().filter(isEven).forEach(n -> System.out.print(n + " "));
        System.out.println();

        if (args.length > 0 && args[0].equals("demo")) {
            runDemo();
        }
    }
}