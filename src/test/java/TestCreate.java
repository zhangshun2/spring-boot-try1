import com.baizhi.csa.App;
import com.baizhi.csa.dao.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class TestCreate {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        System.out.println(userMapper.selectAll());
    }
}
