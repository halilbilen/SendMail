package mail;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.*;

public class MailAl {
    public static final String USERNAME = "xxx@gmail.com";
    public static final String PASSWORD = "xxx";
    public static void main(String[] args) throws Exception 
    {
        try{
        // 1. Posta alma için özellikleri belirledik.
        Properties props = new Properties();
        props.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.pop3.port", "995");
        props.put("mail.pop3.host", "pop.gmail.com");
        props.put("mail.pop3.user", USERNAME);
        props.put("mail.store.protocol", "pop3");

        // 2. Bir javax.mail.Authenticator nesnesi oluşturuyor
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        };

        // 3. Mail oturumu oluşturuluyor.
        Session session = Session.getDefaultInstance(props, auth);

        // 4. POP3 store sağlayıcısı edinir ve stora bağlanır.
        Store store = session.getStore("pop3");
        store.connect("pop.gmail.com", USERNAME, PASSWORD);

        // 5. Gelen kutusunu alır ve storedaki gelen kutusunu açar.
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);

        // 6. Mesajları gelen kutusundan geri alır.
        Message[] message = inbox.getMessages();
        for (int i=0; i <message.length ; ++i) {//5 adet mail alıyoruz
            String from = "unknown";	
            Message msg = message[i];
         



   
       //mesajı gonderen kısının maıl adresini alır
         if (msg.getFrom().length >= 1) 
        {
          from = msg.getFrom()[0].toString();
        }         
           String subject = msg.getSubject();
           
        System.out.println(i+1+" ---- "+"Gönderen : " + from);
        System.out.println("Mail Basligi : " + subject);
          
        }
        // 7. Gelen kutusu ve store kapatılır.
        inbox.close(false);
        store.close();
        }
        catch(Exception hata){
            System.out.println(hata.getMessage()); //hata varsa yakalanıyor
        }
    }
    
}
