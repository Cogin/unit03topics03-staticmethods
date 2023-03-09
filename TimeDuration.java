import java.util.Scanner;

public class TimeDuration {
    
    private int hour;
    private int minute;
    private int second;

    public TimeDuration(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public static TimeDuration parseFromString (String time) {


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

    public String toString() {
        String paddedminutes = minute < 10 ? "0" + minute : "" + minute;
        String paddedseconds = second < 10 ? "0" + second : "" + second;
        return hour + ":" + paddedminutes + ":" + paddedseconds;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter time: ");
        String timeStr = s.nextLine();
        TimeDuration time = TimeDuration.parseFromString(timeStr);
        System.out.println(time);
        s.close();
    }
}
