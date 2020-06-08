package app.repository.loader;

import app.repository.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Loader {

    private static ObjectMapper objectMapper = new ObjectMapper();


    public static void main(String[] args) {
        loadSettings();
        List<Role> roles = loadRole();
//        List<Mentor> mentors = loadMentors(roles);
//        List<Client> clients = loadClients(roles);
        loadUsers(roles);
        loadMenu(roles);
    }

    private static void loadMenu(List<Role> roles) {
        Role roleMentor = null;
        for(Role r: roles) {
            if(r.getCode().equals("MENTOR")){
                roleMentor = r;
                break;
            }
        }

        Role roleClient = null;
        for(Role r: roles){
            if(r.getCode().equals("CLIENT")){
                roleClient = r;
                break;
            }
        }

        Menu menu1 = new Menu();
        menu1.setUrl("");
        menu1.setName("Главная (M)");
        menu1.setRoleId(roleMentor);

        Menu menu2 = new Menu();
        menu2.setUrl("schedules");
        menu2.setName("Записи");
        menu2.setRoleId(roleMentor);

        Menu menu3 = new Menu();
        menu3.setUrl("mentors");
        menu3.setName("Наставники");
        menu3.setRoleId(roleMentor);

        Menu menu4 = new Menu();
        menu4.setUrl("settings");
        menu4.setName("Общие настройки");
        menu4.setRoleId(roleMentor);

        Menu menu11 = new Menu();
        menu11.setUrl("");
        menu11.setName("Главная (C)");
        menu11.setRoleId(roleClient);

        Menu menu12 = new Menu();
        menu12.setUrl("");
        menu12.setName("Записаться");
        menu12.setRoleId(roleClient);

        Menu menu13 = new Menu();
        menu13.setUrl("");
        menu13.setName("Мои записи");
        menu13.setRoleId(roleClient);

        List<Menu> menus = new LinkedList<>();
        menus.add(menu1);
        menus.add(menu2);
        menus.add(menu3);
        menus.add(menu4);
        menus.add(menu11);
        menus.add(menu12);
        menus.add(menu13);

        saveToFile("/files/menu.json", menus);


    }

    private static List<User> loadUsers(List<Role> roles) {
        Role roleMentor = null;
        for(Role r: roles) {
            if(r.getCode().equals("MENTOR")){
                roleMentor = r;
                break;
            }
        }

        Role roleClient = null;
        for(Role r: roles){
            if(r.getCode().equals("CLIENT")){
                roleClient = r;
                break;
            }
        }

        List<User> users = new LinkedList<>();

        User user1 = new User();
        user1.setId(UUID.randomUUID().toString());
        user1.setEmail("mentor1@mail.ru");
        user1.setLogin("mentor1@mail.ru");
        user1.setPassword("mentor1");
        user1.setRole(roleMentor);

        User user2 = new User();
        user2.setId(UUID.randomUUID().toString());
        user2.setEmail("mentor2@mail.ru");
        user2.setLogin("mentor2@mail.ru");
        user2.setPassword("mentor2");
        user2.setRole(roleMentor);

        User user3 = new User();
        user3.setId(UUID.randomUUID().toString());
        user3.setEmail("client1@mail.ru");
        user3.setLogin("client1@mail.ru");
        user3.setPassword("client1");
        user3.setRole(roleClient);

        User user4 = new User();
        user4.setId(UUID.randomUUID().toString());
        user4.setEmail("client4@mail.ru");
        user4.setLogin("client4@mail.ru");
        user4.setPassword("client4");
        user4.setRole(roleClient);

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);

        saveToFile("/files/users.json", users);
        return users;
    }

    private static List<Client> loadClients(List<Role> roles) {
        Role role = null;
        for(Role r: roles){
            if(r.getCode().equals("CLIENT")){
                role = r;
                break;
            }
        }
        List<Client> clients = new LinkedList<>();
        Client m = new Client();
        m.setId(UUID.randomUUID().toString());
        m.setEmail("mailClient1@mail");
        m.setLogin("client1");
        m.setPassword("client1");
        m.setRole(role);

        Client m2 = new Client();
        m2.setId(UUID.randomUUID().toString());
        m2.setEmail("mailClient2@mail");
        m2.setLogin("client2");
        m2.setPassword("client2");
        m2.setRole(role);

        clients.add(m2);
        clients.add(m);

        saveToFile("/files/clients.json", clients);
        return clients;
    }

    private static List<Mentor> loadMentors(List<Role> roles) {
        Role role = null;
        for(Role r: roles){
            if(r.getCode().equals("MENTOR")){
                role = r;
                break;
            }
        }

        List<Mentor> mentors = new LinkedList<>();
        Mentor m = new Mentor();
        m.setId(UUID.randomUUID().toString());
        m.setEmail("mail@mail.ru");
        m.setLogin("mail@mail.ru");
        m.setPassword("mentor1");
        m.setRole(role);

        Mentor m2 = new Mentor();
        m2.setId(UUID.randomUUID().toString());
        m2.setEmail("mail2@mail.ru");
        m2.setLogin("mail2@mail.ru");
        m2.setPassword("mentor2");
        m2.setRole(role);

        mentors.add(m2);
        mentors.add(m);

        saveToFile("/files/mentors.json", mentors);
        return mentors;
    }


    public static List<Role> loadRole(){
        List<Role> roleList = new ArrayList<>();
        Role role = new Role();
        role.setId(UUID.randomUUID().toString());
        role.setCode("CLIENT");
        roleList.add(role);

        Role role1 = new Role();
        role1.setId(UUID.randomUUID().toString());
        role1.setCode("MENTOR");

        roleList.add(role1);
        saveToFile("/files/roles.json", roleList);
        return roleList;
    }

    public static void loadSettings(){
        List<Setting> settings = new ArrayList<>();
        Setting setting = new Setting();
        setting.setId(UUID.randomUUID().toString());
        setting.setName("Ограничение на кол-во консультаций в день");
        setting.setValue("0");

        Setting setting2 = new Setting();
        setting2.setId(UUID.randomUUID().toString());
        setting2.setName("Ограничение на кол-во консультаций в месяц");
        setting2.setValue("0");

        settings.add(setting2);
        settings.add(setting);

        saveToFile("/files/settings.json", settings);
    }



    private static <T> void saveToFile(String path, T data){
        String filePath = Loader.class.getResource(path).getPath();
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            String str = objectMapper.writeValueAsString(data);
            fileWriter.write(str);
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
//        try (PrintWriter pr = new PrintWriter(Loader.class.getResource(path).getPath())) {
//            String str = objectMapper.writeValueAsString(data);
//            System.out.println(str);
//            pr.println(str);
//        } catch (IOException ex) {
//            throw new RuntimeException(ex.getMessage());
//        }
    }

}
