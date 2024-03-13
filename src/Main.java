import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int count = 0;
        int total = 0;
        print("finding words...");
        long startTime = System.currentTimeMillis();
        for (String[] dict : DICT) {
            for (String word : dict) {
                total++;
                Word w = new Word(word);
                if (w.isNC(DICT)) count++;
            }
        }
        long endTime = System.currentTimeMillis();
        print("total: " + total);
        print("NCs: " + count);
        print("finished in " + (endTime - startTime) + " milliseconds.");

    }

    static String[][] DICT = makeDictionaries("words.txt");
    public static String[][] makeDictionaries(String filename) {
        //print("Making dictionaries...");
        File file = new File(filename);

        // get max length
        int max = 0;
        try {
            Scanner input = new Scanner(file);
            while ( input.hasNext() ) {
                max = Math.max(input.next().length(), max);
            }
        } catch (IOException ex) {
            print("An error occurred!");
            print(ex.getMessage());
        }

        ArrayList< ArrayList<String> > master = new ArrayList<>();
        for (int i = 0; i < max; i++) {
            master.add(new ArrayList<>());
        }
        String[][] dictArray = new String[max][0];


        try {
            Scanner input = new Scanner(file);
            master.get(0).add("i");
            master.get(0).add("a");
            while ( input.hasNext() ) {
                String word = input.next();
                if ( word.length() > 1 && Character.isLowerCase(word.charAt(0)) ) {
                    master.get(word.length() - 1).add(word);
                }
            }
        } catch (IOException ex) {
            print("An error occurred!");
            print(ex.getMessage());
        }

        for (int i = 0; i < dictArray.length; i++) {
            dictArray[i] = new String[master.get(i).size()];

            for (int j = 0; j < master.get(i).size(); j++) {
                dictArray[i][j] = master.get(i).get(j);
            }
        }
        return dictArray;
    }

    public static void print(Object s) {
        System.out.print(s + "\n");
    }
}