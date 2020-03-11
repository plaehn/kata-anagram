import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BagOfLettersTest {

    @Test
    public void fromStringReturnsEmptyBagOnNullString() {
        BagOfLetters bagOfLetters = BagOfLetters.fromString(null);

        assertThat(bagOfLetters.isEmpty()).isTrue();
    }

    @Test
    public void fromStringReturnsEmptyBagOnBlankString() {
        BagOfLetters bagOfLetters = BagOfLetters.fromString(" \t \r");

        assertThat(bagOfLetters.isEmpty()).isTrue();
    }

    @Test
    public void fromStringRemovesWhitespace() {
        BagOfLetters bagOfLetters = BagOfLetters.fromString(" ab\tc\n");

        assertThat(bagOfLetters.getCharacterSet()).containsExactlyInAnyOrder('a', 'b', 'c');
    }
}