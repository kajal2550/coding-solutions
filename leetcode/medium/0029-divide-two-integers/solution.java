class Solution {

    
public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
                    return Integer.MAX_VALUE;
        }
            boolean neg = (dividend < 0) ^ (divisor < 0);
                long a = Math.abs((long) dividend);
                    long b = Math.abs((long) divisor);
                        long res = 0;
                            while (a >= b) {
                                        long temp = b;
                                                long count = 1;
                                                        while (a >= (temp << 1)) {
                                                                        temp <<= 1;
                                                                                    count <<= 1;
                                                        }
                                                                a -= temp;
                                                                        res += count;
                            }
                                long ans = neg ? -res : res;
                                    if (ans > Integer.MAX_VALUE) {
                                                return Integer.MAX_VALUE;
                                    }
                                        if (ans < Integer.MIN_VALUE) {
                                                    return Integer.MIN_VALUE;
                                        }
                                            return (int) ans;
}
    

}

 

 