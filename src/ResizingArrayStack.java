import java.util.Iterator;

// 欲实现迭代必须继承 Iterable 的接口
public class ResizingArrayStack<Item> implements Iterable<Item> {
    // 栈元素，注意泛型数组的创建方法
    private Item[] a;
    // 元素数量
    private int n;

    public ResizingArrayStack(int size) {
        a = (Item[]) new Object[size];
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private void resize(int max) {
        // 将栈移动到一个大小为 max 的新数组
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < n; i++)
            temp[i] = a[i];
        a = temp;
    }

    public void push(Item item) {
        // 将元素插入栈顶
        if (n == a.length)
            resize(2 * a.length);
        a[n++] = item;
    }

    public Item pop() {
        // 从栈顶删除元素
        Item item = a[--n];
        // 删除存储的对象
        a[n] = null;
        // 实际元素数量为数组大小的 1/4 时，将数组调整为目前的 1/2 （留下一半空间）
        if (n > 0 && n == a.length / 4)
            resize(a.length / 2);

        return item;
    }

    // Iterable 接口需要实现 iterator() 方法
    // 该方法返回一个迭代器
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    // 迭代器可以在类中定义，必须继承 Iterator 接口
    // Iterator 接口定义了 hasNext() next() remove() 三种方法
    private class ReverseArrayIterator implements Iterator<Item> {

        private int i = n;

        public boolean hasNext() {
            return i > 0;
        }

        // 嵌套类可以访问上级类的实例变量，此处即 this.a
        public Item next() {
            return a[--i];
        }

        public void remove() {
        }
    }

    // 测试用例
    public static void main(String[] args) {
        ResizingArrayStack<String> s;
        s = new ResizingArrayStack<String>(100);
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