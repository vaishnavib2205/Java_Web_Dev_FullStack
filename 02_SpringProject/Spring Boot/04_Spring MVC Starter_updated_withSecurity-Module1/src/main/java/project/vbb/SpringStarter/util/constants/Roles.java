package project.vbb.SpringStarter.util.constants;

public enum Roles {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN"),
    EDITOR("ROLE_EDITOR");
    
    private String role;

    // Constructor to initialize the role
    private Roles(String role) {
        this.role = role;
    }

    // Getter method to retrieve the role string
    public String getRole() {
        return role;
    }
}
