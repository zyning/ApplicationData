package application;

import application.domain.Applications;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

public class ApplicationOpsTest {

    @Resource
    private ApplicationOps applicationOps;

    @Before
    public void setUp() throws Exception {
        applicationOps = new ApplicationOps();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void insertApp() throws Exception {
        String companyName = "test_company_junit";
        String positionName = "test_position_junit";
        String jobId = "test_id";
        Integer status = 1;

        int returnValue = applicationOps.insertApp(companyName, positionName, jobId, status);
        assertEquals(returnValue, 1);
    }

    @Test
    public void selectApp() throws Exception {
        List<Applications> outputList = applicationOps.selectByCompanyName("test_company_1");
        assertEquals("test_company_1", outputList.get(0).getCompanyName());
    }
}