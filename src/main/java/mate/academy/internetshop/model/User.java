package mate.academy.internetshop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import mate.academy.internetshop.lib.IdGenerator;

public class User {
    private Long id;
    private String name;
    private String login;
    private String password;
    private String token;
    private List<Order> orders;

    public User() {
        id = IdGenerator.getNextUserId();
        orders = new ArrayList<>();
        token = UUID.randomUUID().toString();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getToken() {
        return token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
