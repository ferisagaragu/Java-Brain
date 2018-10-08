package org.javabrain.util.web.service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.javabrain.annotation.ajax.*;
import org.javabrain.util.data.Type;

import java.util.Map;

import org.javabrain.util.data.Text;

/***
 * @author Fernando Isaías García Aguirre
 * @version 0.0.3
 * @implNote esta clase funciona en FX con Platform.runLater(() -> {});
 */
public class Ajax {

    public static void petition(Object o) {
        Field[]  fields = o.getClass().getDeclaredFields();
        Method[] methods = o.getClass().getDeclaredMethods();
        String url = "";

        Get getAnn = (Get) o.getClass().getAnnotation(Get.class);
        Post postAnn = (Post) o.getClass().getAnnotation(Post.class);
        Update putAnn = (Update) o.getClass().getAnnotation(Update.class);
        Delete deleteAnn = (Delete) o.getClass().getAnnotation(Delete.class);
        org.javabrain.annotation.ajax.Ajax ajaxAnn = o.getClass().getAnnotation(org.javabrain.annotation.ajax.Ajax.class);
        
        Method postconstructMtd = null;
        Method successMtd = null;
        Method errorMtd = null;
        Method alwaysMtd = null;
        Method processMtd = null;
        Method respMtd = null;

        try {

            for(Field fld:fields) {
                Annotation resp = fld.getAnnotation(Response.class);
                if (resp != null) {
                    respMtd = o.getClass().getMethod("is"+Text.upperFirst(fld.getName()));
                }
            }

            for (Method mtd : methods) {
                
                Annotation postconstructAnn = mtd.getAnnotation(Postconstruct.class);
                Annotation successAnn = mtd.getAnnotation(Success.class);
                Annotation errorAnn = mtd.getAnnotation(org.javabrain.annotation.ajax.Error.class);
                Annotation alwaysAnn = mtd.getAnnotation(Always.class);
                Annotation processAnn = mtd.getAnnotation(org.javabrain.annotation.ajax.Process.class);
                
                if (postconstructAnn != null) {
                    postconstructMtd = mtd;
                }

                if (successAnn != null) {
                    successMtd = mtd;
                }

                if (errorAnn != null) {
                    errorMtd = mtd;
                }

                if (alwaysAnn != null) {
                    alwaysMtd = mtd;
                }
                
                if (processAnn != null) {
                    processMtd = mtd;
                }
            }

            if (postconstructMtd != null) {
                postconstructMtd.setAccessible(true);
            }

            if (successMtd != null) {
                successMtd.setAccessible(true);
            }

            if (errorMtd != null) {
                errorMtd.setAccessible(true);
            }

            if (processMtd != null) {
                processMtd.setAccessible(true);
            }

            if (alwaysMtd != null) {
                alwaysMtd.setAccessible(true);
            }

            if (getAnn != null) {
                url = getAnn.url();
                rutineGet(postconstructMtd,successMtd,errorMtd,alwaysMtd,o,url);
                return;
            }

            if (postAnn != null) {
                url = postAnn.url();
                rutinePost(postconstructMtd, successMtd, errorMtd, alwaysMtd, o, url, getParamms(fields, o));
                return;
            }
            
            if (putAnn != null) {
                url = putAnn.url();
                rutinePut(postconstructMtd, successMtd, errorMtd, alwaysMtd, o, url, getParamms(fields, o));
                return;
            }
            
            if (deleteAnn != null) {
                url = deleteAnn.url();
                rutineDelete(postconstructMtd, successMtd, errorMtd, alwaysMtd, o, url, getParamms(fields, o));
                return;
            }
            
            if (ajaxAnn != null) {
                rutineAjax(postconstructMtd, successMtd, errorMtd, alwaysMtd, processMtd, o,respMtd);
            }

        } catch (Exception e) {
            System.err.println("Upps" + e.getMessage());
        }
        
    }

    public static void petitions(Object... o) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                for (Object obs : o) {

                    Field[] fields = obs.getClass().getDeclaredFields();
                    Method[] methods = obs.getClass().getDeclaredMethods();
                    String url = "";

                    Annotation get = obs.getClass().getAnnotation(Get.class);
                    Annotation post = obs.getClass().getAnnotation(Post.class);
                    Annotation put = obs.getClass().getAnnotation(Update.class);
                    Annotation delete = obs.getClass().getAnnotation(Delete.class);
                    Annotation ajax = obs.getClass().getAnnotation(org.javabrain.annotation.ajax.Ajax.class);

                    Get getAnn = (Get) get;
                    Post postAnn = (Post) post;
                    Update putAnn = (Update) put;
                    Delete deleteAnn = (Delete) delete;
                    org.javabrain.annotation.ajax.Ajax ajaxAnn = (org.javabrain.annotation.ajax.Ajax) ajax;

                    Method postconstructMtd = null;
                    Method successMtd = null;
                    Method errorMtd = null;
                    Method alwaysMtd = null;
                    Method processMtd = null;
                    Method respMtd = null;

                    try {

                        for(Field fld:fields) {
                            Annotation resp = fld.getAnnotation(Response.class);
                            if (resp != null) {
                                respMtd = obs.getClass().getMethod("is"+Text.upperFirst(fld.getName()));
                            }
                        }

                        for (Method mtd : methods) {

                            Annotation postconstructAnn = mtd.getAnnotation(Postconstruct.class);
                            Annotation successAnn = mtd.getAnnotation(Success.class);
                            Annotation errorAnn = mtd.getAnnotation(org.javabrain.annotation.ajax.Error.class);
                            Annotation alwaysAnn = mtd.getAnnotation(Always.class);
                            Annotation processAnn = mtd.getAnnotation(org.javabrain.annotation.ajax.Process.class);

                            if (postconstructAnn != null) {
                                postconstructMtd = mtd;
                            }

                            if (successAnn != null) {
                                successMtd = mtd;
                            }

                            if (errorAnn != null) {
                                errorMtd = mtd;
                            }

                            if (alwaysAnn != null) {
                                alwaysMtd = mtd;
                            }

                            if (processAnn != null) {
                                processMtd = mtd;
                            }
                        }

                        if (postconstructMtd != null) {
                            postconstructMtd.setAccessible(true);
                        }

                        if (successMtd != null) {
                            successMtd.setAccessible(true);
                        }

                        if (errorMtd != null) {
                            errorMtd.setAccessible(true);
                        }

                        if (processMtd != null) {
                            processMtd.setAccessible(true);
                        }

                        if (alwaysMtd != null) {
                            alwaysMtd.setAccessible(true);
                        }

                        if (getAnn != null) {
                            url = getAnn.url();
                            get(postconstructMtd, successMtd, errorMtd, alwaysMtd, obs, url);
                            return;
                        }

                        if (postAnn != null) {
                            url = postAnn.url();
                            post(postconstructMtd, successMtd, errorMtd, alwaysMtd, obs, url, getParamms(fields, o));
                            return;
                        }

                        if (putAnn != null) {
                            url = putAnn.url();
                            put(postconstructMtd, successMtd, errorMtd, alwaysMtd, obs, url, getParamms(fields, o));
                            return;
                        }

                        if (deleteAnn != null) {
                            url = deleteAnn.url();
                            delete(postconstructMtd, successMtd, errorMtd, alwaysMtd, obs, url, getParamms(fields, o));
                            return;
                        }

                        if (ajaxAnn != null) {
                            ajax(postconstructMtd, successMtd, errorMtd, alwaysMtd, processMtd, obs,respMtd);
                        }

                    } catch (Exception e) {
                        System.err.println("Upps" + e.getMessage());
                    }
                }

            }
        });
        thread.start();
    }

    private static Map getParamms(Field[] fields,Object o) {
        Map paramms = null;
        try {
        
            for (Field fld : fields) {
                Annotation ann = fld.getAnnotation(Paramms.class);

                if (ann != null) {
                    fld.setAccessible(true);
                    Method m = o.getClass().getMethod("get" + Text.upperFirst(fld.getName()));
                    paramms = (Map) m.invoke(o);
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return paramms;
    }

    //PETICIONES CON HILOS
    private static void rutineGet(Method pos, Method succ, Method err, Method alw, Object o,String url) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                get(pos,succ,err,alw,o,url);
            }
        });
        t.start();
    }

    private static void rutinePost(Method pos, Method succ, Method err, Method alw, Object o, String url, Map params) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                post(pos, succ, err, alw, o, url, params);
            }
        });
        t.start();
    }

    private static void rutinePut(Method pos, Method succ, Method err, Method alw, Object o,String url,Map params) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                put(pos,succ,err,alw,o,url,params);
            }
        });
        thread.start();
    }
    
    private static void rutineDelete(Method pos, Method succ, Method err, Method alw, Object o,String url,Map params) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                delete(pos,succ,err,alw,o,url,params);
            }
        });
        thread.start();
    }

    private static void rutineAjax(Method pos, Method succ, Method err, Method alw,Method proc, Object o,Method resp) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                ajax(pos,succ,err,alw,proc,o,resp);
            }
        });
        thread.start();
    }


    //PROCESOS INTERNOS DE PETICION
    private static void get(Method pos, Method succ, Method err, Method alw, Object o, String url) {
        Type resp = null;
        try {

            if (pos != null) {
                if (pos.getParameterCount() == 0) {
                    pos.invoke(o);
                } else {
                    System.err.println("El postconstructor no puede llevar parametros");
                    return;
                }
            }
            resp = Petition.doGet(url);
            if (succ != null && resp.STATUS.equals(200)) {
                switch (succ.getParameterCount()) {
                    case 0:
                        succ.invoke(o);
                        break;
                    case 1:

                        for (Parameter p : succ.getParameters()) {
                            if (p.getType().getName().equals("org.javabrain.util.data.Type")) {
                                succ.invoke(o, resp);
                            } else if (p.getType().getName().equals("int")) {
                                succ.invoke(o, resp.STATUS);
                            }
                        }

                        break;
                    case 2:

                        boolean isTypeFirst = false;

                        if (succ.getParameters()[0].getType().getName().equals("org.javabrain.util.data.Type")) {
                            isTypeFirst = true;
                        }


                        if (isTypeFirst) {
                            succ.invoke(o, resp, resp.STATUS);
                        } else {
                            succ.invoke(o, resp.STATUS, resp);
                        }

                        break;
                    default:
                        System.err.println("El metodo succes no puede tener mas de dos parametros y estos deben ser int y Type");

                }
            } else {

                if (err != null) {
                    switch (err.getParameterCount()) {
                        case 0:
                            err.invoke(o);
                            break;
                        case 1:

                            for (Parameter p : err.getParameters()) {
                                if (p.getType().getName().equals("org.javabrain.util.data.Type")) {
                                    err.invoke(o, resp);
                                } else if (p.getType().getName().equals("int")) {
                                    err.invoke(o, resp.STATUS);
                                }
                            }

                            break;
                        case 2:

                            boolean isTypeFirst = false;

                            if (err.getParameters()[0].getType().getName().equals("org.javabrain.util.data.Type")) {
                                isTypeFirst = true;
                            }


                            if (isTypeFirst) {
                                err.invoke(o, resp, resp.STATUS);
                            } else {
                                err.invoke(o, resp.STATUS, resp);
                            }

                            break;
                        default:
                            System.err.println("El metodo error no puede tener mas de dos parametros y estos deben ser int y Type");
                    }
                }
            }

            if (alw != null) {
                if (alw.getParameterCount() == 0) {
                    alw.invoke(o);
                } else {
                    System.err.println("El metodo always no puede contener parametros");
                }
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    private static void post(Method pos, Method succ, Method err, Method alw, Object o, String url, Map params) {
        if (params != null) {
            Type resp = null;

            try {

                if (pos != null) {
                    if (pos.getParameterCount() == 0) {
                        pos.invoke(o);
                    } else {
                        System.err.println("El postconstructor no puede llevar parametros");
                        return;
                    }
                }
                resp = Petition.doPost(url, params);
                if (succ != null && resp.STATUS.equals(200)) {
                    switch (succ.getParameterCount()) {
                        case 0:
                            succ.invoke(o);
                            break;
                        case 1:

                            for (Parameter p : succ.getParameters()) {
                                if (p.getType().getName().equals("org.javabrain.util.data.Type")) {
                                    succ.invoke(o, resp);
                                } else if (p.getType().getName().equals("int")) {
                                    succ.invoke(o, resp.STATUS);
                                }
                            }

                            break;
                        case 2:

                            boolean isTypeFirst = false;

                            if (succ.getParameters()[0].getType().getName().equals("org.javabrain.util.data.Type")) {
                                isTypeFirst = true;
                            }


                            if (isTypeFirst) {
                                succ.invoke(o, resp, resp.STATUS);
                            } else {
                                succ.invoke(o, resp.STATUS, resp);
                            }

                            break;
                        default:
                            System.err.println("El metodo succes no puede tener mas de dos parametros y estos deben ser int y Type");

                    }
                } else {

                    if (err != null) {
                        switch (err.getParameterCount()) {
                            case 0:
                                err.invoke(o);
                                break;
                            case 1:

                                for (Parameter p : err.getParameters()) {
                                    if (p.getType().getName().equals("org.javabrain.util.data.Type")) {
                                        err.invoke(o, resp);
                                    } else if (p.getType().getName().equals("int")) {
                                        err.invoke(o, resp.STATUS);
                                    }
                                }

                                break;
                            case 2:

                                boolean isTypeFirst = false;

                                if (err.getParameters()[0].getType().getName().equals("org.javabrain.util.data.Type")) {
                                    isTypeFirst = true;
                                }


                                if (isTypeFirst) {
                                    err.invoke(o, resp, resp.STATUS);
                                } else {
                                    err.invoke(o, resp.STATUS, resp);
                                }

                                break;
                            default:
                                System.err.println("El metodo error no puede tener mas de dos parametros y estos deben ser int y Type");
                        }
                    }
                }

                if (alw != null) {
                    if (alw.getParameterCount() == 0) {
                        alw.invoke(o);
                    } else {
                        System.err.println("El metodo always no puede contener parametros");
                    }
                }

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }


        } else {
            System.err.println("The petition don't have parammeters");
        }
    }

    private static void put(Method pos, Method succ, Method err, Method alw, Object o, String url, Map params) {
        if (params != null) {
            Type resp = null;

            try {

                if (pos != null) {
                    if (pos.getParameterCount() == 0) {
                        pos.invoke(o);
                    } else {
                        System.err.println("El postconstructor no puede llevar parametros");
                        return;
                    }
                }
                resp = Petition.doPut(url, params);
                if (succ != null && resp.STATUS.equals(200)) {
                    switch (succ.getParameterCount()) {
                        case 0:
                            succ.invoke(o);
                            break;
                        case 1:

                            for (Parameter p : succ.getParameters()) {
                                if (p.getType().getName().equals("org.javabrain.util.data.Type")) {
                                    succ.invoke(o, resp);
                                } else if (p.getType().getName().equals("int")) {
                                    succ.invoke(o, resp.STATUS);
                                }
                            }

                            break;
                        case 2:

                            boolean isTypeFirst = false;

                            if (succ.getParameters()[0].getType().getName().equals("org.javabrain.util.data.Type")) {
                                isTypeFirst = true;
                            }


                            if (isTypeFirst) {
                                succ.invoke(o, resp, resp.STATUS);
                            } else {
                                succ.invoke(o, resp.STATUS, resp);
                            }

                            break;
                        default:
                            System.err.println("El metodo succes no puede tener mas de dos parametros y estos deben ser int y Type");

                    }
                } else {

                    if (err != null) {
                        switch (err.getParameterCount()) {
                            case 0:
                                err.invoke(o);
                                break;
                            case 1:

                                for (Parameter p : err.getParameters()) {
                                    if (p.getType().getName().equals("org.javabrain.util.data.Type")) {
                                        err.invoke(o, resp);
                                    } else if (p.getType().getName().equals("int")) {
                                        err.invoke(o, resp.STATUS);
                                    }
                                }

                                break;
                            case 2:

                                boolean isTypeFirst = false;

                                if (err.getParameters()[0].getType().getName().equals("org.javabrain.util.data.Type")) {
                                    isTypeFirst = true;
                                }


                                if (isTypeFirst) {
                                    err.invoke(o, resp, resp.STATUS);
                                } else {
                                    err.invoke(o, resp.STATUS, resp);
                                }

                                break;
                            default:
                                System.err.println("El metodo error no puede tener mas de dos parametros y estos deben ser int y Type");
                        }
                    }
                }

                if (alw != null) {
                    if (alw.getParameterCount() == 0) {
                        alw.invoke(o);
                    } else {
                        System.err.println("El metodo always no puede contener parametros");
                    }
                }

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

        } else {
            System.err.println("The petition don't have parammeters");
        }
    }

    private static void delete(Method pos, Method succ, Method err, Method alw, Object o, String url, Map params) {
        if (params != null) {
            Type resp = null;

            try {

                if (pos != null) {
                    if (pos.getParameterCount() == 0) {
                        pos.invoke(o);
                    } else {
                        System.err.println("El postconstructor no puede llevar parametros");
                        return;
                    }
                }
                resp = Petition.doDelete(url, params);
                if (succ != null && resp.STATUS.equals(200)) {
                    switch (succ.getParameterCount()) {
                        case 0:
                            succ.invoke(o);
                            break;
                        case 1:

                            for (Parameter p : succ.getParameters()) {
                                if (p.getType().getName().equals("org.javabrain.util.data.Type")) {
                                    succ.invoke(o, resp);
                                } else if (p.getType().getName().equals("int")) {
                                    succ.invoke(o, resp.STATUS);
                                }
                            }

                            break;
                        case 2:

                            boolean isTypeFirst = false;

                            if (succ.getParameters()[0].getType().getName().equals("org.javabrain.util.data.Type")) {
                                isTypeFirst = true;
                            }


                            if (isTypeFirst) {
                                succ.invoke(o, resp, resp.STATUS);
                            } else {
                                succ.invoke(o, resp.STATUS, resp);
                            }

                            break;
                        default:
                            System.err.println("El metodo succes no puede tener mas de dos parametros y estos deben ser int y Type");

                    }
                } else {

                    if (err != null) {
                        switch (err.getParameterCount()) {
                            case 0:
                                err.invoke(o);
                                break;
                            case 1:

                                for (Parameter p : err.getParameters()) {
                                    if (p.getType().getName().equals("org.javabrain.util.data.Type")) {
                                        err.invoke(o, resp);
                                    } else if (p.getType().getName().equals("int")) {
                                        err.invoke(o, resp.STATUS);
                                    }
                                }

                                break;
                            case 2:

                                boolean isTypeFirst = false;

                                if (err.getParameters()[0].getType().getName().equals("org.javabrain.util.data.Type")) {
                                    isTypeFirst = true;
                                }


                                if (isTypeFirst) {
                                    err.invoke(o, resp, resp.STATUS);
                                } else {
                                    err.invoke(o, resp.STATUS, resp);
                                }

                                break;
                            default:
                                System.err.println("El metodo error no puede tener mas de dos parametros y estos deben ser int y Type");
                        }
                    }
                }

                if (alw != null) {
                    if (alw.getParameterCount() == 0) {
                        alw.invoke(o);
                    } else {
                        System.err.println("El metodo always no puede contener parametros");
                    }
                }

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

        } else {
            System.err.println("The petition don't have parammeters");
        }
    }

    private static void ajax(Method pos, Method succ, Method err, Method alw, Method proc, Object o,Method resp) {
        try {

            if (pos != null) {
                if (pos.getParameterCount() == 0) {
                    pos.invoke(o);
                }
            }

            try {

                if (proc != null) {
                    if (proc.getParameterCount() == 0) {
                        proc.invoke(o);
                    }
                }

                if (resp == null) {
                    if (succ != null) {
                        if (succ.getParameterCount() == 0) {
                            succ.invoke(o);
                        }
                    }
                } else {
                    if (Boolean.parseBoolean(resp.invoke(o).toString())) {
                        if (succ != null) {
                            if (succ.getParameterCount() == 0) {
                                succ.invoke(o);
                            }
                        }
                    } else {
                        if (err != null) {
                            if (err.getParameterCount() == 0) {
                                err.invoke(o);
                            }
                        }
                    }
                }
            } catch (Exception ex) {

                if (err != null) {
                    if (err.getParameterCount() == 0) {
                        err.invoke(o);
                    }
                }
            }

            if (alw != null) {
                if (alw.getParameterCount() == 0) {
                    alw.invoke(o);
                }
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }


    }

}
