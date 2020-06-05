package app.factories;

import app.repository.Repository;
import app.repository.file.UserFileRepository;
import app.repository.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUsersFactory {
    public static Repository<User> getUsers(){
        return new UserFileRepository(new ObjectMapper());
    }
}
