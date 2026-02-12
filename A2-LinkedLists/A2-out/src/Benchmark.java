import java.lang.reflect.Method;

public class Benchmark {
    private static final int[] SIZES = {1000, 5000, 20000};
    private static final int REPS = 200;

    public static void main(String[] args) {
        System.out.println("Linked List Benchmark: Node-Based vs Index-Based");
        System.out.println("Each timing is an average over " + REPS + " add+remove pairs.\n");

        for (int n : SIZES) {
            SLL<Integer> list = buildList(n);
            int midIndex = n / 2;
            NodeSL<Integer> midNode = nodeAt(list, midIndex);

            Method addIndex = findAddIndexMethod(list);

            // Warmup
            runNodePair(list, midNode, 10);
            if (addIndex != null) {
                runIndexPair(list, addIndex, midIndex + 1, 10);
            }

            long nodeNs = timeNodePair(list, midNode, REPS);
            String nodeMsg = String.format("node addAfter/removeAfter: %s ns/op", fmt(nodeNs));

            String indexMsg;
            if (addIndex != null) {
                long idxNs = timeIndexPair(list, addIndex, midIndex + 1, REPS);
                indexMsg = String.format("index add/remove: %s ns/op", fmt(idxNs));
            } else {
                indexMsg = "index add/remove: N/A (no add(int, T) found)";
            }

            System.out.println("n = " + n);
            System.out.println("  " + nodeMsg);
            System.out.println("  " + indexMsg);
            System.out.println();
        }
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
