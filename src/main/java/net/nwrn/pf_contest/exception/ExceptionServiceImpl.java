package net.nwrn.pf_contest.exception;

import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.base64.Base64;
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

    @Override
    public String encode(String message) {
        byte[] byteMessage = message.getBytes();
        byte[] encoded = Base64.encode(byteMessage);

        return new String(encoded);
    }

    @Override
    public String decode(String message) {
        byte[] decoded = Base64.decode(message);
        return new String(decoded);
    }


}
