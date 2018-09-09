<#if package?? && package != "">
package ${package};
</#if>

import java.util.Map;
import org.javabrain.util.data.Type;
import org.javabrain.util.web.service.Ajax;

/**
 * @by JavaBrain
 * @author ${user}
 * ${date}
 */
public class ${name} extends Ajax {

    @Override
    public void put(String url, Map<Object, Object> params) {
        super.put(url, params);
    }

    @Override
    public void postconstruct() {

    }

    @Override
    public void success(Type resp) {

    }

    @Override
    public void error(Type resp, int status) {

    }

    @Override
    public void always() {

    }

}
