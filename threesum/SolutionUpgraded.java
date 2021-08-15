package threesum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SolutionUpgraded {
    private List<List<Integer>> twoSum(int target, int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        List<List<Integer>> result = new LinkedList<>();
        for (int num : nums) {
            int key = target - num;
            if (map.containsKey(key) && map.get(key) == null) {
                map.put(key, num);
                int[] tempArr = { key, num };
                List<Integer> integers = Arrays.stream(tempArr).boxed().collect(Collectors.toList());
                result.add(integers);
            } else if (!map.containsKey(num)) {
                map.put(num, null);
            }
        }
        return result;
    }
    
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new LinkedList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            List<List<Integer>> twoSumResult = twoSum(-nums[i], Arrays.copyOfRange(nums, i + 1, nums.length));
            for (List<Integer> element : twoSumResult){
                element.add(nums[i]);
            }
            result.addAll(twoSumResult);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] input = {0,0,0,0};
        SolutionUpgraded solution = new SolutionUpgraded();
        var result = solution.threeSum(input);
        System.out.println(result);
    }
    
}
