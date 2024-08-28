import stdio.StdIn;
import stdio.StdOut;

public class QuickUnionUF {

    private int[] id;
    private int count;

    // QuickUnionUF 和 QuickFindUF 基本相同
    // 但 QuickFindUF 在 id 中存放的是同类节点而不是类别
    // 所以在 find() 和 union() 方法有区别
    public QuickUnionUF(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // 查找给点触点的归属，不同归属的节点有不同的根，所以寻找根
    public int find(int p) {
        // 反复使用 p 找到的值作为索引查找，等价于 id[id[id[p]]]...
        // 直至找到根节点，因为根节点指向自己，有条件 p = id[p]
        // 寻找速度慢
        while (p != id[p])
            p = id[p];
        return p;
    }

    public void union(int p, int q) {
        // 需要合并两种类别，直接将其中一个根接向另一个根节点，合并操作速度快
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot)
            return;
        id[pRoot] = id[qRoot];

        count--;
    }

    public static void main(String[] args) {
        // 从 StdIn 输入测试用例 tinyUF.txt
        int N = StdIn.readInt();
        QuickFindUF uf = new QuickFindUF(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q))
                continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }

}