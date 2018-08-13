package student;

import com.lgren.school.student.pojo.Student;
import org.junit.Test;
import org.springframework.util.ReflectionUtils;

public class CommonTest {
    @Test
    public void 反射工具使用() {
        System.out.println(ReflectionUtils.findMethod(Student.class,"toString"));
    }
}
