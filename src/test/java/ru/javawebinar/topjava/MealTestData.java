package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {


    public static final int BREAKFAST1_ID = START_SEQ + 2;
    public static final int LUNCH1_ID = START_SEQ + 3;
    public static final int DINNER1_ID = START_SEQ + 4;
    public static final int BREAKFAST2_ID = START_SEQ + 5;
    public static final int LUNCH2_ID = START_SEQ + 6;
    public static final int DINNER2_ID = START_SEQ + 7;

    public static final LocalDate STARTDATE = LocalDate.of(2015,Month.MAY,1);
    public static final LocalDate ENDDATE = LocalDate.of(2015,Month.MAY,30);

    public static final LocalDateTime STARTDATETIME = LocalDateTime.of(2015,Month.MAY,30,23,59);
    public static final LocalDateTime ENDDATETIME = LocalDateTime.of(2015,Month.JUNE,30,0,0);

    public static final Meal BREAKFAST1 = new Meal(BREAKFAST1_ID,LocalDateTime.of(2015,Month.MAY,30,10,0),"Завтрак", 500);
    public static final Meal LUNCH1 = new Meal(LUNCH1_ID,LocalDateTime.of(2015,Month.MAY,30,13,0),"Обед", 1000);
    public static final Meal DINNER1 = new Meal(DINNER1_ID,LocalDateTime.of(2015,Month.MAY,30,20,0),"Ужин", 500);
    public static final Meal BREAKFAST2 = new Meal(BREAKFAST2_ID,LocalDateTime.of(2015,Month.MAY,31,10,0),"Завтрак", 1000);
    public static final Meal LUNCH2 = new Meal(LUNCH2_ID,LocalDateTime.of(2015,Month.MAY,31,13,0),"Обед", 500);
    public static final Meal DINNER2 = new Meal(DINNER2_ID,LocalDateTime.of(2015,Month.MAY,31,20,0),"Ужин", 510);

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual,Arrays.asList(expected));
    }
}
