/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;


import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;


public class MailAtma 
{

   public static void main(String[] args) throws MessagingException 
   {
       //username tanımladık
        final String username = "xxxx@gmail.com"; 
        //pass tanımladık
        final String password = "xxxx";
        String mailSubject = "BASLIK DENEME";
	String mailBody = "selam";
        Properties p = new Properties();
        //mail hizmeti verecek hostu belirledik
        p.setProperty("mail.host", "smtp.gmail.com");
        //Security ayarları SSL
        p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); 
        //portumuzu belirledik
        p.setProperty("mail.smtp.socketFactory.port", "465");
        p.put("mail.transport.protocol", "smtp");   
        //Sunucu mail gönderirken şifre istiyor mu kontrolünü true yaptık.
        p.put("mail.smtp.auth", "true");
        //True ise, herhangi bir oturum açma komutu vermeden 
        p.put("mail.smtp.starttls.enable","true");
        //önce bağlantıyı TLS korumalı bir bağlantıya geçirmek için STARTTLS komutunun 
        /*sunucu tarafından destekleniyorsa) 
        kullanılmasını sağlar.Desteklemiyorsa TLS kullanmadan devam edilir.*/
        //(TLS)Tasıma katmanı guvenligi
        //STARTTLS ise mevcut kurulmuş olan güvenli olmayan bir bağlantıyı 
        //almak ve  TLS / SSL ile guvenli bir bağlantıya çevirmek için bir yöntemdir.
        
        p.put("mail.debug","true");
        //Mailin hangi protokol ile depolanacağını belirledik.
        p.put("mail.store.protocol","pop3"); 
        //Mailin hangi port üzerinden gönderileceğini belirledik.
        p.put("mail.smtp.port","587");
       
        

        //oturum acma islemi yapılmaktadır
        try{
            Session mailSession = Session.getDefaultInstance(p, new Authenticator()
                    {
                        protected PasswordAuthentication getPasswordAuthentication()
                        {
                            return new PasswordAuthentication(username, password);
                        }
                    });
            //Mail gönderme işlemini gerçekleştirecek nesnemizi oluşturuyoruz.
            Transport transport = mailSession.getTransport();
            //Mail değişkenimizi oluşturuyoryuz. Burada tüm mail bilgleri yer almaktadır.    
            MimeMessage mail = new MimeMessage(mailSession);
            //mimemessage maili taklit eder
            mail.setSubject(mailSubject);//mail basliği dolduruluyor
	    mail.setContent(mailBody, "text/html");//mail icerigi dolduruluyor
            //mesaj kimden gidecek onu belirledik
            mail.setFrom(new InternetAddress("xxxx@gmail.com"));
            //mail kime gönderilecek onu belirledik
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress("yyyy@gmail.com"));
            mail.setText("MESAJ DENEME  ");
            //sunucu ile bağlantı kuruluyor
            transport.connect("smtp.gmail.com",username,password);
            //mail gonderiliyor
            transport.sendMessage(mail, mail.getAllRecipients());
            transport.close();//baglantı kesiliyor
            //basarılı mesajı veriliyor ve islem bitiyor
            System.out.println("Başarılı");
        }
        catch(Exception hata){
            //hata varsa yakalanıyor
            System.out.println(hata.getMessage()); 
        }
   }
   

}
