package student.service.serviceImpl;

import com.lgren.school.student.service.IActivitiCore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class ActivitiCoreImplTest {
    @Autowired
    IActivitiCore activitiCore;
    @Test
    public void queryTaskList() {
        activitiCore.queryTaskList();
    }

    @Test
    public void queryTaskByTaskId() {
    }

    @Test
    public void queryTaskByAssignee() {
    }

    @Test
    public void completeTaskByTaskId() {
    }

    @Test
    public void completeTaskByAssignee() {
    }

    @Test
    public void queryProcessList() {
    }

    @Test
    public void queryDeployList() {
    }
}