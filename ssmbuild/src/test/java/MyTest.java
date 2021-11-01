import com.mildlamb.pojo.Books;
import com.mildlamb.service.BookService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void test(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        BookService bookServiceImpl = ac.getBean("bookServiceImpl", BookService.class);
        for (Books selectBook : bookServiceImpl.selectBooks()) {
            System.out.println(selectBook);
        }
    }
}
