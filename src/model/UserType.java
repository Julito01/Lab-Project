package model;

public class UserType {
    private int codigo;
    private String user;

    public UserType(int codigo, String user) {
        this.codigo = codigo;
        this.user = user;
    }

    public String toString() {
        return this.user;
    }
}
