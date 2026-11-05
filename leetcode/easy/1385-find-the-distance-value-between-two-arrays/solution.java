// class Solution {
//     public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        
//     }
// }

 
 

class Solution {
        public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
                java.util.Arrays.sort(arr2);
                        int distanceValue = 0;
                                for (int x : arr1) {
                                            int left = 0, right = arr2.length - 1;
                                                        boolean found = false;
                                                                    while (left <= right) {
                                                                                    int mid = left + (right - left) / 2;
                                                                                                    if (Math.abs(arr2[mid] - x) <= d) {
                                                                                                                        found = true;
                                                                                                                                            break;
                                                                                                                                                            }
                                                                                                                                                                            if (arr2[mid] < x) {
                                                                                                                                                                                                left = mid + 1;
                                                                                                                                                                                                                } else {
                                                                                                                                                                                                                                    right = mid - 1;
                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                            if (!found) {
                                                                                                                                                                                                                                                                                            distanceValue++;
                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                        return distanceValue;
                                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                                            }





















