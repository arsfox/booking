package app.repository.file;

import app.repository.Repository;
import app.repository.model.Mentor;
import app.repository.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Logger;

public class UserFileRepository implements Repository<User> {

    private ObjectMapper mapper;
    private final static String USER_FILE = "/files/users.json";
    private final static Logger LOGGER = Logger.getLogger(SimpleMentorFileRepository.class.getName());

    public UserFileRepository(ObjectMapper mapper){
        this.mapper = mapper;
    }

    @Override
    public List<User> get() {

        StringBuilder content = new StringBuilder();

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(USER_FILE)))){
            String str = "";
            while ((str = bufferedReader.readLine()) != null){
                content.append(str);
            }
            List<User> list = mapper.readValue(content.toString(), new TypeReference<List<User>>() {
            });

            if (list == null || list.isEmpty()){
                throw new RuntimeException("Список пуст");
            }
            return list;
        }catch (IOException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public User get(String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void update(User item) {

    }

    @Override
    public void save(User item) {

    }

}
