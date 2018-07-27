package student.service.serviceImpl;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.lgren.school.student.service.IActivitiCore;
import com.lgren.school.student.service.IStudentService;
import com.lgren.school.student.service.aop.NotNullTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class ActivitiCoreImplTest {
    @Autowired
    private IActivitiCore activitiCore;

    @Autowired
    private IStudentService studentService;

    private Date nowDate = new Date();

    @Test
    public void aopTest() {
        List list = Lists.newArrayList(1,2,43,2);
        Set set = Sets.newHashSet(4,3,6,2);
        Map map = ImmutableMap.of(1,3,2,2,3,1,5,3);

        List list1 = Lists.newArrayList();
        Set set1 = Sets.newHashSet();
        Map map1 = new HashMap();
        System.out.println("\n");
        System.out.println(studentService.aopTest(1,"32","43",nowDate,3232L,map,list,set));
        System.out.println("\n");
    }

}