import java.util.ArrayList;
import java.util.Arrays;

public class Word {
    public String s;
    public char[] vowels = {'a', 'e', 'i', 'o', 'u', 'y'};

    public Word(String s) {
        this.s = s;
    }

    public boolean isNC(String[][] dict) {
        ArrayList<String> products = new ArrayList<>();

        if (s.endsWith("s") || s.endsWith("ed") || s.endsWith("ing")) return false;
        int count = 0;
        for (int k = 1; k < dict.length; k++) {
            for (int i = 1; i < s.length() - k; i++) {
                String innerWord = s.substring(i, i + k);
                String outerWord = s.substring(0, i) + s.substring(i + k);

                // if (!hasVowels(innerWord) || !hasVowels(outerWord)) continue;

                if (dict[innerWord.length() - 1].length < dict[outerWord.length() - 1].length) {
                    if ( lookup(innerWord, dict) && lookup(outerWord, dict)) {
                        print(s + " produces " + innerWord + " and " + outerWord);
                        products.add(innerWord);
                        products.add(outerWord);
                        count++;
                    }
                } else {
                    if ( lookup(outerWord, dict) && lookup(innerWord, dict)) {
                        print(s + " produces " + innerWord + " and " + outerWord);
                        products.add(innerWord);
                        products.add(outerWord);
                        count++;
                    }
                }

            }
        }
        /*
        if (count > 2) {
            for (int i = 0; i < products.size(); i += 2) {
                print(s + " produces " + products.get(i) + " and " + products.get(i+1));
            }
            print("----------------");
            return true;
        }*/
       return (count > 0);
    }

    private boolean hasVowels(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (Arrays.binarySearch(vowels, str.charAt(i)) > -1) {
                return true;
            }
        }
        return false;
    }

    private boolean lookup(String s, String[][] dict) {
        int length = s.length();
        return Arrays.binarySearch(dict[length - 1], s) > -1;
    }

    private static void print(Object s) {
        System.out.print(s + "\n");
    }

}
