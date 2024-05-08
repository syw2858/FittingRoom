package net.nwrn.pf_contest.exception;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

@Service
public class ExceptionServiceImpl implements ExceptionService {
    @Override
    public String generateMessage() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "EXCEPTION\n" + now.format(formatter);
    }

    @Override
    public String encode(String message) {
        byte[] byteMessage = message.getBytes();
        byte[] encoded = Base64.getEncoder().encode(byteMessage);

        return new String(encoded);
    }

    @Override
    public String decode(String message) {
        message = message.replace(' ', '+');
        byte[] decoded = Base64.getDecoder().decode(message);
        return new String(decoded);
    }

    @Override
    public String redirect(String url, String message) {
        String encoded = encode(message);
        return "redirect:" + url + "?errorMessage=" + encoded;
    }
}
