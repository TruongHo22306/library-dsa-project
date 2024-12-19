package org.example.library.utility;

public class SecurityContextHolder {
    private String password;
    private String username;

    private static SecurityContextHolder instance;

    private SecurityContextHolder() {
    }

    public static SecurityContextHolder getInstance() {
        if (instance == null) {
            instance = new SecurityContextHolder();
        }

        System.out.println("SecurityContextHolder.getInstance" + instance.getPassword());

        return instance;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void clear() {
        password = null;
    }
}
