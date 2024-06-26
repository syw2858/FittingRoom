package net.nwrn.pf_contest.users.service;

import jakarta.servlet.http.HttpServletResponse;

public interface UserService {
    public void join(String userId, String password, HttpServletResponse response);
    public void login(String userId, String password, HttpServletResponse response);
    public void logout(Long id, HttpServletResponse response);

}
