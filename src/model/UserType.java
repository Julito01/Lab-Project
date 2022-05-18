package model;

public class UserType {
    private String user;

    public UserType(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return this.user;
    }
}
