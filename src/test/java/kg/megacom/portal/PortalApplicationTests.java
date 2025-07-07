package kg.megacom.portal;

import kg.megacom.portal.controllers.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("dev")
class PortalApplicationTests {

    @Autowired
    private AuthController authController;
    @Autowired
    private EmployeeController employeeController;
    @Autowired
    private KnowledgeBaseController knowledgeBaseController;
    @Autowired
    private NewsController newsController;
    @Autowired
    private RepairRequestController repairRequestController;

    @Test
    void contextLoads() {
        assertNotNull(authController);
        assertNotNull(employeeController);
        assertNotNull(knowledgeBaseController);
        assertNotNull(newsController);
        assertNotNull(repairRequestController);
    }

}
