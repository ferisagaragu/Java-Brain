
import org.javabrain.util.data.Json;

public class Run{
    
    /*@FillJson(data = "conf.{dataTable.json}")
    public Json json;
    
    @FillJson(data = "conf.{neuron_example.json}")
    public Json json2;

    public Run() {
        Class<?> aliClass = this.getClass();
        Field[] field = aliClass.getDeclaredFields();

        for (Field fld : field) {
            Annotation annotation = fld.getAnnotation(FillJson.class);
            if (annotation != null && annotation instanceof FillJson) {
                try {
                    FillJson data = (FillJson) annotation;
                    Json j = (Json) fld.getType().newInstance();
                    j.setJSON(data.data());
                } catch (Exception e) {
                    System.out.print(e.getMessage());
                }
            }
        }
    }*/
    
    public static void main(String[] args) throws Exception {
        
    }

}
