package octils.base.util;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class DUtils {

	private DUtils() { throw new RuntimeException("Cannot instantiate a utility class."); }

	public static Calendar calendarUTC = Calendar.getInstance(TimeZone.getTimeZone("UTC")); //mostly for sql
	public static ZoneId zoneId = ZoneId.of("America/Guatemala"); //cst


	public static DateTimeFormatter getFormatter(){
		return DateTimeFormatter.ofLocalizedDateTime( FormatStyle.MEDIUM )
				.withLocale(Locale.UK)
				.withZone(zoneId);
	}

	public static DateTimeFormatter getFormatter(String format){
		return DateTimeFormatter.ofPattern( format )
				.withLocale(Locale.UK)
				.withZone(zoneId);
	}
}
