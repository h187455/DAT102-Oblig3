package oblig3;

import java.util.HashSet;
import java.util.Set;

public class HobbyMatch {
    public static double match(Person a, Person b) {
        Set<String> felles = new HashSet<>(a.getHobbyer());
        felles.retainAll(b.getHobbyer());

        Set<String> kunHosA = new HashSet<>(a.getHobbyer());
        kunHosA.removeAll(b.getHobbyer());

        Set<String> kunHosB = new HashSet<>(b.getHobbyer());
        kunHosB.removeAll(a.getHobbyer());

        int antallFelles = felles.size();
        int antallKunHosDenE = kunHosA.size() + kunHosB.size();
        int antallTotalt = a.getHobbyer().size() + b.getHobbyer().size();

        return (double) (antallFelles - antallKunHosDenE) / antallTotalt;
    }

    public static void main(String[] args) {
        Person Lana = new Person("Lana", "slalom", "sykling", "gambling", "data");
        Person Johnny = new Person("Johnny", "sykling", "data", "gambling");
        Person Martin = new Person("Martin", "slalom", "fiske", "tur", "fotball");

        System.out.println("0.5 er best match, -1 er dårligast match!");
        System.out.println("Match Lana - Johnny: " + match(Lana, Johnny));
        System.out.println("Match Lana - Martin: " + match(Lana, Martin));
        System.out.println("Match Johnny - Martin: " + match(Johnny, Martin));
    }
}
