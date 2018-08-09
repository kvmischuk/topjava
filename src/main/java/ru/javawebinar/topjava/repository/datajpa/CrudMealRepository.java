package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Query("SELECT m FROM Meal m WHERE m.user.id=:user_id")
    List<Meal> findAll(@Param("user_id") int user_id);

    @Override
    Meal save(Meal meal);


    @Transactional
    @Modifying
    @Query("DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:user_id")
    int delete(@Param("id") int id,@Param("user_id") int user_id);

    @Query("SELECT m FROM Meal m WHERE m.id=:id AND m.user.id=:user_id")
    Meal findById(@Param("id") int id,@Param("user_id") int user_id);

    @Query("SELECT m FROM Meal m WHERE m.user.id=:user_id AND m.dateTime>=:startDate AND m.dateTime<=:endDate")
    List<Meal> findAll(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("user_id") int user_id);

}
