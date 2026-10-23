class Solution {
    public int sortPermutation(int[] nums) {
        return Math.max(IntStream.range(0, nums.length).filter(i -> nums[i] != i).map(i -> nums[i]).reduce(-1, (a, b) -> a & b), 0);
    }
}