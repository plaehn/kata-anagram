import com.google.common.base.Joiner;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import static java.nio.charset.StandardCharsets.UTF_8;

public class AnagramMain {

    private static final AnagramFinder anagramFinder = new AnagramFinder();

    public static void main(String[] args) {
        Set<String> words = readWordlist();

        SortedSet<String> anagrams = anagramFinder.findTwoWordAnagrams("documenting", words);

        System.out.println("Found the following two-word anagrams:");
        System.out.println(Joiner.on("\n").join(anagrams));
    }

    private static Set<String> readWordlist() {
        try (InputStream inputStream = AnagramMain.class.getResourceAsStream("wordlist.txt")) {
            List<String> lines = IOUtils.readLines(inputStream, UTF_8);
            return new HashSet<>(lines);
        } catch (IOException exx) {
            throw new IllegalArgumentException("Could not read word list", exx);
        }
    }
}
