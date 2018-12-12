package org.javabrain.util.web.service;

import java.io.File;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * @passwor qikbrvpecjtodqjp
 * @email javabrain.email@gmail.com
 * @author Fernando García
 * @version 0.0.1
 */
public class Email {
    
    public static boolean sendMail(String destination, String affair, String message) {
        try {
            Properties properties = new Properties();
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.setProperty("mail.smtp.starttls.enable", "true");
            properties.setProperty("mail.smtp.port", "587");
            properties.setProperty("mail.smtp.user", "javabrain.email@gmail.com");//se manda el usuario del correo
            properties.setProperty("mail.smtp.auth", "true");

            //aqui se construye el cuerpo del correo
            Session session = Session.getDefaultInstance(properties, null);//inicia secion
            BodyPart texto = new MimeBodyPart();
            texto.setText(message);//se manda el mensaje

            //se le manda el texto
            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(texto);

            MimeMessage mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress("javabrain.email@gmail.com"));//quien enviara el mensaje
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destination));//quien lo resive
            mensaje.setSubject(affair);
            mensaje.setContent(multipart,"text/html");

            Transport transport = session.getTransport("smtp");
            transport.connect("javabrain.email@gmail.com", "qikbrvpecjtodqjp");//se valida el inicio
            transport.sendMessage(mensaje, mensaje.getAllRecipients());//se envia el mensaje
            transport.close();

            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
    
    public static boolean sendMail(String destination, String affair, String message, File attached) {
        try {
            Properties properties = new Properties();
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.setProperty("mail.smtp.starttls.enable", "true");
            properties.setProperty("mail.smtp.port", "587");
            properties.setProperty("mail.smtp.user","javabrain.email@gmail.com");//se manda el usuario del correo
            properties.setProperty("mail.smtp.auth", "true");

            //aqui se construye el cuerpo del correo
            Session session = Session.getDefaultInstance(properties, null);//inicia secion
            BodyPart texto = new MimeBodyPart();
            texto.setText(message);//se manda el mensaje
            BodyPart adjunto = new MimeBodyPart();

            if (attached != null) {   //para mandar el archivo adjunto
                adjunto.setDataHandler(new DataHandler(new FileDataSource(attached.getPath())));
                adjunto.setFileName(attached.getName());
            }

            //se le manda el texto
            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(texto);

            //pos si hay archivo
            if (!attached.getPath().equals("")) {
                multipart.addBodyPart(adjunto);
            }

            MimeMessage mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress("javabrain.email@gmail.com"));//quien enviara el mensaje
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destination));//quien lo resive
            mensaje.setSubject(affair);
            mensaje.setContent(multipart,"text/html");

            Transport transport = session.getTransport("smtp");
            transport.connect("javabrain.email@gmail.com","qikbrvpecjtodqjp");//se valida el inicio
            transport.sendMessage(mensaje, mensaje.getAllRecipients());//se envia el mensaje
            transport.close();

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
     
    
    public static boolean sendMail(String account,String code,String destination, String affair, String message) {
        try {
            Properties properties = new Properties();
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.setProperty("mail.smtp.starttls.enable", "true");
            properties.setProperty("mail.smtp.port", "587");
            properties.setProperty("mail.smtp.user",account);//se manda el usuario del correo
            properties.setProperty("mail.smtp.auth", "true");

            //aqui se construye el cuerpo del correo
            Session session = Session.getDefaultInstance(properties, null);//inicia secion
            BodyPart texto = new MimeBodyPart();
            texto.setText(message);//se manda el mensaje

            //se le manda el texto
            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(texto);

            MimeMessage mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(account));//quien enviara el mensaje
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destination));//quien lo resive
            mensaje.setSubject(affair);
            mensaje.setContent(multipart,"text/html");

            Transport transport = session.getTransport("smtp");
            transport.connect(account,code);//se valida el inicio
            transport.sendMessage(mensaje, mensaje.getAllRecipients());//se envia el mensaje
            transport.close();

            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
    
    public static boolean sendMail(String account,String code,String destination, String affair, String message, File attached) {
        try {
            Properties properties = new Properties();
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.setProperty("mail.smtp.starttls.enable", "true");
            properties.setProperty("mail.smtp.port", "587");
            properties.setProperty("mail.smtp.user",account);//se manda el usuario del correo
            properties.setProperty("mail.smtp.auth", "true");

            //aqui se construye el cuerpo del correo
            Session session = Session.getDefaultInstance(properties, null);//inicia secion
            BodyPart texto = new MimeBodyPart();
            texto.setText(message);//se manda el mensaje
            BodyPart adjunto = new MimeBodyPart();

            if (attached != null) {   //para mandar el archivo adjunto
                adjunto.setDataHandler(new DataHandler(new FileDataSource(attached.getPath())));
                adjunto.setFileName(attached.getName());
            }

            //se le manda el texto
            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(texto);

            //pos si hay archivo
            if (!attached.getPath().equals("")) {
                multipart.addBodyPart(adjunto);
            }

            MimeMessage mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(account));//quien enviara el mensaje
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destination));//quien lo resive
            mensaje.setSubject(affair);
            mensaje.setContent(multipart,"text/html");

            Transport transport = session.getTransport("smtp");
            transport.connect(account,code);//se valida el inicio
            transport.sendMessage(mensaje, mensaje.getAllRecipients());//se envia el mensaje
            transport.close();

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
