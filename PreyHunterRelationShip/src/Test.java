/**
 *
 * @author erdem
 */
import java.util.Scanner;
public class Test {
    public static void main(String[] args) {
        Hunting h1 = new Hunting();
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("You can quit with press q");
            System.out.println(h1.toString());
            h1.move();   
        } while (!"q".equals(sc.nextLine()));
        System.out.println("");
    }
    
}