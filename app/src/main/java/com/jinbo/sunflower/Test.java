package com.jinbo.sunflower;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * @author houjinbo
 * @date 2021/5/28
 */
public class Test {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 3, 2, 8};
        int tartget = 10;

        int[] result = sum(nums, tartget);
        assert (result.length == 2 && result[0] + result[1] == tartget);
    }

    /**
     * @param nums
     * @param tartget
     * @return
     */
    public static int[] sum(int[] nums, int tartget) {
        int[] result = new int[2];
        int startIndex = 0;
        Map<Integer, Boolean> frobiden = new HashMap<>();
        for (int i = startIndex; i < nums.length; i++) {
            if (frobiden.containsKey(i)) {
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == tartget) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }

            }
            frobiden.put(i, true);
        }
        return result;
    }
}
