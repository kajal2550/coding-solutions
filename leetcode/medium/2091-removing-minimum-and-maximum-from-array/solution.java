 class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        int i = 0;
        int j = 0;
        
        for (int k = 1; k < n; ++k) {
            if (nums[k] < nums[i]) i = k;
            if (nums[k] > nums[j]) j = k;
        }
        
        if (i > j) {
            int temp = i;
            i = j;
            j = temp;
        }
        
        return Math.min(j + 1, Math.min(n - i, i + 1 + n - j));
    }
}