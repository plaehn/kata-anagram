import com.google.common.base.Objects;
import com.google.common.collect.ImmutableSet;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashSet;
import java.util.Set;

import static com.google.common.base.CharMatcher.whitespace;
import static com.google.common.collect.Sets.difference;
import static com.google.common.collect.Sets.powerSet;
import static java.util.Collections.emptySet;
import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toSet;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class BagOfLetters {

    private final Set<Character> characterSet;

    private BagOfLetters(Set<Character> characterSet) {
        this.characterSet = characterSet;
    }

    public static BagOfLetters fromString(String input) {
        if (isBlank(input)) {
            return new BagOfLetters(emptySet());
        }
        Set<Character> characterSet = input
                .chars()
                .mapToObj(c -> (char) c)
                .filter(not(whitespace()::matches))
                .collect(toSet());
        return new BagOfLetters(characterSet);
    }

    public boolean isEmpty() {
        return characterSet.isEmpty();
    }

    public Set<Character> getCharacterSet() {
        return ImmutableSet.copyOf(characterSet);
    }

    public Set<Pair<BagOfLetters, BagOfLetters>> computeAllDisjointNonEmptyPairs() {
        Set<Pair<BagOfLetters, BagOfLetters>> allPairs = new HashSet<>();
        for (Set<Character> subset : powerSet(characterSet)) {
            if (!subset.isEmpty()) {
                allPairs.add(createPair(subset));
            }
        }
        return allPairs;
    }

    private Pair<BagOfLetters, BagOfLetters> createPair(Set<Character> subset) {
        BagOfLetters left = new BagOfLetters(subset);
        BagOfLetters right = new BagOfLetters(difference(characterSet, subset));
        return Pair.of(left, right);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BagOfLetters that = (BagOfLetters) o;
        return Objects.equal(characterSet, that.characterSet);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(characterSet);
    }
}
