# Algorithm 4th

| Chapter | Class                | Description                                  | Test File  |
| ------- | -------------------- | -------------------------------------------- | ---------- |
| 1.1     | ResizingArrayStack   | 下压（LIFO）栈（能够动态调整数组大小的实现） | tobe.txt   |
| 1.2     | Stack                | 下压堆栈（链表实现）                         | tobe.txt   |
| 1.3     | Queue                | 先进先出队列                                 | tobe.txt   |
| 1.4     | Bag                  | 背包                                         | tobe.txt   |
| 1.5     | QuickFindUF          | union-find 的实现                            | tinyUF.txt |
| 1.5     | QuickUnionUF         | union-find 的实现                            | tinyUF.txt |
| 1.5     | WeightedQuickUnionUF | union-find 的实现（加权 quick-union 算法）   | tinyUF.txt |

## Compile & Run

以 `QuickUnionUF.java` 为例

```shell
$ pwd
~/algorithm-4th
$ javac -sourcepath src src/QuickUnionUF.java -d bin
$ java -cp bin QuickUnionUF < tests/tinyUF.txt
```
