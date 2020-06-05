package app.facade.settings;

import app.factories.JsonSettingFactory;
import app.factories.JsonUsersFactory;
import app.repository.Repository;
import app.repository.model.Setting;
import app.repository.model.User;

import java.util.List;

public class GetUsers {

    Repository<User> users = JsonUsersFactory.getUsers();

    public List<User> fromJson(){
        return users.get();
    }
}
