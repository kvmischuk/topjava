package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class MealRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public List<MealWithExceed> getAll() {
        log.info("getAll");
        return MealsUtil.getWithExceeded(service.getAll(SecurityUtil.authUserId()),SecurityUtil.authUserCaloriesPerDay());
    }

    public MealWithExceed get(int id) {
        log.info("get {}", id);
        Meal meal = service.get(id,SecurityUtil.authUserId());
        return MealsUtil.createWithExceed(meal,true);
    }

    public MealWithExceed create(Meal meal) {
        log.info("create {}", meal);
        checkNew(meal);
        return MealsUtil.createWithExceed(service.create(meal),true);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id, SecurityUtil.authUserId());
    }

    public void update(Meal meal, int id, int userId) {
        log.info("update {} with id={} for user {}", meal, id, userId);
        assureIdConsistent(meal, id);
        service.update(meal,userId);
    }


}