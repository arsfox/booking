package app.factories;

import app.repository.Repository;
import app.repository.file.ClientFileRepository;
import app.repository.file.SimpleMentorFileRepository;
import app.repository.model.Client;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonClientFactory {

    public static Repository<Client> getClients(){
        return new ClientFileRepository(new ObjectMapper());
    }

}
