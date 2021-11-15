import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Solution
 */

public class Solution {

    public Solution() {

    }

    String[] processString(String S) {
        String[] result = new String[3];
        String[] splitS = S.split(" ");
        result[0] = splitS[0];
        String[] splitT = splitS[1].split("-");
        result[1] = splitT[0];
        result[2] = splitT[1];
        return result;
    }

    int[] processTime(String[] S) {
        int[] result = new int[2];
        Map<String, Integer> days = new HashMap<>();
        days.put("Mon", 0);
        days.put("Tue", 1);
        days.put("Wed", 2);
        days.put("Thu", 3);
        days.put("Fri", 4);
        days.put("Sat", 5);
        days.put("Sun", 6);
        int day = days.get(S[0]);
        String[] startTime = S[1].split(":");
        int intStartTime = day * 60 * 24 + Integer.parseInt(startTime[0]) * 60 + Integer.parseInt(startTime[1]);
        String[] endTime = S[2].split(":");
        int intEndTime = day * 60 * 24 + Integer.parseInt(endTime[0]) * 60 + Integer.parseInt(endTime[1]);
        result[0] = intStartTime;
        result[1] = intEndTime;
        return result;
    }

    public int solution(String S) {
        int result;
        List<int[]> meetingList = new ArrayList<>();
        String[] lines = S.split("\n");
        for (String line : lines) {
            String[] processedLine = processString(line);
            meetingList.add(processTime(processedLine));
        }
        meetingList.sort((a, b) -> {
            return a[0] - b[0];
        });
        result = 6 * 24 * 60 + 24 * 60 - meetingList.get(meetingList.size() - 1)[1];
        result = meetingList.get(0)[0] > result ? meetingList.get(0)[0] : result;
        for (int i = 0; i < meetingList.size() - 1; i++) {
            int sleepTime = meetingList.get(i + 1)[0] - meetingList.get(i)[1];
            result = sleepTime > result ? sleepTime : result;
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(
                "Tue 01:00-23:00\nWed 01:00-23:00\nThu 01:00-23:00\nFri 01:00-23:00\nSat 01:00-23:00\nSun 01:00-21:00"));

    }
}