import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by Administrator on 2016/10/13.
 */
public class Test01Main {

    public static void main(String[] args) {
        ApplicationContext context = new FileSystemXmlApplicationContext("/bean.xml");

    }

}
