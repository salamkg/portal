package kg.megacom.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PortalApplication {

    public static void main(String[] args) {
        System.setProperty("java.security.krb5.conf", "C:/IdeaProjects/portal/krb5.conf");
        SpringApplication.run(PortalApplication.class, args);
    }

}
