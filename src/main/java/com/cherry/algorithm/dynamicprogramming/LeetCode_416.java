package com.cherry.algorithm.dynamicprogramming;

public class LeetCode_416 {
    /**
     * 416. 分割等和子集
     * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     *
     * 注意:
     *
     * 每个数组中的元素不会超过 100
     * 数组的大小不会超过 200
     * 示例 1:
     *
     * 输入: [1, 5, 11, 5]
     *
     * 输出: true
     *
     * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
     *  
     *
     * 示例 2:
     *
     * 输入: [1, 2, 3, 5]
     *
     * 输出: false
     *
     * 解释: 数组不能分割成两个元素和相等的子集.
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/partition-equal-subset-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        LeetCode_416 leetCode_416 = new LeetCode_416();
        int[] arr = {1, 1, 1, 1};
        boolean b = leetCode_416.canPartition(arr);
        System.out.println(b);
    }

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        int len = nums.length;
        boolean[][] status = new boolean[len][sum + 1];
        return dfs(nums, status,target);
    }

    public boolean dfs(int[] nums,boolean[][] status,int target) {
        status[0][0] = true;
        status[0][nums[0]] = true;
        for (int i = 1; i < nums.length; i++) {
            for (int j = target; j >=0; j--) {
                if (status[i - 1][j]) {
                    status[i][j] = status[i - 1][j];
                }
            }
            for (int j = target - nums[i]; j >= 0; j--) {
                if (status[i - 1][j]) {
                    status[i][j + nums[i]] = true;
                }
            }

        }
        return status[nums.length - 1][target];
    }
}
