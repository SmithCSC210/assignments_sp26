import java.util.ArrayList;

/**
 * Dictionary implementation backed by an ArrayList.
 */
public class ListDictionary implements Dictionary {
  /**
  * Here, words is a collection of Strings stored in an ArrayList
  * New words are added to the end of the arraylist
  */
  private ArrayList<String> words;

  /**
   * Create an empty list-backed dictionary.
   */
  public ListDictionary() {
    this.words = new ArrayList<>();
  }

  /**
   * Add a word if it is not already present.
   *
   * @param word the word to add
   */
  public void add(String word) {
    if (!this.words.contains(word)) {
      this.words.add(word);
    }
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
