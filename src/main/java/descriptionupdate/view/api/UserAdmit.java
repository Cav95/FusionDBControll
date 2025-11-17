package descriptionupdate.view.api;

/**
 * Enum representing users who are allowed to admit changes.
 * Each user is identified by a unique name.
 */
public enum UserAdmit {
    CEPIUT("CEPIUT", "CEPIUT"),
    TEST("TEST", "TEST");

    private final String username;
    private final String psw;

    UserAdmit(String username, String psw) {
        this.username = username;
        this.psw = psw;
    }

    /**
     * Returns the name of the user.
     *
     * @return the name of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the ID of the user.
     *
     * @return the ID of the user
     */
    public String getPsw() {
        return psw;
    }

}
