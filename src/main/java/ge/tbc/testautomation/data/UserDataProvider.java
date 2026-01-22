package ge.tbc.testautomation.data;

import org.testng.annotations.DataProvider;

import java.util.List;

public class UserDataProvider {
    @DataProvider
    public static Object[][] getAllUsers(){
        DatabaseSteps databaseSteps = new DatabaseSteps();
        List<UserModel> users = databaseSteps.getAllUsers();

        Object[][] userData = new Object[users.size()][1];
        for (int i = 0; i < users.size(); i++){
            userData[i][0] = users.get(i);
        }

        return userData;
    }
}
