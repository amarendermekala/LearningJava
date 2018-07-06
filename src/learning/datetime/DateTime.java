package learning.datetime;

import java.sql.Timestamp;
import java.time.*;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoPeriod;
import java.time.chrono.Chronology;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

public class DateTime {

    public static void main(String[] args) {
        // Java 7
        Date date = new Date(); // now
        Calendar cal = Calendar.getInstance();
        cal.set(2012, 1, 10); // 2012 Feb 10, As January is 0
        cal.add(Calendar.DAY_OF_MONTH, 2);
        Date d2 = cal.getTime();
        System.out.print(d2);
        // Sun Feb 12 10:56:44 CST 2012

        // Date Class is Mutable
         Date d = new Person().getCreationDate();
         d.setTime(0);
        // Can change date from outside which is bad
        // To avoid use defensive copy pattern instead, see person
        // Overhead: New object everytime, overhead of garbage collector


        // Java 8
        // package java.time

        // Instant
        Instant start = Instant.now();
        Instant end = Instant.now();

        // Duration
        Duration elapsed = Duration.between(start, end.plusSeconds(5));
        System.out.println("\n" + start + "    " + elapsed.toMillis());
        // 2018-07-06T19:07:37.468Z    5000

        // LocalDate
        LocalDate ld = LocalDate.now();
        LocalDate bd = LocalDate.of(2012, Month.APRIL, 21);
        System.out.println(bd);
        // 2012-04-21

        // Period
        Period p = Period.between(bd, ld);
        System.out.println(p);
        // P6Y2M15D

        Period gap = bd.until(ld);
        System.out.println(gap.getYears() + " " + gap.getMonths() + " " + gap.getDays());
        // 6 2 15

        System.out.println(bd.until(ld, ChronoUnit.DAYS));
        // 2267
        System.out.println(bd.until(ld, ChronoUnit.MONTHS));
        // 74

        System.out.println(gap.get(ChronoUnit.DAYS));
        // 15
        System.out.println(gap.get(ChronoUnit.MONTHS));
        // 2


        // DateAdjuster

        LocalDate ld1 = LocalDate.now();
        LocalDate nextSunday = ld1.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(nextSunday);
        // 2018-07-08 which is sunday after July 6th
        // Methods like firstDayOfMonth, lastDayOfMonth, ofyear, ofnextmonth other 14 static methods

        // LocalTime
        LocalTime now = LocalTime.now();
        LocalTime lt = LocalTime.of(12, 23, 45);
        System.out.println(lt);
        // 12:23:45

        System.out.println(lt.plusHours(18));
        // 06:23:45

        // ZonedTime
        // IANA.org/time-zones

        Set<String> allZoneIds = ZoneId.getAvailableZoneIds();
        ZoneId uk = ZoneId.of("America/Marigot");
        System.out.println(uk);

        // create a zoned time

        ZonedDateTime zt = ZonedDateTime.of(2018, Month.APRIL.getValue(), 12,
                                            10, 0 , 0 , 0,
                                                ZoneId.of("Europe/London"));
        System.out.println(zt);
        // 2018-04-12T10:00+01:00[Europe/London]
        ZonedDateTime zt1 = zt.plus(Period.ofMonths(1));
        System.out.println(zt1);
        // 2018-05-12T10:00+01:00[Europe/London]
        ZonedDateTime usTime = zt1.withZoneSameInstant(ZoneId.of("US/Central"));
        System.out.println(usTime);
        // 2018-05-12T04:00-05:00[US/Central]


        System.out.println(DateTimeFormatter.ISO_DATE_TIME.format(usTime));
        // 2018-05-12T04:00:00-05:00[US/Central]

        System.out.println(DateTimeFormatter.RFC_1123_DATE_TIME.format(usTime));
        // Sat, 12 May 2018 04:00:00 -0500

        // Legacy and Java 8 compatibility
        Date date1 = Date.from(Instant.now());
        Instant i1 = date1.toInstant();

        Timestamp time1 = Timestamp.from(i1);
        Instant i2 = time1.toInstant();

        // Date aad = Date.from(LocalDate.now());
        // Instant i2 = aad.toLocalDate();

        // LocalTime to Time



    }

}


