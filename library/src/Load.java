
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import org.javabrain.annotation.FillJson;
import org.javabrain.util.data.Json;


/**
 *
 * @author Qnaltop
 */
public class Load {

    public Load() {
        Class<?> aliClass = this.getClass();
        Field[] field = aliClass.getDeclaredFields();

        for (Field fld : field) {
            System.out.println(fld);
            Annotation annotation = fld.getAnnotation(FillJson.class);
            if (annotation != null && annotation instanceof FillJson) {
                FillJson data = (FillJson) annotation;
                
            }
        }
    }
    
}
