<#if package?? && package != "">
package ${package};
</#if>

import org.javabrain.util.data.Type;
import org.javabrain.util.web.service.Ajax;

/**
 * @by JavaBrain
 * @author ${user}
 * ${date}
 */
public class ${name} extends Ajax {

    @Override
    public void post(String url, Map<Object, Object> params) {
        super.post(url, params);
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
