package net.nwrn.pf_contest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PfContestApplication {

	public static void main(String[] args) {
		SpringApplication.run(PfContestApplication.class, args);
	}

}
