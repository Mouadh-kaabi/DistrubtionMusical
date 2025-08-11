package com.example.demo.rest;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class SendEmail {

	
	@Autowired
	private JavaMailSender javaMailSender;
	
	
	
	public void confirmAccount(String to, String subject, String nom, String prenom, String user, Integer id) throws MessagingException {

		String link;
		
		
		String body ="<!DOCTYPE html>" + 
				"<html>" + 
				"" + 
				"<head>" + 
				"    <title></title>" + 
				"    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />" + 
				"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">" + 
				"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />" + 
				"    <style type=\"text/css\">" + 
				"        @media screen {" + 
				"            @font-face {" + 
				"                font-family: 'Lato';" + 
				"                font-style: normal;" + 
				"                font-weight: 400;" + 
				"                src: local('Lato Regular'), local('Lato-Regular'), url(https://fonts.gstatic.com/s/lato/v11/qIIYRU-oROkIk8vfvxw6QvesZW2xOQ-xsNqO47m55DA.woff) format('woff');" + 
				"            }" + 
				"" + 
				"            @font-face {" + 
				"                font-family: 'Lato';" + 
				"                font-style: normal;" + 
				"                font-weight: 700;" + 
				"                src: local('Lato Bold'), local('Lato-Bold'), url(https://fonts.gstatic.com/s/lato/v11/qdgUG4U09HnJwhYI-uK18wLUuEpTyoUstqEm5AMlJo4.woff) format('woff');" + 
				"            }" + 
				"" + 
				"            @font-face {" + 
				"                font-family: 'Lato';" + 
				"                font-style: italic;" + 
				"                font-weight: 400;" + 
				"                src: local('Lato Italic'), local('Lato-Italic'), url(https://fonts.gstatic.com/s/lato/v11/RYyZNoeFgb0l7W3Vu1aSWOvvDin1pK8aKteLpeZ5c0A.woff) format('woff');" + 
				"            }" + 
				"" + 
				"            @font-face {" + 
				"                font-family: 'Lato';" + 
				"                font-style: italic;" + 
				"                font-weight: 700;" + 
				"                src: local('Lato Bold Italic'), local('Lato-BoldItalic'), url(https://fonts.gstatic.com/s/lato/v11/HkF_qI1x_noxlxhrhMQYELO3LdcAZYWl9Si6vvxL-qU.woff) format('woff');" + 
				"            }" + 
				"        }" + 
				"" + 
				"        /* CLIENT-SPECIFIC STYLES */" + 
				"        body," + 
				"        table," + 
				"        td," + 
				"        a {" + 
				"            -webkit-text-size-adjust: 100%;" + 
				"            -ms-text-size-adjust: 100%;" + 
				"        }" + 
				"" + 
				"        table," + 
				"        td {" + 
				"            mso-table-lspace: 0pt;" + 
				"            mso-table-rspace: 0pt;" + 
				"        }" + 
				"" + 
				"        img {" + 
				"            -ms-interpolation-mode: bicubic;" + 
				"        }" + 
				"" + 
				"        /* RESET STYLES */" + 
				"        img {" + 
				"            border: 0;" + 
				"            height: auto;" + 
				"            line-height: 100%;" + 
				"            outline: none;" + 
				"            text-decoration: none;" + 
				"        }" + 
				"" + 
				"        table {" + 
				"            border-collapse: collapse !important;" + 
				"        }" + 
				"" + 
				"        body {" + 
				"            height: 100% !important;" + 
				"            margin: 0 !important;" + 
				"            padding: 0 !important;" + 
				"            width: 100% !important;" + 
				"        }" + 
				"" + 
				"        /* iOS BLUE LINKS */" + 
				"        a[x-apple-data-detectors] {" + 
				"            color: inherit !important;" + 
				"            text-decoration: none !important;" + 
				"            font-size: inherit !important;" + 
				"            font-family: inherit !important;" + 
				"            font-weight: inherit !important;" + 
				"            line-height: inherit !important;" + 
				"        }" + 
				"" + 
				"        /* MOBILE STYLES */" + 
				"        @media screen and (max-width:600px) {" + 
				"            h1 {" + 
				"                font-size: 32px !important;" + 
				"                line-height: 32px !important;" + 
				"            }" + 
				"        }" + 
				"" + 
				"        /* ANDROID CENTER FIX */" + 
				"        div[style*=\"margin: 16px 0;\"] {" + 
				"            margin: 0 !important;" + 
				"        }" + 
				"    </style>" + 
				"</head>" + 
				"" + 
				"<body style=\"background-color: #f4f4f4; margin: 0 !important; padding: 0 !important;\">" + 
				"    <!-- HIDDEN PREHEADER TEXT -->" + 
				"    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">" + 
				"        <!-- LOGO -->" + 
				"        <tr>" + 
				"<!-- Head -->"+
				"            <td bgcolor=\"#ffffff\" align=\"center\">" + 
				"                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">" + 
				"                    <tr>" + 
				"                        <td align=\"center\" valign=\"top\" style=\"padding: 40px 10px 40px 10px;\"> </td>" + 
				"                    </tr>" + 
				"                </table>" + 
				"            </td>" + 
				"        </tr>" + 
				"        <tr>" + 
				"<!-- li ta7t Head -->"+
				"            <td bgcolor=\"#181b2c\" align=\"center\" style=\"padding: 0px 10px 0px 10px;\">" + 
				"                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">" + 
				"                    <tr>" + 
				"                        <td bgcolor=\"#ffffff\" align=\"center\" valign=\"top\" style=\"padding: 40px 20px 20px 20px; border-radius: 4px 4px 0px 0px; color: #111111; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 48px; font-weight: 400; letter-spacing: 4px; line-height: 48px;\">" + 
				"                            <h1 style=\"font-size: 48px; font-weight: 400; margin: 2;\">Smart Music "+"</h1> " + 
				"                        </td>" + 
				"                    </tr>" + 
				"                    <tr>" + 
				"                        <td bgcolor=\"#ffffff\" align=\"center\" valign=\"top\" style=\"padding: 40px 20px 20px 20px; border-radius: 4px 4px 0px 0px; color: #111111; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 48px; font-weight: 400; letter-spacing: 4px; line-height: 48px;\">" + 
				"                            <h1 style=\"font-size: 48px; font-weight: 400; margin: 2;\">Bonjour "+nom+" "+prenom+" "+"!</h1> <img src=\" https://img.icons8.com/clouds/100/000000/handshake.png\" width=\"125\" height=\"120\" style=\"display: block; border: 0px;\" />" + 
				"                        </td>" + 
				"                    </tr>" + 
				"                </table>" + 
				"            </td>" + 
				"        </tr>" + 
				"        <tr>" + 
				"            <td bgcolor=\"#dc5b93\" align=\"center\" style=\"padding: 0px 10px 0px 10px;\">" + 
				"                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">" + 
				"                    <tr>" + 
				"                        <td bgcolor=\"#ffffff\" align=\"left\" style=\"padding: 20px 30px 40px 30px; color: #666666; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 25px;\">" + 
				"                            <p style=\"margin: 0;\">Nous sommes ravis que vous commenciez. Tout d'abord, vous devez confirmer votre compte. Appuyez simplement sur le bouton ci-dessous.</p>" + 
				"<p style=\"margin: 0;\">http://192.168.1.111:4200/loginArt.</p>" + 
				"                        </td>" + 
				"                    </tr>" + 
				"                    <tr>" + 
				"                        <td bgcolor=\"#ffffff\" align=\"left\">" + 
				"                            <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" + 
				"                                <tr>" + 
				"                                    <td bgcolor=\"#ffffff\" align=\"center\" style=\"padding: 20px 30px 60px 30px;\">" + 
				"                                        <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" + 
				"                                            <tr>" + 
				"                                                <td align=\"center\" style=\"border-radius: 3px;\" bgcolor=\"#FFFFFF\"><a href="+" target=\"_blank\" style=\"font-size: 20px; font-family: Helvetica, Arial, sans-serif; color: #ffffff; text-decoration: none; color: #ffffff; text-decoration: none; padding: 15px 25px; border-radius: 2px; border: 1px solid #FFFFFF; display: inline-block;\">Confirm Account</a></td>" + 
				"                                            </tr>" + 
				"                                        </table>" + 
				"                                    </td>" + 
				"                                </tr>" + 
				"                            </table>" + 
				"                        </td>" + 
				"                    </tr> <!-- COPY -->" + 
				"                    <tr>" + 
				"                        <td bgcolor=\"#ffffff\" align=\"left\" style=\"padding: 0px 30px 0px 30px; color: #666666; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 25px;\">" + 
				"                            <p style=\"margin: 0;\">Si cela ne fonctionne pas, copiez et collez le lien suivant dans votre navigateur:</p>" + 
				"                        </td>" + 
				"                    </tr> <!-- COPY -->" + 
				"                    <tr>" + 
				"                        <td bgcolor=\"#ffffff\" align=\"left\" style=\"padding: 20px 30px 20px 30px; color: #666666; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 25px;\">" + 
				"                            <p style=\"margin: 0;\"><a href=\"#\" target=\"_blank\" style=\"color: #FFFFFF;\">"+"</a></p>" + 
				"                        </td>" + 
				"                    </tr>" + 
				"                    <tr>" + 
				"                        <td bgcolor=\"#ffffff\" align=\"left\" style=\"padding: 0px 30px 20px 30px; color: #666666; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 25px;\">" + 
				"                            <p style=\"margin: 0;\">Si vous avez des questions, il suffit de répondre à cet e-mail, nous sommes toujours heureux de vous aider.</p>" + 
				"                        </td>" + 
				"                    </tr>" + 
				"                    <tr>" + 
				"                        <td bgcolor=\"#ffffff\" align=\"left\" style=\"padding: 0px 30px 40px 30px; border-radius: 0px 0px 4px 4px; color: #666666; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 25px;\">" + 
				"                            <p style=\"margin: 0;\">Smart Technology </p>" +
				"                    </tr>" + 
				"                </table>" + 
				"            </td>" + 
				"        </tr>" + 
				"    </table>" + 
				"</body>" + 
				"" + 
				"</html>";
		
		MimeMessage msg = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(to);

        helper.setSubject(subject);
        
        helper.setText(body, true);

        javaMailSender.send(msg);
		
	}
	
	
	
	
	
	
	
	public void confirmAccountCp(String to, String subject,  String nomContent, String user, Integer id) throws MessagingException {

		String link;
		
		
		String body ="<!DOCTYPE html>" + 
				"<html>" + 
				"" + 
				"<head>" + 
				"    <title></title>" + 
				"    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />" + 
				"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">" + 
				"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />" + 
				"    <style type=\"text/css\">" + 
				"        @media screen {" + 
				"            @font-face {" + 
				"                font-family: 'Lato';" + 
				"                font-style: normal;" + 
				"                font-weight: 400;" + 
				"                src: local('Lato Regular'), local('Lato-Regular'), url(https://fonts.gstatic.com/s/lato/v11/qIIYRU-oROkIk8vfvxw6QvesZW2xOQ-xsNqO47m55DA.woff) format('woff');" + 
				"            }" + 
				"" + 
				"            @font-face {" + 
				"                font-family: 'Lato';" + 
				"                font-style: normal;" + 
				"                font-weight: 700;" + 
				"                src: local('Lato Bold'), local('Lato-Bold'), url(https://fonts.gstatic.com/s/lato/v11/qdgUG4U09HnJwhYI-uK18wLUuEpTyoUstqEm5AMlJo4.woff) format('woff');" + 
				"            }" + 
				"" + 
				"            @font-face {" + 
				"                font-family: 'Lato';" + 
				"                font-style: italic;" + 
				"                font-weight: 400;" + 
				"                src: local('Lato Italic'), local('Lato-Italic'), url(https://fonts.gstatic.com/s/lato/v11/RYyZNoeFgb0l7W3Vu1aSWOvvDin1pK8aKteLpeZ5c0A.woff) format('woff');" + 
				"            }" + 
				"" + 
				"            @font-face {" + 
				"                font-family: 'Lato';" + 
				"                font-style: italic;" + 
				"                font-weight: 700;" + 
				"                src: local('Lato Bold Italic'), local('Lato-BoldItalic'), url(https://fonts.gstatic.com/s/lato/v11/HkF_qI1x_noxlxhrhMQYELO3LdcAZYWl9Si6vvxL-qU.woff) format('woff');" + 
				"            }" + 
				"        }" + 
				"" + 
				"        /* CLIENT-SPECIFIC STYLES */" + 
				"        body," + 
				"        table," + 
				"        td," + 
				"        a {" + 
				"            -webkit-text-size-adjust: 100%;" + 
				"            -ms-text-size-adjust: 100%;" + 
				"        }" + 
				"" + 
				"        table," + 
				"        td {" + 
				"            mso-table-lspace: 0pt;" + 
				"            mso-table-rspace: 0pt;" + 
				"        }" + 
				"" + 
				"        img {" + 
				"            -ms-interpolation-mode: bicubic;" + 
				"        }" + 
				"" + 
				"        /* RESET STYLES */" + 
				"        img {" + 
				"            border: 0;" + 
				"            height: auto;" + 
				"            line-height: 100%;" + 
				"            outline: none;" + 
				"            text-decoration: none;" + 
				"        }" + 
				"" + 
				"        table {" + 
				"            border-collapse: collapse !important;" + 
				"        }" + 
				"" + 
				"        body {" + 
				"            height: 100% !important;" + 
				"            margin: 0 !important;" + 
				"            padding: 0 !important;" + 
				"            width: 100% !important;" + 
				"        }" + 
				"" + 
				"        /* iOS BLUE LINKS */" + 
				"        a[x-apple-data-detectors] {" + 
				"            color: inherit !important;" + 
				"            text-decoration: none !important;" + 
				"            font-size: inherit !important;" + 
				"            font-family: inherit !important;" + 
				"            font-weight: inherit !important;" + 
				"            line-height: inherit !important;" + 
				"        }" + 
				"" + 
				"        /* MOBILE STYLES */" + 
				"        @media screen and (max-width:600px) {" + 
				"            h1 {" + 
				"                font-size: 32px !important;" + 
				"                line-height: 32px !important;" + 
				"            }" + 
				"        }" + 
				"" + 
				"        /* ANDROID CENTER FIX */" + 
				"        div[style*=\"margin: 16px 0;\"] {" + 
				"            margin: 0 !important;" + 
				"        }" + 
				"    </style>" + 
				"</head>" + 
				"" + 
				"<body style=\"background-color: #f4f4f4; margin: 0 !important; padding: 0 !important;\">" + 
				"    <!-- HIDDEN PREHEADER TEXT -->" + 
				"    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">" + 
				"        <!-- LOGO -->" + 
				"        <tr>" + 
				"<!-- Head -->"+
				"            <td bgcolor=\"#ffffff\" align=\"center\">" + 
				"                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">" + 
				"                    <tr>" + 
				"                        <td align=\"center\" valign=\"top\" style=\"padding: 40px 10px 40px 10px;\"> </td>" + 
				"                    </tr>" + 
				"                </table>" + 
				"            </td>" + 
				"        </tr>" + 
				"        <tr>" + 
				"<!-- li ta7t Head -->"+
				"            <td bgcolor=\"#181b2c\" align=\"center\" style=\"padding: 0px 10px 0px 10px;\">" + 
				"                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">" + 
				"                    <tr>" + 
				"                        <td bgcolor=\"#ffffff\" align=\"center\" valign=\"top\" style=\"padding: 40px 20px 20px 20px; border-radius: 4px 4px 0px 0px; color: #111111; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 48px; font-weight: 400; letter-spacing: 4px; line-height: 48px;\">" + 
				"                            <h1 style=\"font-size: 48px; font-weight: 400; margin: 2;\">Smart Music "+"</h1> " + 
				"                        </td>" + 
				"                    </tr>" + 
				"                    <tr>" + 
				"                        <td bgcolor=\"#ffffff\" align=\"center\" valign=\"top\" style=\"padding: 40px 20px 20px 20px; border-radius: 4px 4px 0px 0px; color: #111111; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 48px; font-weight: 400; letter-spacing: 4px; line-height: 48px;\">" + 
				"                            <h1 style=\"font-size: 48px; font-weight: 400; margin: 2;\">Bonjour  "+nomContent+" "+"!</h1> <img src=\" https://img.icons8.com/clouds/100/000000/handshake.png\" width=\"125\" height=\"120\" style=\"display: block; border: 0px;\" />" + 
				"                        </td>" + 
				"                    </tr>" + 
				"                </table>" + 
				"            </td>" + 
				"        </tr>" + 
				"        <tr>" + 
				"            <td bgcolor=\"#dc5b93\" align=\"center\" style=\"padding: 0px 10px 0px 10px;\">" + 
				"                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">" + 
				"                    <tr>" + 
				"                        <td bgcolor=\"#ffffff\" align=\"left\" style=\"padding: 20px 30px 40px 30px; color: #666666; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 25px;\">" + 
				"                            <p style=\"margin: 0;\">Nous sommes ravis que vous commenciez. Tout d'abord, vous devez confirmer votre compte. Appuyez simplement sur le bouton ci-dessous.</p>" + 
				"                        </td>" + 
				"                    </tr>" + 
				"                    <tr>" + 
				"                        <td bgcolor=\"#ffffff\" align=\"left\">" + 
				"                            <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" + 
				"                                <tr>" + 
				"                                    <td bgcolor=\"#ffffff\" align=\"center\" style=\"padding: 20px 30px 60px 30px;\">" + 
				"                                        <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" + 
				"                                            <tr>" + 
				"                                                <td align=\"center\" style=\"border-radius: 3px;\" bgcolor=\"#FFFFFF\"><a href="+" target=\"_blank\" style=\"font-size: 20px; font-family: Helvetica, Arial, sans-serif; color: #ffffff; text-decoration: none; color: #ffffff; text-decoration: none; padding: 15px 25px; border-radius: 2px; border: 1px solid #FFFFFF; display: inline-block;\">Confirm Account</a></td>" + 
				"                                            </tr>" + 
				"                                        </table>" + 
				"                                    </td>" + 
				"                                </tr>" + 
				"                            </table>" + 
				"                        </td>" + 
				"                    </tr> <!-- COPY -->" + 
				"                    <tr>" + 
				"                        <td bgcolor=\"#ffffff\" align=\"left\" style=\"padding: 0px 30px 0px 30px; color: #666666; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 25px;\">" + 
				"                            <p style=\"margin: 0;\">Si cela ne fonctionne pas, copiez et collez le lien suivant dans votre navigateur:</p>" + 
				"                        </td>" + 
				"                    </tr> <!-- COPY -->" + 
				"                    <tr>" + 
				"                        <td bgcolor=\"#ffffff\" align=\"left\" style=\"padding: 20px 30px 20px 30px; color: #666666; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 25px;\">" + 
				"                            <p style=\"margin: 0;\"><a href=\"#\" target=\"_blank\" style=\"color: #FFFFFF;\">"+"</a></p>" + 
				"                        </td>" + 
				"                    </tr>" + 
				"                    <tr>" + 
				"                        <td bgcolor=\"#ffffff\" align=\"left\" style=\"padding: 0px 30px 20px 30px; color: #666666; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 25px;\">" + 
				"                            <p style=\"margin: 0;\">Si vous avez des questions, il suffit de répondre à cet e-mail, nous sommes toujours heureux de vous aider.</p>" + 
				"                        </td>" + 
				"                    </tr>" + 
				"                    <tr>" + 
				"                        <td bgcolor=\"#ffffff\" align=\"left\" style=\"padding: 0px 30px 40px 30px; border-radius: 0px 0px 4px 4px; color: #666666; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 25px;\">" + 
				"                            <p style=\"margin: 0;\">Smart Technology </p>" +
				"                    </tr>" + 
				"                </table>" + 
				"            </td>" + 
				"        </tr>" + 
				"    </table>" + 
				"</body>" + 
				"" + 
				"</html>";
		
		MimeMessage msg = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(to);

        helper.setSubject(subject);
        
        helper.setText(body, true);

        javaMailSender.send(msg);
		
	}
	
	
public void sendLinkResetPwd(String to, String token, String subject, String nom, String prenom) throws MessagingException {
		
		String link="http://192.168.1.111:4200/resetpassword/"+token;
		String body = " Bonjour,<br> Voici le lien pour restaurer votre mot de passe : "+ link ;
		
		MimeMessage msg = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(to);

        helper.setSubject(subject);
        
        helper.setText(body, true);

        javaMailSender.send(msg);
	}



public void sendLinkResetPwdAdmin(String to, String token, String subject, String nom, String prenom) throws MessagingException {
	
	String link="http://192.168.1.111:4200/resetpasswordAdmin/"+token;
	String body = " Bonjour,<br> Voici le lien pour restaurer votre mot de passe : "+ link ;
	
	MimeMessage msg = javaMailSender.createMimeMessage();

    MimeMessageHelper helper = new MimeMessageHelper(msg, true);
    helper.setTo(to);

    helper.setSubject(subject);
    
    helper.setText(body, true);

    javaMailSender.send(msg);
}


public void sendLinkResetPwdCp(String to, String token, String subject, String nomContent) throws MessagingException {
	
	String link="http://192.168.1.111:4200/resetpassword/"+token;
	String body = " Bonjour,<br> Voici le lien pour restaurer votre mot de passe : "+ link ;
	
	MimeMessage msg = javaMailSender.createMimeMessage();

    MimeMessageHelper helper = new MimeMessageHelper(msg, true);
    helper.setTo(to);

    helper.setSubject(subject);
    
    helper.setText(body, true);

    javaMailSender.send(msg);
}
	
	
}
