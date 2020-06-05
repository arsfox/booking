package app.factories;

import app.repository.Repository;
import app.repository.file.MenuFileRepository;
import app.repository.file.SimpleMentorFileRepository;
import app.repository.model.Mentor;
import app.repository.model.Menu;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMenuFactory {

    public static Repository<Menu> getMentors(){
        return new MenuFileRepository(new ObjectMapper());
    }

}
