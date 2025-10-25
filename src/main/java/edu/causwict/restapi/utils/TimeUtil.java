package edu.causwict.restapi.utils;

public class TimeUtil {
    public static long toSecond(int second, int minute, int hour, int day) {
        return second + minute * 60L + hour * 3600L + day * 86400L;
    }

    public static long toSecond(int second, int minute, int hour) {
        return toSecond(second, minute, hour, 0);
    }

    public static long toSecond(int second, int minute) {
        return toSecond(second, minute, 0);
    }
}
