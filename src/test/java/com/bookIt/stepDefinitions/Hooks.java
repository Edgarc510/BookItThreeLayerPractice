package com.bookIt.stepDefinitions;

import com.bookIt.utils.DbUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;


public class Hooks {

    @Before("@db")
public void dbHook(){
        DbUtils.createConnection();
    }
    @After("@db")
    public void afterDbHook() {
        DbUtils.destroyConnection();
    }
}
