import java.util.Set;

/**
 * Operations that should be supported by a spelling dictionary.
 */
public interface SpellingOperations {
  /**
   * Check whether a word appears in the dictionary.
   *
   * @param query the word to check
   * @return true if the query word is in the dictionary
   */
  boolean containsWord(String query);

  /**
   * Generate valid suggestions that are one edit away from a query word.
   *
   * @param query the word to check
   * @return a set of all valid words that are one edit away from the query
   */
  Set<String> nearMisses(String query);
}
