import java.util.Iterator;

import stdio.StdIn;
import stdio.StdOut;

public class Stack<Item> implements Iterable<Item> {
    private Node first; // 栈顶（最近添加的元素）
    private int n; // 元素数量

    private class Node {
        // 定义节点的嵌套类
        Item item;
        Node next;

        // 不定义构造函数，new Node 时默认为 null
    }

    // 不定义构造函数 new Stack 时默认使用以下的构造方式
    // public Stack(){
    //     first = null;
    //     n = 0;
    // }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public void push(Item item) {
        // 向栈顶添加元素
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    public Item pop() {
        // 从栈顶删除元素
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<Item> {
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
        Stack<String> s = new Stack<String>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                s.push(item);
            else if (!s.isEmpty())
                StdOut.print(s.pop() + " ");
        }
        StdOut.println("(" + s.size() + " left on stack)");
    }
}
