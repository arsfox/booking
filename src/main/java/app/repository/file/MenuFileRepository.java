package app.repository.file;

import app.repository.Repository;
import app.repository.model.Client;
import app.repository.model.Menu;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Logger;

public class MenuFileRepository implements Repository<Menu> {

    private ObjectMapper mapper;
    private final static String MENU_FILE = "/files/menu.json";
    private final static Logger LOGGER = Logger.getLogger(SimpleMentorFileRepository.class.getName());

    public MenuFileRepository(ObjectMapper mapper){
        this.mapper = mapper;
    }

    @Override
    public List<Menu> get() {

        StringBuilder content = new StringBuilder();

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(MENU_FILE)))){
            String str = "";
            while ((str = bufferedReader.readLine()) != null){
                content.append(str);
            }
            List<Menu> list = mapper.readValue(content.toString(), new TypeReference<List<Menu>>() {
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
    public Menu get(String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void update(Menu item) {

    }

    @Override
    public void save(Menu item) {

    }

}
