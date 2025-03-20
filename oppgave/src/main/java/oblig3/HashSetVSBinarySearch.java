package oblig3;
import java.util.*;

public class HashSetVSBinarySearch {
    public static void main(String[] args) {
        int antElement = 100000;
        int[] tabell = new int[antElement];
        HashSet<Integer> hashSet = new HashSet<>();

        // Generer 100 000 unike tal og legg dei til i tabell og HashSet
        int tall = 376;
        for (int i = 0; i < antElement; i++) {
            tabell[i] = tall;
            hashSet.add(tall);
            tall = (tall + 45713) % 1000000;
        }
        
        // Sorter tabellen for binærsøk
        Arrays.sort(tabell);

        // Generer 10 000 tilfeldige søketal
        Random rand = new Random();
        int[] soketall = new int[10000];
        for (int i = 0; i < soketall.length; i++) {
            soketall[i] = rand.nextInt(1000000);
        }

        // Mål tid for søk i HashSet
        long startTidHashSet = System.nanoTime();
        for (int tallSok : soketall) {
            hashSet.contains(tallSok);
        }
        long sluttTidHashSet = System.nanoTime();
        long tidHashSet = sluttTidHashSet - startTidHashSet;

        // Mål tid for binærsøk i tabell
        long startTidBinarySearch = System.nanoTime();
        for (int tallSok : soketall) {
            Arrays.binarySearch(tabell, tallSok);
        }
        long sluttTidBinarySearch = System.nanoTime();
        long tidBinarySearch = sluttTidBinarySearch - startTidBinarySearch;

        // Skriv ut resultat
        System.out.println("Tid for HashSet-søk: " + tidHashSet + " nanosekunder");
        System.out.println("Tid for binærsøk i tabell: " + tidBinarySearch + " nanosekunder");
    }
}

