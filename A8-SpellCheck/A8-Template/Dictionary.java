/**
 * A small interface for a collection of valid words.
 *
 * Part 1 of the assignment asks you to compare different backing data
 * structures that implement this same behavior.
 */
public interface Dictionary {
  /**
   * Add a word to the dictionary.
   *
   * @param word the word to add
   */
  void add(String word);

  /**
   * Check whether a word is in the dictionary.
   *
   * @param word the word to look up
   * @return true when the word is present
   */
  boolean contains(String word);

  /**
   * Report how many distinct words are stored.
   *
   * @return the number of words in the dictionary
   */
  int size();
}
