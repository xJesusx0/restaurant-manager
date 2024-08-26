package co.jesus.RestaurantManager.entities;

public class User{
    private final int id;
    private final String username;
    private final int roleId;

    public User(int id,String username,int roleId){
        this.id = id;
        this.username = username;
        this.roleId = roleId;
    }

    public int getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }

    public int getRoleId() {
        return roleId;
    }
}
