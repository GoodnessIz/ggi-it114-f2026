package M2;

public class IfBranches {
    public static void main(String[] args) {
        int health = 40;
        if (health < 50) {
            System.out.println("Strong heal");
        } else if (health < 100) {
            System.out.println("Heal");
        } else {
            System.out.println("Already full");
        }
    } }
    
