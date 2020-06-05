package app.repository.file;

import app.repository.Repository;
import app.repository.model.Client;
import app.repository.model.Mentor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Logger;

public class ClientFileRepository implements Repository<Client> {

    private ObjectMapper mapper;
    private final static String MENTOR_FILE = "/files/clients.json";
    private final static Logger LOGGER = Logger.getLogger(SimpleMentorFileRepository.class.getName());

    public ClientFileRepository(ObjectMapper mapper){
        this.mapper = mapper;
    }

    @Override
    public List<Client> get() {

        StringBuilder content = new StringBuilder();

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(MENTOR_FILE)))){
            String str = "";
            while ((str = bufferedReader.readLine()) != null){
                content.append(str);
            }
            List<Client> list = mapper.readValue(content.toString(), new TypeReference<List<Client>>() {
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
    public Client get(String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void update(Client item) {

    }

    @Override
    public void save(Client item) {

    }

}
