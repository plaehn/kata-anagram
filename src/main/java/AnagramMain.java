import com.google.common.base.Joiner;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.commons.io.IOUtils.readLines;

public class AnagramMain {

    private static final String NEWLINE = "\n";

    public static void main(String[] args) {
        Set<String> words = readWordlist();

        AnagramFinder anagramFinder = new AnagramFinder(words);
        SortedSet<String> anagrams = anagramFinder.findTwoWordAnagramsFor("documenting");

        System.out.println("Found the following two-word anagrams:");
        System.out.println(Joiner.on(NEWLINE).join(anagrams));
    }

    private static Set<String> readWordlist() {
        try (InputStream inputStream = AnagramMain.class.getResourceAsStream("wordlist.txt")) {
            List<String> lines = readLines(inputStream, UTF_8);
            return new HashSet<>(lines);
        } catch (IOException exx) {
            throw new IllegalArgumentException("Could not read word list", exx);
        }
    }
}
