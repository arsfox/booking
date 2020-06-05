package app.facade.settings;

import app.factories.JsonMentorFactory;
import app.factories.JsonMenuFactory;
import app.repository.Repository;
import app.repository.model.Mentor;
import app.repository.model.Menu;

import java.util.List;

public class GetMenu {

    private Repository<Menu> menu = JsonMenuFactory.getMentors();

    public List<Menu> fromJson(){
        return menu.get();
    }

}
