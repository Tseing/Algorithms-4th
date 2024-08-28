import stdio.StdIn;
import stdio.StdOut;

public class QuickFindUF {

    private int[] id; // 触点的分量 id（索引为触点）
    private int count; // 分量的数量，或者说类别数量

    public QuickFindUF(int N) {
        // 构造函数，初始化所有触点
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
    }

    public int count() {
        return count;
    }

    // 用于判断两个触点是否连接，也就是查找其归属（类别）是否相同
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // 查找给点触点的归属，直接访问数组，速度快 quickFind
    public int find(int p) {
        return id[p];
    }

    // 将两点连接，也就是归属到同一个类别下
    public void union(int p, int q) {
        int pID = find(p);
        int qID = find(q);

        // 如果已经是同一类别下，不用额外操作
        if (pID == qID)
            return;

        // 否则将 pID 从属的触点都改为 qID，操作速度慢
        for (int i = 0; i < id.length; i++)
            if (id[i] == pID)
                id[i] = qID;

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