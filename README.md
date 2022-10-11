# 数据结构
## 数组

数组使用最多的解法：双指针法、快慢指针法。

| Leetcode题号 | 题目 | 难度 | 掌握程度 |
| :----: | :----: | :----: | :----: |
| [88](https://leetcode-cn.com/problems/merge-sorted-array/) | 合并两个有序数组 | 简单 | 一般 |
| [26](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/) | 删除有序数组中的重复项 | 简单 | 较好 |
| [27](https://leetcode-cn.com/problems/remove-element/) | 移除元素 | 简单 | 较好 |
| [283](https://leetcode-cn.com/problems/move-zeroes/) | 移动零 | 简单 | 较好 |
| [844](https://leetcode-cn.com/problems/backspace-string-compare/) | 比较含退格的字符串 | 简单 | 较差 |
| [977](https://leetcode-cn.com/problems/squares-of-a-sorted-array/) | 有序数组的平方 | 简单 | 一般 |
| [209](https://leetcode-cn.com/problems/minimum-size-subarray-sum/) | 长度最小的子数组 | 中等 | 一般 |
| [59](https://leetcode-cn.com/problems/spiral-matrix-ii/) | 螺旋矩阵 II | 中等 | 一般 |

## 链表

虚拟链表头，可以简化链表头的处理过程。

赋值引用变量时，避免变量丢失，应先暂存再赋值。

| Leetcode题号 | 题目 | 难度 | 掌握程度 |
| :----: | :----: | :----: | :----: |
| [21](https://leetcode-cn.com/problems/merge-two-sorted-lists/) | 合并两个有序链表 | 简单 | 较好 |
| [160](https://leetcode-cn.com/problems/intersection-of-two-linked-lists/) | 相交链表 | 简单 | 一般 |
| [25](https://leetcode-cn.com/problems/reverse-nodes-in-k-group/) | K 个一组翻转链表 | 困难 | <font color=red>差</font> |
| [141](https://leetcode-cn.com/problems/linked-list-cycle/) | 环形链表 | 简单 | 一般 |
| [142](https://leetcode-cn.com/problems/linked-list-cycle-ii/) | 环形链表 II | 中等 | 一般 |
| [206](https://leetcode-cn.com/problems/reverse-linked-list/) | 反转链表 | 简单 | 一般 |
| [203](https://leetcode-cn.com/problems/remove-linked-list-elements/) | 移除链表元素 | 简单 | 一般 |
| [707](https://leetcode-cn.com/problems/design-linked-list/) | 设计链表 | 中等 | 一般 |
| [24](https://leetcode-cn.com/problems/swap-nodes-in-pairs/) | 两两交换链表中的节点 | 中等 | 一般 |

## 栈

| Leetcode题号 | 题目 | 掌握程度 |
| :----: | :----: | :----: |
| [20](https://leetcode-cn.com/problems/valid-parentheses/) | 有效的括号 | 一般 |

## 单调栈

| Leetcode题号 | 题目 | 掌握程度 |
| :----: | :----: | :----: |
| [402](https://leetcode-cn.com/problems/remove-k-digits/) | 移掉 K 位数字 | <font color=red>差</font> |

## 队列

## 堆

| Leetcode题号 | 题目 | 掌握程度 |
| :----: | :----: | :----: |
| [215](https://leetcode-cn.com/problems/kth-largest-element-in-an-array/) | 数组中的第K个最大元素 | <font color=red>差</font> |

## 二叉树

| Leetcode题号 | 题目 | 掌握程度 |
| :----: | :----: | :----: |
| [102](https://leetcode-cn.com/problems/binary-tree-level-order-traversal/) | 二叉树的层序遍历 | 一般 |
| [103](https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/) | 二叉树的锯齿形层序遍历 | 一般 |
| [236](https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/) | 二叉树的最近公共祖先 | 一般 |

## hash 表

| Leetcode题号 | 题目 | 掌握程度 |
| :----: | :----: | :----: |
| [1](https://leetcode-cn.com/problems/two-sum/) | 两数之和 | 一般 |

# 算法

## 动态规划

| Leetcode题号 | 题目 | 掌握程度 |
| :----: | :----: | :----: |
| [53](https://leetcode-cn.com/problems/maximum-subarray/) | 最大子数组和 | 一般 |
| [121](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/) | 买卖股票的最佳时机 | 一般 |
| [5](https://leetcode-cn.com/problems/longest-palindromic-substring/) | 最长回文子串 | 一般 |

## 贪心算法

| Leetcode题号 | 题目 | 掌握程度 |
| :----: | :----: | :----: |
| [53](https://leetcode-cn.com/problems/maximum-subarray/) | 最大子数组和 | 一般 |
| [402](https://leetcode-cn.com/problems/remove-k-digits/) | 移掉 K 位数字 | <font color=red>差</font> |

## 滑动窗口

| Leetcode题号 | 题目 | 掌握程度 |
| :----: | :----: | :----: |
| [3](https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/) | 无重复字符的最长子串 | 一般 |
| [209](https://leetcode-cn.com/problems/minimum-size-subarray-sum/) | 长度最小的子数组 | 一般 |

## 二分查找

二分法的核心在于缩小查询范围，每次的迭代都在缩小查询范围。

二分查找的基本写法有两种写法：

第一种查找区间为`[a, b]`:

```java
class Solution {
    public int search(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}

```

第二种查找区间为`[a, b + 1)`:

```java
class Solution {
    public int search(int[] nums, int target) {

        int left = 0;
        int right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return -1;
    }
}

```

| Leetcode题号 | 题目 | 掌握程度 |
| :----: | :----: | :----: |
| [4](https://leetcode-cn.com/problems/median-of-two-sorted-arrays/) | 寻找两个正序数组的中位数 | <font color=red>差</font> |
| [33](https://leetcode-cn.com/problems/search-in-rotated-sorted-array/) | 搜索旋转排序数组 | <font color=red>差</font> |
| [34](https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/) | 在排序数组中查找元素的第一个和最后一个位置 | 较好 |
| [35](https://leetcode-cn.com/problems/search-insert-position/) | 搜索插入位置 | 较好 |
| [69](https://leetcode-cn.com/problems/sqrtx/) | Sqrt(x) | 一般 |
| [367](https://leetcode-cn.com/problems/valid-perfect-square/) | 有效的完全平方数 | 较好 |

## 分治算法

| Leetcode题号 | 题目 | 掌握程度 |
| :----: | :----: | :----: |
| [215](https://leetcode-cn.com/problems/kth-largest-element-in-an-array/) | 数组中的第K个最大元素 | 一般 |

## 回溯

## 排序

## 递归

满足递归编程的三个条件：

1. 一个问题的解可以分解为几个子问题的解，何为子问题？ 子问题就是数据规模更小的问题。比如，前面讲的电影院的例子，你要知道，“自己在哪一排”的问题，可以分解为“前一排的人在哪一排”这样一个子问题。
2. 这个问题与分解之后的子问题，除了数据规模不同，求解思路完全一样。所以子问题可以继续分解。比如电影院那个例子，你求解“自己在哪一排”的思路，和前面一排人求解“自己在哪一排”的思路，是一模一样的。
3. 存在递归终止条件。把问题分解为子问题，把子问题再分解为子子问题，一层一层分解下去，不能存在无限循环，这就需要有终止条件。

## 其他

| Leetcode题号 | 题目 | 掌握程度 |
| :----: | :----: | :----: |
| [146](https://leetcode-cn.com/problems/lru-cache/) | LRU 缓存 | <font color=red>差</font> |
| [15](https://leetcode-cn.com/problems/3sum/) | 三数之和 | <font color=red>差</font> |
| [415](https://leetcode-cn.com/problems/add-strings/) | 字符串相加 | 较好 |

# 经验之谈

刷题基本原则：

1. coding 的过程，和盖房子一样，先有思路和基本代码框架，再去实现细节。（写文章也是这样子，先理好结构，再去完善各个章节内容。）


