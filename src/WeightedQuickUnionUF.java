import stdio.StdIn;
import stdio.StdOut;

public class WeightedQuickUnionUF {
    private int[] id; // 当前节点父节点
    private int[] sz; // 根节点所辖节点的数量
    private int count;

    public WeightedQuickUnionUF(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
        // sz 全部初始化为 1
        sz = new int[N];
        for (int i = 0; i < N; i++)
            sz[i] = 1;
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // 随父节点寻找根节点
    public int find(int p) {
        while (p != id[p])
            p = id[p];
        return p;
    }

    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j)
            return;
        // WeightedQuickUnion 的改进是永远只将小树接到大树上
        // 使得到的树展得更平坦，有效减小深度
        if (sz[i] < sz[j]) {
            // 接上小树
            id[i] = j;
            // 更新树节点的数量
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
        // 树（类别）的数量减少
        count--;
    }

    public static void main(String[] args) {
        // 从 StdIn 输入测试用例 tinyUF.txt
        int N = StdIn.readInt();
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
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
