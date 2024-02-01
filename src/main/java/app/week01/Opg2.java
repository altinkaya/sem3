package app.week01;

import java.util.Arrays;

public class Opg2 {

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

    public static void main(String[] args) {
        int[] myArray = {1, 2, 3, 4, 5, 6};

        int[] mappedArray = map(myArray, (x) -> x * 2);
        System.out.println(Arrays.toString(mappedArray));

        int[] filteredArray = filter(myArray, (x) -> x % 3 == 0);
        System.out.println(Arrays.toString(filteredArray));
    }
}
