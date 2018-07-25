package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class UserUtil {
    public static final List<User> USERS = Arrays.asList(
            new User(0,"John","John@topjava.com","password",2000,true,new HashSet<>()),
            new User(0,"James","James@topjava.com","12345",2500,true,new HashSet<>()),
            new User(0,"Jane","Jane@topjava.com","strong password",1500,true,new HashSet<>()),
            new User(0,"Johanna","Johanna@topjava.com","54321",3000,true,new HashSet<>())
    );
}
