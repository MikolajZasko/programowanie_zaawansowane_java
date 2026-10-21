import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class problem_a610 {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // get the n
        int n = input.nextInt();

        System.out.println();

        int possible_sets = 0;
        Set<Integer> s = new HashSet<Integer>();

        if (n < 6) {
            System.out.println(possible_sets);
            return;
        }

        for (int i = 1; i < n; i++) {
            int c = n;

            c -= i * 2;

            if (c > 0 && (c % 2) == 0) {

                // i is the first edge length and nd_edge is the second
                int nd_edge = c/2;
                
                if (! s.contains(i) && ! s.contains(nd_edge) && i != nd_edge) {
                    possible_sets++;
                    s.add(i);
                    s.add(c/2);
                    continue;
                }
            }
        }

        System.out.println(possible_sets);
        return;
    }
}