import java.util.Scanner;

public class TimeDuration {
    //secondssincemidnight allows for inputs higher than the next level, allowing an input of 1, 60, 60 to be converted to 2:01:00
    private int secondssincemidnight = 0;
    private static int totalCreated = 0;

    public TimeDuration(int hour, int minute, int second) {
        totalCreated++;
        secondssincemidnight = hour * 3600 + minute * 60 + second;
    }

    public int getHour() {
        return secondssincemidnight / 3600;
    }

    public int getMinute() {
        return secondssincemidnight % 3600 / 60;
    }

    public int getSecond() {   
        return secondssincemidnight % 60;
    }

    public static TimeDuration parseFromString (String time) {

        if ((time.indexOf("h") == -1 && time.indexOf("m") == -1 && time.indexOf("s") == -1) && (time.indexOf(":") == -1)) {
            System.err.println("Invalid time format | Usage: 1h 2m 3s OR 10:20:30");
            return null;
        }

        int hour = 0;
        int minute = 0;
        int second = 0;

        if (time.indexOf(":") != -1) {
            String[] timeArray = time.split(":");
            if (timeArray.length == 3) {
                hour = Integer.parseInt(timeArray[0]);
                minute = Integer.parseInt(timeArray[1]);
                second = Integer.parseInt(timeArray[2]);
                return new TimeDuration(hour, minute, second);
            }
            else if (timeArray.length == 2) {
                minute = Integer.parseInt(timeArray[0]);
                second = Integer.parseInt(timeArray[1]);
                return new TimeDuration(hour, minute, second);
            }
            else {
                System.err.println("Invalid time format | Example: 1h 2m 3s OR 10:20:30");
                return null;
            }
        }
        
        String cleanedInput = time;
        cleanedInput = cleanedInput.toLowerCase();
        cleanedInput = cleanedInput.replaceAll(" ", "");
        cleanedInput = cleanedInput.replaceAll(",", "");

        int hourindex = cleanedInput.indexOf("h");
        int minuteindex = cleanedInput.indexOf("m");
        int secondindex = cleanedInput.indexOf("s");


        if (hourindex != -1) {
            hour = Integer.parseInt(cleanedInput.substring(0, hourindex));
        }
        if (minuteindex != -1) {
            minute = Integer.parseInt(cleanedInput.substring(hourindex + 1, minuteindex));
        }
        if (secondindex != -1) {
            if (minuteindex == -1) {
                minuteindex = hourindex;
            }
            second = Integer.parseInt(cleanedInput.substring(minuteindex + 1, secondindex));
        }
        
            


        return new TimeDuration(hour, minute, second);
    }

    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }
        TimeDuration otherTime = (TimeDuration) other;
        return secondssincemidnight == otherTime.secondssincemidnight;
    }

    public String toString() {
        String paddedminutes = getMinute() < 10 ? "0" + getMinute() : "" + getMinute();
        String paddedseconds = getSecond() < 10 ? "0" + getSecond() : "" + getSecond();
        return getHour() + ":" + paddedminutes + ":" + paddedseconds;
}

    public static void main(String[] args) {
        System.out.println(totalCreated);
        String[] test = {
            "1h",
            "1h 2m",
            "1h 2m, 3s",
            "10:2:30",
            "10:20",
            "10:2:3",
            "10h 3s",
            "3h, 10s",
        };

        for (String time : test) {
            System.out.println("input: " + time);
            System.out.println("output: " + TimeDuration.parseFromString(time) + "\n");
        }
        System.out.println(totalCreated); //8
    }
}
