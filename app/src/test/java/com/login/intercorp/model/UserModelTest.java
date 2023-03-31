package com.login.intercorp.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserModelTest {

    UserModel userModel;

    @Before
    public void setUp() throws Exception {
        userModel = new UserModel("123","Alberto","Ramirez",26,"07/07/2022");
    }
    @Test
    public void validNameUserModel(){

        Assert.assertEquals(userModel.getLastName(),"Ramirez");
    }

}
