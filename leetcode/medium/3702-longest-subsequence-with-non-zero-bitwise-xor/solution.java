 class Solution {
    public int longestSubsequence(int[] nums) {
        int totalXor = 0;
        boolean hasNonZero = false;
        
        for (int x : nums) {
            totalXor ^= x;
            if (x != 0) hasNonZero = true;
        }
        
        if (!hasNonZero) return 0;
        if (totalXor != 0) return nums.length;
        
        return nums.length - 1;
    }
}