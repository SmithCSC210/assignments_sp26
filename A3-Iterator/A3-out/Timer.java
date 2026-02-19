/**
 * Simple benchmark for comparing copy vs transfer split operations.
 *
 * Run:
 *   javac $(ls *.java | grep -v 'Test\\.java$')
 *   java Timer
 */
public class Timer {
    private static final int[] SIZES = { 1000, 5000, 20000 };
    private static final int REPS = 500;
    private static final int WARMUP = 200;

    public static void main(String[] args) {
        System.out.println("Copy vs Transfer Split (lightweight benchmark)");
        System.out.println("Each timing is an average over " + REPS + " ops.");
        System.out.println("Note: sub-100 ns timings are noisy; focus on trends.\n");

        for (int n : SIZES) {
            System.out.println("n = " + n);

            DynamicArray<Integer> darr = buildDynamicArray(n);
            SLL<Integer> sll = buildSLL(n);

            warmup(darr, sll);

            long darrGet = timeGet(darr, REPS);
            long sllGet = timeGet(sll, REPS);
            long darrAdd0 = timeAddRemoveZero(darr, REPS);
            long sllAdd0 = timeAddRemoveZero(sll, REPS);
            long darrCopy = timeSplitCopy(darr, REPS);
            long sllCopy = timeSplitCopy(sll, REPS);
            long darrTransfer = timeSplitTransfer(darr, REPS);
            long sllTransfer = timeSplitTransfer(sll, REPS);

            System.out.println("  get(mid):          DynamicArray " + darrGet + " ns/op, SLL " + sllGet + " ns/op");
            System.out.println("  add+remove(0):     DynamicArray " + darrAdd0 + " ns/op, SLL " + sllAdd0 + " ns/op");
            System.out.println("  splitCopy(index):  DynamicArray " + darrCopy + " ns/op, SLL " + sllCopy + " ns/op");
            System.out.println("  splitTransfer(idx):DynamicArray " + darrTransfer + " ns/op, SLL " + sllTransfer + " ns/op");
            System.out.println();
        }
    }

    private static DynamicArray<Integer> buildDynamicArray(int n) {
        DynamicArray<Integer> arr = new DynamicArray<>();
        for (int i = 0; i < n; i++) {
            arr.add(i, i);
        }
        return arr;
    }

    private static SLL<Integer> buildSLL(int n) {
        SLL<Integer> list = new SLL<>();
        for (int i = 0; i < n; i++) {
            list.add(i, i);
        }
        return list;
    }

    private static void warmup(DynamicArray<Integer> arr, SLL<Integer> list) {
        timeGet(arr, WARMUP);
        timeGet(list, WARMUP);
        timeAddRemoveZero(arr, WARMUP);
        timeAddRemoveZero(list, WARMUP);
        timeSplitCopy(arr, WARMUP);
        timeSplitCopy(list, WARMUP);
        timeSplitTransfer(arr, WARMUP);
        timeSplitTransfer(list, WARMUP);
    }

    private static long timeGet(DynamicArray<Integer> arr, int reps) {
        int idx = arr.size() / 2;
        long sink = 0;
        long start = System.nanoTime();
        for (int i = 0; i < reps; i++) {
            sink += arr.get(idx);
        }
        long elapsed = System.nanoTime() - start;
        if (sink == 42) {
            System.out.print("");
        }
        return elapsed / reps;
    }

    private static long timeGet(SLL<Integer> list, int reps) {
        int idx = list.size() / 2;
        long sink = 0;
        long start = System.nanoTime();
        for (int i = 0; i < reps; i++) {
            sink += list.get(idx);
        }
        long elapsed = System.nanoTime() - start;
        if (sink == 42) {
            System.out.print("");
        }
        return elapsed / reps;
    }

    private static long timeAddRemoveZero(DynamicArray<Integer> arr, int reps) {
        long start = System.nanoTime();
        for (int i = 0; i < reps; i++) {
            arr.add(0, -1);
            arr.remove(0);
        }
        long elapsed = System.nanoTime() - start;
        return elapsed / reps;
    }

    private static long timeAddRemoveZero(SLL<Integer> list, int reps) {
        long start = System.nanoTime();
        for (int i = 0; i < reps; i++) {
            list.add(0, -1);
            list.remove(0);
        }
        long elapsed = System.nanoTime() - start;
        return elapsed / reps;
    }

    private static long timeSplitCopy(DynamicArray<Integer> arr, int reps) {
        int index = arr.size() / 2;
        long sink = 0;
        long start = System.nanoTime();
        for (int i = 0; i < reps; i++) {
            DynamicArray<Integer> out = arr.splitCopy(index);
            sink += out.size();
        }
        long elapsed = System.nanoTime() - start;
        if (sink == 42) {
            System.out.print("");
        }
        return elapsed / reps;
    }

    private static long timeSplitCopy(SLL<Integer> list, int reps) {
        int index = list.size() / 2;
        long sink = 0;
        long start = System.nanoTime();
        for (int i = 0; i < reps; i++) {
            SLL<Integer> out = list.splitCopy(index);
            sink += out.size();
        }
        long elapsed = System.nanoTime() - start;
        if (sink == 42) {
            System.out.print("");
        }
        return elapsed / reps;
    }

    private static long timeSplitTransfer(DynamicArray<Integer> arr, int reps) {
        int index = arr.size() / 2;
        long sink = 0;
        long start = System.nanoTime();
        for (int i = 0; i < reps; i++) {
            DynamicArray<Integer> fresh = buildDynamicArray(arr.size());
            DynamicArray<Integer> out = fresh.splitTransfer(index);
            sink += out.size();
        }
        long elapsed = System.nanoTime() - start;
        if (sink == 42) {
            System.out.print("");
        }
        return elapsed / reps;
    }

    private static long timeSplitTransfer(SLL<Integer> list, int reps) {
        int index = list.size() / 2;
        long sink = 0;
        long start = System.nanoTime();
        for (int i = 0; i < reps; i++) {
            SLL<Integer> fresh = buildSLL(list.size());
            SLL<Integer> out = fresh.splitTransfer(index);
            sink += out.size();
        }
        long elapsed = System.nanoTime() - start;
        if (sink == 42) {
            System.out.print("");
        }
        return elapsed / reps;
    }
}
