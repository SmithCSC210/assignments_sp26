import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Benchmark driver for comparing dictionary implementations.
 */
public class Timer {
  private static final String DEFAULT_FILENAME = "words.txt";
  private static final int REPEATS = 200;

  /**
   * Create a benchmark driver object.
   */
  public Timer() {
  }

  /**
   * Run the benchmark for the provided dictionary file.
   *
   * @param args optional command-line arguments; the first argument may be a
   *     dictionary filename
   */
  public static void main(String[] args) {
    String filename = (args.length > 0) ? args[0] : DEFAULT_FILENAME;
    List<String> words = readWords(filename);

    Dictionary listDictionary = new ListDictionary();
    Dictionary hashSetDictionary = new HashSetDictionary();

    loadDictionary(listDictionary, words);
    loadDictionary(hashSetDictionary, words);

    long listTime = benchmarkContains(listDictionary, words);
    long hashSetTime = benchmarkContains(hashSetDictionary, words);

    System.out.println("contains() benchmark using " + words.size() + " words");
    System.out.println("ListDictionary: " + listTime + " ns");
    System.out.println("HashSetDictionary: " + hashSetTime + " ns");
  }

  /**
   * Read all words from a file into a list.
   *
   * @param filename the file to read
   * @return the words found in the file
   */
  public static List<String> readWords(String filename) {
    ArrayList<String> words = new ArrayList<>();

    try (Scanner scanner = new Scanner(new File(filename))) {
      while (scanner.hasNext()) {
        words.add(clean(scanner.next()));
      }
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Cannot locate file: " + filename, e);
    }

    return words;
  }

  /**
   * Add all words in a list to a dictionary.
   *
   * @param dictionary the dictionary to populate
   * @param words the words to add
   */
  public static void loadDictionary(Dictionary dictionary, List<String> words) {
    for (String word : words) {
      dictionary.add(word);
    }
  }

  /**
   * Time repeated membership checks on a dictionary.
   *
   * @param dictionary the dictionary to query
   * @param words the words used as queries
   * @return elapsed time in nanoseconds
   */
  public static long benchmarkContains(Dictionary dictionary, List<String> words) {
    long start = System.nanoTime();

    for (int repeat = 0; repeat < REPEATS; repeat++) {
      for (String word : words) {
        dictionary.contains(word);
      }
    }

    return System.nanoTime() - start;
  }

  /**
   * Normalize a word for dictionary use.
   *
   * @param word the word to normalize
   * @return a lowercase word with punctuation removed
   */
  public static String clean(String word) {
    return word.toLowerCase().replaceAll("[^\\sa-zA-Z0-9]", "");
  }
}
