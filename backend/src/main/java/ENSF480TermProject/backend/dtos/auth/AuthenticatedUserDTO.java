package ENSF480TermProject.backend.dtos.auth;

import ENSF480TermProject.backend.models.RegisteredUser;

public class AuthenticatedUserDTO {
    private RegisteredUser user;
    private boolean isAdmin;

    public AuthenticatedUserDTO(RegisteredUser user, boolean isAdmin) {
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

