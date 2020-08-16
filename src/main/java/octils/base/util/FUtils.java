package octils.base.util;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DurationFormatUtils;
import org.bukkit.ChatColor;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class FUtils {

    private FUtils() {
        throw new RuntimeException("Cannot instantiate a utility class.");
    }

	public static DecimalFormat getFormat() {
		return new DecimalFormat("0.00");
	}

	public static String strip(String s){ return ChatColor.stripColor(s);}

	public static String color(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}

	public static String deathMsg(String victim, int vkills, String killer, int kkills, String death){
        String msg = death.replace(victim, "&c"+victim+"&7["+vkills+"&7]&e");
        if(killer != null){
           msg = msg.replace(killer,"&c"+killer+"&7["+kkills+"&7]&e" );
        }
       return color("&e"+msg);
    }

    
    public static String getHeart() {
    	return "\u2665";
    }
    
    public static String getLine() {
    	return color("&m----------------");
    }
    
    public static ChatColor randomChatColor() {
        ChatColor[] chatColors = new ChatColor[]{
                ChatColor.DARK_GREEN, ChatColor.DARK_PURPLE, ChatColor.GOLD,
                ChatColor.DARK_GRAY, ChatColor.DARK_RED, ChatColor.BLUE, ChatColor.GREEN, ChatColor.RED,
                ChatColor.LIGHT_PURPLE, ChatColor.YELLOW, ChatColor.WHITE, ChatColor.AQUA,
        };
        return chatColors[new Random().nextInt(chatColors.length)];
    }

    public static String LongCountDown(Long value) {
        if (value < TimeUnit.MINUTES.toMillis(1L)) {
            return new DecimalFormat("#").format(value / 1000.0) + "s";
        }
        return DurationFormatUtils.formatDuration(value, ((value >= TimeUnit.HOURS.toMillis(1L)) ? "HH:" : "") + "mm:ss");
    }

    public static String secondsToFormat(int seconds, boolean showEmptyHours) {
        int hours = seconds / 3600;
        seconds -= hours * 3600;
        int minutes = seconds / 60;
        seconds -= minutes * 60;
        return niceTime(hours, minutes, seconds, showEmptyHours);
    }

    private static String niceTime(int hours, int minutes, int seconds, boolean showEmptyHours) {
        StringBuilder builder = new StringBuilder();
        if (hours > 0) {
            if (hours < 10) {
                builder.append('0');
            }
            builder.append(hours);
            builder.append(':');
        } else if (showEmptyHours) {
            builder.append("00:");
        }
        if (minutes < 10 && hours != -1) {
            builder.append('0');
        }
        builder.append(minutes);
        builder.append(':');
        if (seconds < 10) {
            builder.append('0');
        }
        builder.append(seconds);

        return builder.toString();
    }


    public static long timeFromString(String sr) {
        long time = 0;
        if (sr.substring(0, 1).matches("[0-9]")) {
            char timeLength = Character.toLowerCase(sr.charAt(sr.length() - 1));
            time = NumberUtils.toLong(sr.substring(0, sr.length() - 1), 0);

            switch(timeLength){
                case 'm':
                    time = TimeUnit.MINUTES.toMillis(time);
                    break;
                case 'h':
                    time = TimeUnit.HOURS.toMillis(time);
                    break;
                case 'd':
                    time = TimeUnit.DAYS.toMillis(time);
                    break;
                case 'w':
                    time = TimeUnit.DAYS.toMillis(time*7L);
                    break;
                case 'n':
                    time = TimeUnit.DAYS.toMillis(time*30L);
                    break;
                case 'y':
                    time = TimeUnit.DAYS.toMillis(time*365L);
                    break;
                default:
                    time = 0;
                    break;
            }
        }
        if(time != 0){
            time += System.currentTimeMillis(); //why we adding this here?
        }
        return time; //"-1" = infinite, "0" = invalid
    }


    public static String getDuration(Long now, Long end){

	    end = end == -1L ? -1L : end+1000L; //correct format.

        int seconds = (int)TimeUnit.MILLISECONDS.toSeconds(end-now);
        int minutes = (int)Math.floor(seconds/60.0);
        int hours = (int)Math.floor(minutes/60.0);
        int days =(int)Math.floor(hours/24.0);
        int months =(int)Math.floor(days/30.0);
        String c = "forever";

        if(end == -1L){
            return c;
        }
        else if(months > 0){
            c = months+" month(s)";
        }
        else if(days > 0){
            c  = days+" day(s)";
        }
        else if(hours > 0){
            c = hours+" hour(s)";
        }
        else if(minutes > 0){
            c = minutes+" minute(s)";
        }
        else{
            c = seconds+" second(s)";
        }
        return c;
    }

   public static String getMemory() {
        Runtime runtime = Runtime.getRuntime();
       return (runtime.totalMemory() - runtime.freeMemory()) / 1048576L  +"/"+(runtime.maxMemory()/1048576);
    }
}
