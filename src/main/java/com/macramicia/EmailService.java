package com.macramicia;

import com.macramicia.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender emailSender;

	@Async("sendMailExecutor")
	public void sendNewAccountMail(User user) {
		String to = user.getEmail();
		String subject = "New Macra Micia Account";
		String text = new StringBuilder()
				.append("Welcome to the Macramicia Community, " + user.getUsername() + "!")
				.append(System.lineSeparator())
				.append(System.lineSeparator())
				.append("A new account has been created for you. " +
						"You can now log in at: https://macramicia.herokuapp.com/user/login")
				.append(System.lineSeparator())
				.append("We hope you will enjoy the courses on our website.")
      			.append(System.lineSeparator())
				.append(System.lineSeparator())
				.append("Yours,")
				.append(System.lineSeparator())
				.append(System.lineSeparator())
      			.append("Team Macra Micia")
				.append(System.lineSeparator())
				.append(System.lineSeparator())
				.append(System.lineSeparator())
				.append("This wasn't you? + " +
						"Send us an email to macramicia@gmail.com and we'll sort this out for you.").toString();
		sendMail(to, subject, text);
	}

	@Async("sendMailExecutor")
	public void sendMail(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		emailSender.send(message);
	}
}
