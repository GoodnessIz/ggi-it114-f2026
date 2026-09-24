public class Overflow {
    public static void main(String[] args) {
        int largest = Integer.MAX_VALUE;
        int overflow = largest + 1;

        System.out.println("Largest int value: " + largest);
        System.out.println("After adding 1, it wraps to: " + overflow);
    }
}
