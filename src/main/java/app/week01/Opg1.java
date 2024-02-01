package app.week01;

public class Opg1 {

    interface ArithmeticOperation {
        int perform(int a, int b);
    }

    public static void main(String[] args) {
        ArithmeticOperation addition = (a, b) -> a + b;
        ArithmeticOperation subtraction = (a, b) -> a - b;
        ArithmeticOperation multiplication = (a, b) -> a * b;
        ArithmeticOperation division = (a, b) -> a / b;
        ArithmeticOperation modulus = (a, b) -> a % b;
        ArithmeticOperation power = (a, b) -> (int) Math.pow(a, b);

        System.out.println(operate(5, 3, addition)); // Outputs 8
        System.out.println(operate(5, 3, subtraction)); // Outputs 2
        System.out.println(operate(5, 3, multiplication)); // Outputs 15
        System.out.println(operate(5, 3, division)); // Outputs 1
        System.out.println(operate(5, 3, modulus)); // Outputs 2
        System.out.println(operate(5, 3, power)); // Outputs 125

        int[] a = {1, 2, 3};
        int[] b = {4, 5, 6};
        int[] result = operate(a, b, addition);
        for (int i : result) {
            System.out.print(i + " "); // Outputs 5 7 9
        }
    }

    public static int operate(int a, int b, ArithmeticOperation op) {
        return op.perform(a, b);
    }

    public static int[] operate(int[] a, int[] b, ArithmeticOperation op) {
        if (a.length != b.length) {
            throw new IllegalArgumentException("Arrays must be of the same length");
        }

        int[] result = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = op.perform(a[i], b[i]);
        }
        return result;
    }
}
