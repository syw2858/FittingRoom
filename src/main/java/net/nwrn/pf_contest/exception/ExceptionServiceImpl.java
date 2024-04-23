package net.nwrn.pf_contest.exception;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ExceptionServiceImpl implements ExceptionService {


    @Override
    public String generateMessage() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "EXCEPTION\n" + now.format(formatter);
    }
}
