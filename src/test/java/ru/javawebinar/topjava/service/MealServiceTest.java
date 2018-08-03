package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() throws Exception {
        Meal meal = service.get(BREAKFAST1_ID, USER_ID);
        assertMatch(meal,BREAKFAST1);
    }

    @Test
    public void delete() throws Exception {
        service.delete(BREAKFAST1_ID, USER_ID);
        assertMatch(service.getAll(USER_ID), LUNCH1,DINNER1,BREAKFAST2,LUNCH2,DINNER2);
    }

    @Test
    public void getBetweenDates() {
        List<Meal> meals = service.getBetweenDates(STARTDATE,ENDDATE,USER_ID);
        assertMatch(meals,BREAKFAST1,LUNCH1,DINNER1);
    }

    @Test
    public void getBetweenDateTimes() {
        List<Meal> meals = service.getBetweenDateTimes(STARTDATETIME,ENDDATETIME,USER_ID);
        assertMatch(meals,BREAKFAST2,LUNCH2,DINNER2);
    }

    @Test
    public void getAll() {
        assertMatch(service.getAll(USER_ID),BREAKFAST1,LUNCH1,DINNER1,BREAKFAST2,LUNCH2,DINNER2);
    }

    @Test
    public void update() {
        Meal meal = service.get(LUNCH2_ID,USER_ID);
        meal.setCalories(3000);
        meal.setDescription("Большой обед");
        service.update(meal,USER_ID);
        assertMatch(meal,service.get(LUNCH2_ID,USER_ID));
    }

    @Test
    public void create() {
        Meal meal = new Meal(LocalDateTime.of(2016,1,1,0,0),"Перекус",1000);
        service.create(meal,USER_ID);
        assertMatch(service.getAll(USER_ID),BREAKFAST1,LUNCH1,DINNER1,BREAKFAST2,LUNCH2,DINNER2,meal);
    }
}