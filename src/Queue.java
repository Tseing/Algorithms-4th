import java.util.Iterator;

public class Queue<Item> implements Iterable<Item> {
    private Node first; // 指向最早添加的节点的链接
    private Node last; // 指向最近添加的节点的链接
    private int n; // 队列中的元素数量

    private class Node {
        // 定义节点的嵌套类
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        // 初始化时 first 和 last 都为 null
        return first == null; // 也可以 return n == 0
    }

    public int size() {
        return n;
    }

    public void enqueue(Item item) {
        // 向表尾添加元素
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        // 如果该元素入队前队列为空，那么 first 就要指向 last
        if (isEmpty())
            first = last;
        else
            oldlast.next = last;
        n++;
    }

    public Item dequeue() {
        // 从表头删除元素
        Item item = first.item;
        // 只有一个元素时 first 和 last 都指向同一元素且 next 为 null
        first = first.next;
        // 如果推出 first 后为空，那么 last 也要指向 null
        if (isEmpty())
            last = null;
        n--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new QuequeIterator();
    }

    private class QuequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
        }
    }

    public static void main(String[] args) {
        Queue<String> q = new Queue<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                q.enqueue(item);
            else if (!q.isEmpty())
                StdOut.print(q.dequeue() + " ");
        }
        StdOut.println("(" + q.size() + " left on queue)");
    }
}
