package app.week01;

import java.util.Arrays;

public class Opg5 {

    @FunctionalInterface
    public interface MyTransformingType {
        int transform(int a);
    }

    @FunctionalInterface
    public interface MyValidatingType {
        boolean validate(int a);
    }

    public static int[] map(int[] a, MyTransformingType op) {
        int[] result = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = op.transform(a[i]);
        }
        return result;
    }

    public static int[] filter(int[] a, MyValidatingType op) {
        return Arrays.stream(a)
                .filter(op::validate)
                .toArray();
    }

    public static int doubleValue(int x) {
        return x * 2;
    }

    public static boolean isDivisibleByThree(int x) {
        return x % 3 == 0;
    }

    public static void main(String[] args) {
        int[] myArray = {1, 2, 3, 4, 5, 6};

        MyTransformingType doubleValue = Opg5::doubleValue;
        int[] mappedArray = map(myArray, doubleValue);
        System.out.println(Arrays.toString(mappedArray));

        MyValidatingType isDivisibleByThree = Opg5::isDivisibleByThree;
        int[] filteredArray = filter(myArray, isDivisibleByThree);
        System.out.println(Arrays.toString(filteredArray));
    }
}
