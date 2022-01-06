package com.bookIt.stepDefinitions;

import com.bookIt.utils.DbUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;


import java.util.Map;

public class DBStepDefs {
    Map<String, Object> rowMap;
    public static String DbFullname;
    public static String DbRole;
    public static String DbTeam;
    public static String DbBatch;
    public static String DbCampus;

    @When("User sends a query to database with {string}")
    public void user_sends_a_query_to_database_with(String email) {
        String query ="select firstname,lastname,role,name,location,batch_number\n" +
                "                from users u join(select t.id,t.name,batch_number,location\n" +
                "                from team t join campus c on t.campus_id = c.id) r\n" +
                "                on u.team_id = r.id where u.email='"+email+"'";

        rowMap = DbUtils.getRowMap(query);
        System.out.println("rowMap = " + rowMap);
    }

    @Then("User gets DataBase information")
    public void user_gets_DataBase_information() {
        DbFullname = rowMap.get("firstname")+ " " + rowMap.get("lastname");
        System.out.println("firstname = " + DbFullname);
        DbRole = (String) rowMap.get("role");
        System.out.println("DbRole = " + DbRole);
        DbBatch = "#" + rowMap.get("batch_number");
        System.out.println("DbBatch = " + DbBatch);
        DbCampus = (String) rowMap.get("location");
        System.out.println("DbCampus = " + DbCampus);
        DbTeam = (String) rowMap.get("name");
        System.out.println("DbTeam = " + DbTeam);

        System.out.println("UIStep_defs.UiFullName = " + UIStep_defs.UiFullName);
        System.out.println("UIStep_defs.batch = " + UIStep_defs.batch);


        Assert.assertTrue(UIStep_defs.UiFullName.equals(DbFullname)&&APIStep_defs.ApiFirstName.equals(DbFullname));
        Assert.assertTrue(UIStep_defs.UiRole.equals(DbRole)&&APIStep_defs.ApiRole.equals(DbRole));
        // Assert.assertTrue(UIStep_defs.campus.equals(DbCampus)&&APIStep_defs.ApiCampus.equals(DbCampus));
        //Assert.assertTrue(UIStep_defs.batch.equals(DbBatch)&&APIStep_defs.ApiBatch.equals(DbBatch));
        //Assert.assertTrue(UIStep_defs.UiTeam.equals(DbTeam)&&APIStep_defs.ApiTeam.equals(DbTeam));



    }
}
