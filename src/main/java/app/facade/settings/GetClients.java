package app.facade.settings;

import app.factories.JsonClientFactory;
import app.repository.Repository;
import app.repository.model.Client;

import java.util.List;

public class GetClients {

    private Repository<Client> clients = JsonClientFactory.getClients();

    public List<Client> fromJson(){
        return clients.get();
    }

}
