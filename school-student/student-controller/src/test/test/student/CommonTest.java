package student;

import com.lgren.school.student.pojo.Student;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static java.util.Optional.ofNullable;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("/applicationContext.xml")
public class CommonTest {
    @Test
    public void 反射工具使用() {
        System.out.println(ReflectionUtils.findMethod(Student.class,"toString"));
    }

    @Test
    public void BeanUtils的使用() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Student student = new Student();
        student.setRealName("sada");
        student.setBirthday(new Date());

//        StudentVO studentVO = new StudentVO();
        Map map = BeanUtils.describe(student);
        Map map1 = new HashMap();
        map1.putAll(map);
        System.out.println(map1);
    }

    @Test
    public void optional的使用() {
        Integer x = null;
        System.out.println(ofNullable(x).map(o -> o > 0 && o < 3).orElse(false));
    }

}
