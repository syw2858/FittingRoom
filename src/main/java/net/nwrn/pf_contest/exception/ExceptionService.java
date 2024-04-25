package net.nwrn.pf_contest.exception;

public interface ExceptionService {
    String generateMessage();
    String encode(String message);
    String decode(String message);
}
