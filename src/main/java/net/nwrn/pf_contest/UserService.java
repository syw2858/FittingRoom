package net.nwrn.pf_contest;

import jakarta.servlet.http.HttpServletResponse;

public interface UserService {
    public void join(String userId, String password);
    public void login(String userId, String password, HttpServletResponse response);
}
