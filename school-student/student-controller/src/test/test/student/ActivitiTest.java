package student;

import com.lgren.common.CResult;
import com.lgren.school.student.service.IActivitiCore;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 * @author Lgren
 * @create 2018-08-14 16:40
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class ActivitiTest {
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private IActivitiCore activitiCore;
    @Test
    public void deploy() {
        repositoryService.createDeployment().name("marketingTask")
                .addClasspathResource("diagrams/onFOrOne/marketingTask.bpmn")
                .addClasspathResource("diagrams/onFOrOne/marketingTask.png").deploy();
        System.out.println("部署成功");
    }

    @Test
    public void startProcess() {
        try {
            runtimeService.startProcessInstanceByKey("marketingTask");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("启动成功");
    }

    @Test
    public void listTask() {
        taskService.createTaskQuery().list();
        System.out.println(taskService.createTaskQuery().list());
    }

    @Test
    public void completeTaskByAssignee() {
        Task task = taskService.createTaskQuery().processDefinitionKey("marketingTask").singleResult();
        Map<String, Object> map = new HashMap<>();
        map.put("approver", activitiCore.getSuperior(task.getAssignee()));
        taskService.complete(task.getId(), map);
        System.out.println("----------------->>>>>>>>>>" + taskService.createTaskQuery().processDefinitionKey("marketingTask").singleResult());
    }

    @Test
    public void deleteDeploy() {
        String deployId = repositoryService.createDeploymentQuery().deploymentName("marketingTask").singleResult().getId();
        repositoryService.deleteDeployment(deployId, true);
        System.out.println("删除成功");
    }

}
