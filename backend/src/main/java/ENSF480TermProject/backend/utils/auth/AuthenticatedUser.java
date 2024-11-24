package ENSF480TermProject.backend.utils.auth;

import ENSF480TermProject.backend.models.RegisteredUser;

public class AuthenticatedUser {
    private RegisteredUser user;
    private boolean isAdmin;

    public AuthenticatedUser(RegisteredUser user, boolean isAdmin) {
        this.user = user;
        this.isAdmin = isAdmin;
    }

    public RegisteredUser getUser() {
        return user;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}

