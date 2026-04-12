import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Run the spell checker from the command line.
 */
public class SpellChecker {
  private static final String DEFAULT_DICTIONARY = "words.txt";
  private WordValidation validator;

  /**
   * Create a spell checker object.
   *
   * This constructor uses the default dictionary file.
   */
  public SpellChecker() {
    this(DEFAULT_DICTIONARY);
  }

  /**
   * Create a spell checker object using a particular dictionary file.
   *
   * This constructor is optional, but many students find it convenient.
   *
   * @param filename the dictionary file to use
   */
  public SpellChecker(String filename) {
    this.validator = new WordValidation(filename);
  }

  /**
   * Check one word and print the result.
   *
   * @param word the word to check
   */
  public void checkWord(String word) {
    if (this.validator.containsWord(word)) {
      System.out.println("'" + word + "' is spelled correctly.");
    } else {
      System.out.println("Not found: " + word);
      System.out.println("  Suggestions: " + checkSpelling(word).get(word));
    }
  }

  /**
   * Check the spelling of one word and return any suggestions.
   *
   * @param query the word to check
   * @return a map from the misspelled query to its suggestions
   */
  public Map<String, HashSet<String>> checkSpelling(String query) {
    HashSet<String> suggestions = new HashSet<>(this.validator.nearMisses(query));
    Map<String, HashSet<String>> result = new HashMap<>();

    if (!this.validator.containsWord(query)) {
      result.put(query, suggestions);
    }

    return result;
  }

  /**
   * Launch the spell checker in argument mode or standard-input mode.
   *
   * A common approach is to make argument mode work first, then extend the
   * program so it can read many words from {@code System.in}.
   *
   * @param args command-line arguments to spell-check
   */
  public static void main(String[] args) {

    // This code will analyze any words passed as command lines
    SpellChecker checker = new SpellChecker();

    for (String word : args) {
      checker.checkWord(word);
    }
  }
}
