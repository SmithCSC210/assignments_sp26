import java.lang.reflect.Method;

/**
 * Simple benchmarking harness to compare index-based and node-based operations
 * on a singly linked list.
 */
public class Benchmark {
    private static final int[] SIZES = {10000, 50000, 200000};
    private static final int REPS = 500;
    private static final int TRIALS = 1;

    /**
     * Runs the benchmark and prints timing results to stdout.
     *
     * @param args ignored
     */
    public static void main(String[] args) {
        System.out.println("Linked List Benchmark: Node-Based vs Index-Based");
        System.out.println("Each timing is an average over " + REPS + " add+remove pairs.\n");
        System.out.println("Note: sub-100 ns timings are noisy; focus on overall trends.\n");

        // One dry run to warm up the JVM/JIT
        runOnce(2000, 50, 1, false);

        for (int n : SIZES) {
            runOnce(n, REPS, TRIALS, true);
        }
    }

    private static void runOnce(int n, int reps, int trials, boolean print) {
        long[] nodeSamples = new long[trials];
        long[] indexSamples = new long[trials];

        for (int t = 0; t < trials; t++) {
            SLL<Integer> list = buildList(n);
            int midIndex = n / 2;
            NodeSL<Integer> midNode = nodeAt(list, midIndex);

            Method addIndex = findAddIndexMethod(list);

            // Warmup
            runNodePair(list, midNode, 50);
            if (addIndex != null) {
                runIndexPair(list, addIndex, midIndex, 50);
            }

            nodeSamples[t] = timeNodePair(list, midNode, reps);

            if (addIndex != null) {
                try {
                    indexSamples[t] = timeIndexPair(list, addIndex, midIndex, reps);
                } catch (RuntimeException e) {
                    indexSamples[t] = -1;
                }
            } else {
                indexSamples[t] = -1;
            }
        }

        long nodeNs = medianPositive(nodeSamples);
        long indexNs = medianPositive(indexSamples);

        String nodeMsg = (nodeNs < 0) ? "node addAfter/removeAfter: ERROR" :
                String.format("node addAfter/removeAfter: %s ns/op", fmt(nodeNs));

        String indexMsg = (indexNs < 0) ? "index add/remove: ERROR" :
                String.format("index add/remove: %s ns/op", fmt(indexNs));

        if (print) {
            System.out.println("n = " + n);
            System.out.println("  " + nodeMsg);
            System.out.println("  " + indexMsg);
            System.out.println();
        }
    }

    private static long medianPositive(long[] values) {
        long[] copy = values.clone();
        java.util.Arrays.sort(copy);
        int count = 0;
        for (long v : copy) {
            if (v >= 0) count++;
        }
        if (count == 0) return -1;
        int mid = count / 2;
        int idx = 0;
        for (long v : copy) {
            if (v >= 0) {
                if (idx == mid) return v;
                idx++;
            }
        }
        return -1;
    }

    private static SLL<Integer> buildList(int n) {
        SLL<Integer> list = new SLL<Integer>();
        for (int i = 0; i < n; i++) {
            list.addLast(i);
        }
        return list;
    }

    private static NodeSL<Integer> nodeAt(SLL<Integer> list, int index) {
        NodeSL<Integer> cur = list.getHead();
        for (int i = 0; i < index; i++) {
            if (cur == null) return null;
            cur = cur.getNext();
        }
        return cur;
    }

    private static long timeNodePair(SLL<Integer> list, NodeSL<Integer> here, int reps) {
        long start = System.nanoTime();
        runNodePair(list, here, reps);
        long end = System.nanoTime();
        return (end - start) / reps;
    }

    private static void runNodePair(SLL<Integer> list, NodeSL<Integer> here, int reps) {
        for (int i = 0; i < reps; i++) {
            list.addAfter(here, -1);
            list.removeAfter(here);
        }
    }

    private static long timeIndexPair(SLL<Integer> list, Method addIndex, int index, int reps) {
        long start = System.nanoTime();
        runIndexPair(list, addIndex, index, reps);
        long end = System.nanoTime();
        return (end - start) / reps;
    }

    private static void runIndexPair(SLL<Integer> list, Method addIndex, int index, int reps) {
        for (int i = 0; i < reps; i++) {
            try {
                addIndex.invoke(list, index, -1);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            list.remove(index);
        }
    }

    private static Method findAddIndexMethod(Object list) {
        for (Method m : list.getClass().getMethods()) {
            if (!m.getName().equals("add")) continue;
            Class<?>[] ps = m.getParameterTypes();
            if (ps.length == 2 && ps[0] == int.class) {
                return m;
            }
        }
        return null;
    }

    private static String fmt(long nsPerOp) {
        return String.format("%,d", nsPerOp);
    }
}
