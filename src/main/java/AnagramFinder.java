import com.google.common.base.Joiner;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Collections.emptySortedSet;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class AnagramFinder {

    private final Multimap<BagOfLetters, String> availableBags2SurfaceForm;

    public AnagramFinder(Set<String> availableWords) {
        checkNotNull(availableWords);

        availableBags2SurfaceForm = ArrayListMultimap.create();
        for (String availableWord : availableWords) {
            BagOfLetters bag = BagOfLetters.fromString(availableWord);
            availableBags2SurfaceForm.put(bag, availableWord);
        }
    }

    public SortedSet<String> findTwoWordAnagramsFor(String input) {
        if (isBlank(input)) {
            return emptySortedSet();
        }

        BagOfLetters inputBag = BagOfLetters.fromString(input);
        Set<Pair<BagOfLetters, BagOfLetters>> pairs = inputBag.computeAllDisjointNonEmptyPairs();

        SortedSet<String> twoWordAnagrams = new TreeSet<>();
        for (Pair<BagOfLetters, BagOfLetters> potentialAnagram : pairs) {
            if (isTwoWordAnagram(potentialAnagram)) {
                twoWordAnagrams.addAll(generateAllTwoWordAnagrams(potentialAnagram));
            }
        }
        return twoWordAnagrams;
    }

    private Set<String> generateAllTwoWordAnagrams(Pair<BagOfLetters, BagOfLetters> potentialAnagram) {
        Set<String> leftWords = new HashSet<>(availableBags2SurfaceForm.get(potentialAnagram.getLeft()));
        Set<String> rightWords = new HashSet<>(availableBags2SurfaceForm.get(potentialAnagram.getRight()));
        Set<List<String>> cartesianProduct = Sets.cartesianProduct(List.of(leftWords, rightWords));

        Set<String> allAnagrams = new HashSet<>();
        for (List<String> anagram : cartesianProduct) {
            allAnagrams.add(Joiner.on(" ").join(anagram));
        }
        return allAnagrams;
    }

    private boolean isTwoWordAnagram(Pair<BagOfLetters, BagOfLetters> potentialAnagram) {
        return availableBags2SurfaceForm.containsKey(potentialAnagram.getLeft())
                && availableBags2SurfaceForm.containsKey(potentialAnagram.getRight());
    }
}
