import java.util.HashSet;

/**
 * Dictionary implementation backed by a HashSet.
 */
public class HashSetDictionary implements Dictionary {
  /**
  * HashSet uses hashing to store and access the elements that are added
  */
  private HashSet<String> words;

  /**
   * Create an empty hash-set-backed dictionary.
   */
  public HashSetDictionary() {
    this.words = new HashSet<>();
  }

  /**
   * Add a word to the dictionary.
   *
   * @param word the word to add
   */
  public void add(String word) {
    this.words.add(word);
  }

  /**
   * Check whether the dictionary contains a word.
   *
   * @param word the word to look up
   * @return true if the word is present
   */
  public boolean contains(String word) {
    return this.words.contains(word);
  }

  /**
   * Report the number of distinct words stored.
   *
   * @return the dictionary size
   */
  public int size() {
    return this.words.size();
  }
}
