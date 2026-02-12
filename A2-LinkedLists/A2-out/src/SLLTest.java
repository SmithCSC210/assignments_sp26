import org.junit.Test;
import static org.junit.Assert.*;

public class SLLTest {

    // Define data that will be used to test the SLL class
    public static final String[] abc = { "A", "B", "C" };
    public static final String[] a = { "A" };
    public static final String[] empty = {};
    public static final String[] ab = { "A", "B" };
    public static final String[] cba = { "C", "B", "A" };
    public static final String[] ba = { "B", "A" };
    public static final String[] ac = { "A", "C" };

    /* Method to create an SLL via addFirst, returns SLL */
    public static <T> SLL<T> makeSLL(T[] arr) {
        SLL<T> list = new SLL<T>();
        int i = arr.length;
        while (i > 0) {
            i--;
            list.addFirst(arr[i]);
        }
        return list;
    }

    /* Method to create an SLL via index-based add, returns SLL */
    public static <T> SLL<T> makeSLLByIndex(T[] arr) {
        SLL<T> list = new SLL<T>();
        for (int i = 0; i < arr.length; i++) {
            list.add(i, arr[i]);
        }
        return list;
    }

    /* Method to check an SLL, returns String of SLL elements */
    public static <T> String verifySLL(SLL<T> list, T[] values) {
        String result = "";
        NodeSL<T> here = list.getHead();
        for (int i = 0; i < values.length; i++) {
            if (!((here != null) && (values[i] == here.getData()))) {
                result += "element " + i + "; ";
            }
            if (here == null) {
                break; // list unexpectedly ended early
            }
            here = here.getNext();
        }
        if (here != null) {
            result += "tail";
        }
        if (!result.equals("")) {
            result = " problems at " + result;
        }
        return result;
    }

    @Test
    public void check_methods() {
        SLL<String> list = new SLL<>();
        assertTrue("empty head is null", null == list.getHead());
        assertTrue("empty tail is null", null == list.getTail());
        assertTrue("empty list is empty", list.isEmpty());
    }

    @Test
    public void test_addFirst_1() {
        SLL<String> list = new SLL<>();
        list.addFirst("A");

        assertTrue("list of 1 isn't empty", !list.isEmpty());
        assertTrue("same head and tail of singleton", list.getHead() == list.getTail());
        assertTrue("data correct", list.getHead().getData().equals("A"));
        assertTrue("tail's next is null", null == list.getTail().getNext());
    }

    @Test
    public void test_addFirst_2() {
        SLL<String> list = new SLL<>();

        list.addFirst("A");
        list.addFirst("B");
        assertTrue("list of 2 isn't empty", !list.isEmpty());
        assertTrue("second node is tail", list.getHead().getNext() == list.getTail());
        assertTrue("first element is B", list.getHead().getData().equals("B"));
        assertTrue("second element is A", list.getHead().getNext().getData().equals("A"));
        assertTrue("tail's next is null", null == list.getTail().getNext());
    }

    @Test
    public void test_addFirst_3() {
        SLL<String> list = new SLL<>();
        list.addFirst("A");
        list.addFirst("B");
        list.addFirst("C");

        assertTrue("list of 3 isn't empty", !list.isEmpty());
        assertTrue("third node is tail", list.getHead().getNext().getNext() == list.getTail());
        assertTrue("first element is C", list.getHead().getData().equals("C"));
        assertTrue("second element is B", list.getHead().getNext().getData().equals("B"));
        assertTrue("third element is A", list.getHead().getNext().getNext().getData().equals("A"));
        assertTrue("tail's next is null", null == list.getTail().getNext());

        String s = verifySLL(list, cba);
        assertTrue("3-list contents " + s, s.equals(""));
    }

    @Test
    public void test_toString() {
        SLL<String> list = new SLL<>();
        assertTrue("empty list is []", list.toString().equals("[]"));
        list.addFirst("A");
        assertTrue("list [A]", list.toString().equals("[A]"));
        list.addFirst("B");
        assertTrue("list [B, A]", list.toString().equals("[B, A]"));
        list.addFirst("C");
        assertTrue("list [C, B, A]", list.toString().equals("[C, B, A]"));
    }

    @Test
    public void test_removeFirst() {
        SLL<String> list = makeSLL(cba);
        assertTrue("remove C", list.removeFirst().equals("C"));
        String s = verifySLL(list, ba);
        assertTrue("removeFirst --> BA " + s, s.equals(""));
        assertTrue("remove B", list.removeFirst().equals("B"));
        s = verifySLL(list, a);
        assertTrue("removeFirst --> A " + s, s.equals(""));
        assertTrue("remove A", list.removeFirst().equals("A"));
        s = verifySLL(list, empty);
        assertTrue("removeFirst -> empty " + s, s.equals(""));
    }

    @Test
    public void test_addLast() {
        SLL<String> list = new SLL<String>();
        list.addLast("A");
        String s = verifySLL(list, a);
        assertTrue("addLast -> A " + s, s.equals(""));
        list.addLast("B");
        s = verifySLL(list, ab);
        assertTrue("addLast -> AB " + s, s.equals(""));
        list.addLast("C");
        s = verifySLL(list, abc);
        assertTrue("addLast -> ABC " + s, s.equals(""));
    }

    @Test
    public void test_removeLast() {
        SLL<String> list = makeSLL(abc);
        assertTrue("removeLast C", list.removeLast().equals("C"));
        String s = verifySLL(list, ab);
        assertTrue("removeLast -> AB" + s, s.equals(""));
        assertTrue("removeLast B", list.removeLast().equals("B"));
        s = verifySLL(list, a);
        assertTrue("removeLast -> A" + s, s.equals(""));
        assertTrue("removeLast A", list.removeLast().equals("A"));
        s = verifySLL(list, empty);
        assertTrue("removeLast -> empty" + s, s.equals(""));
    }

    @Test
    public void test_size() {
        SLL<String> list = new SLL<String>();
        assertTrue("size of empty", 0 == list.size());
        list = makeSLL(a);
        assertTrue("size of A", 1 == list.size());
        list = makeSLL(ba);
        assertTrue("size of BA", 2 == list.size());
        list = makeSLL(cba);
        assertTrue("size of CBA", 3 == list.size());
    }

    @Test
    public void test_addAfter() {
        SLL<String> list = makeSLL(a);
        list.addAfter(list.getHead(), "B");
        String s = verifySLL(list, ab);
        assertTrue("A.addAfter(A,B)" + s, s.equals(""));
        list.addAfter(list.getTail(), "C");
        s = verifySLL(list, abc);
        assertTrue("AB.addAfter(B,C)" + s, s.equals(""));
        list.addAfter(list.getHead().getNext(), "D");
        s = verifySLL(list, new String[] { "A", "D", "B", "C" });
        assertTrue("ABC.addAfter(B,D)" + s, s.equals(""));
    }

    @Test
    public void test_addAfter_null() {
        SLL<String> list = makeSLL(a);
        list.addAfter(null, "Z");
        String s = verifySLL(list, new String[] { "Z", "A" });
        assertTrue("A.addAfter(null,Z) -> ZA" + s, s.equals(""));
    }

    @Test
    public void test_removeAfter() {
        SLL<String> list = makeSLL(abc);
        assertTrue("ABC after B", list.removeAfter(list.getHead().getNext()).equals("C"));
        String s = verifySLL(list, ab);
        assertTrue("ABC removeAfter B -> AB" + s, s.equals(""));
        assertTrue("AB after A", list.removeAfter(list.getHead()).equals("B"));
        verifySLL(list, a);
        assertTrue("AB removeAfter A -> A" + s, s.equals(""));
        assertTrue("A after null", list.removeAfter(null).equals("A"));
        s = verifySLL(list, empty);
        assertTrue("A removeAfter null -> empty" + s, s.equals(""));
    }

    @Test
    public void test_index_add_get_set_remove() {
        SLL<String> list = makeSLLByIndex(abc);
        String s = verifySLL(list, abc);
        assertTrue("add by index builds ABC" + s, s.equals(""));

        assertTrue("get(0)=A", list.get(0).equals("A"));
        assertTrue("get(2)=C", list.get(2).equals("C"));
        list.set(1, "X");
        s = verifySLL(list, new String[] { "A", "X", "C" });
        assertTrue("set(1,X) -> AXC" + s, s.equals(""));

        assertTrue("remove(1)=X", list.remove(1).equals("X"));
        s = verifySLL(list, ac);
        assertTrue("remove(1) -> AC" + s, s.equals(""));
    }

    @Test
    public void test_copy_constructor() {
        SLL<String> list = makeSLL(abc);
        SLL<String> copy = new SLL<String>(list);
        String s = verifySLL(copy, abc);
        assertTrue("copy ABC" + s, s.equals(""));
        assertTrue("copy not same head", copy.getHead() != list.getHead());
    }

    @Test
    public void test_exceptions_index_ops() {
        SLL<String> list = makeSLL(abc);
        assertThrows("get invalid index",
                IndexOutOfBoundsException.class,
                () -> list.get(-1));
        assertThrows("get invalid index",
                IndexOutOfBoundsException.class,
                () -> list.get(3));
        assertThrows("set invalid index",
                IndexOutOfBoundsException.class,
                () -> list.set(3, "Z"));
        assertThrows("add invalid index",
                IndexOutOfBoundsException.class,
                () -> list.add(5, "Z"));
        assertThrows("remove invalid index",
                IndexOutOfBoundsException.class,
                () -> list.remove(3));
    }

    @Test
    public void test_exceptions_empty_removals() {
        SLL<String> list = new SLL<>();
        assertThrows("removeFirst on empty",
                IllegalStateException.class,
                list::removeFirst);
        assertThrows("removeLast on empty",
                IllegalStateException.class,
                list::removeLast);
        assertThrows("removeAfter on empty",
                IllegalStateException.class,
                () -> list.removeAfter(null));
    }
}
