import org.junit.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class AnagramFinderTest {

    private static final Set<String> AVAILABLE_WORDS = Set.of("foo", "bar", "baz");

    @Test(expected = NullPointerException.class)
    public void constructorThrowsOnNull() {
        new AnagramFinder(null);
    }

    @Test
    public void noAnagramsOnNullInput() {
        AnagramFinder finder = new AnagramFinder(AVAILABLE_WORDS);

        assertThat(finder.findTwoWordAnagramsFor(null)).isEmpty();
    }

    @Test
    public void noAnagramsOnEmptyInput() {
        AnagramFinder finder = new AnagramFinder(AVAILABLE_WORDS);

        assertThat(finder.findTwoWordAnagramsFor("")).isEmpty();
    }
}