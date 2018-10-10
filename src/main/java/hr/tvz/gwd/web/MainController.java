package hr.tvz.gwd.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.sql.SQLException;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.encog.neural.networks.BasicNetwork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import hr.tvz.gwd.db.DesignPropertiesRepository;
import hr.tvz.gwd.db.UserRepository;
import hr.tvz.gwd.model.DesignProperties;
import hr.tvz.gwd.model.User;

@Controller
public class MainController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	DesignPropertiesRepository designPropertiesRepository;

	@Autowired
	NeuralNetTraining helper;

	@Autowired
	JavaMailSender javaMailSender;

	@Autowired
	SpringTemplateEngine thymeleaf;

	@GetMapping("/")
	public String index() {
		return "main";
	}

	@GetMapping("/newDesign")
	public String newDesign(Model model, @RequestParam(value = "grade", required = false) Integer grade,
			@RequestParam(value = "hash", required = false) Integer hash, HttpSession session, Principal principal) {
		User user = userRepository.findByUsername(principal.getName());
		if (grade != null && hash != null) {
			DesignProperties lastDesign = (DesignProperties) session.getAttribute("lastDesign");
			if (lastDesign != null && lastDesign.hashCode() == hash) {
				lastDesign.setOcjena(grade);
				lastDesign.setUser(user);
				designPropertiesRepository.saveAndFlush(lastDesign);
			}
		}

		DesignProperties design = null;

		if (user.getNetwork() != null) {
			try {
				BasicNetwork network = (BasicNetwork) SerializationUtils
						.deserialize(user.getNetwork().getBytes(1, (int) user.getNetwork().length()));
				design = GeneticAlgorithm.generateDesign(network);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			design = new DesignProperties(true);
		}

		session.setAttribute("lastDesign", design);
		model.addAttribute("customStyle", design.generateCss());
		model.addAttribute("hash", design.hashCode());
		helper.checkNeuralNet(user);

		return "design";
	}

	@GetMapping("/login")
	public String login(@RequestParam(value = "error", required = false) Boolean error,
			@RequestParam(value = "logout", required = false) Boolean logout,
			@RequestParam(value = "verification", required = false) Boolean verification,
			@RequestParam(value = "verificationSuccess", required = false) Boolean verificationSuccess, Model model) {
		if (error != null)
			model.addAttribute("error", true);
		if (logout != null)
			model.addAttribute("logout", true);
		if (verification != null)
			model.addAttribute("verification", true);
		if (verificationSuccess != null)
			model.addAttribute("verificationSuccess", true);
		return "login";
	}

	@GetMapping("/register")
	public String register(Model model) {
		return "register";
	}

	@PostMapping("/register")
	public String registerPost(@RequestParam("username") String username, @RequestParam("email") String email,
			@RequestParam("password") String password, @RequestParam("password2") String password2,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (userRepository.countByUsername(username) > 0) {
			redirectAttributes.addFlashAttribute("taken", true);
			return "redirect:/register";
		}
		if (userRepository.countByEmail(email) > 0) {
			redirectAttributes.addFlashAttribute("email", true);
			return "redirect:/register";
		}
		if (!password.equals(password2)) {
			redirectAttributes.addFlashAttribute("mismatch", true);
			return "redirect:/register";
		}
		User user = new User(username, false);
		user.setPassword(password);
		user.setEmail(email);
		user.setRoleList("ROLE_USER");
		user.setToken(UUID.randomUUID().toString());
		userRepository.save(user);

		final MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
			message.setSubject("Generiranje web dizajna - Potvrdite vašu email adresu");
			message.setTo(user.getEmail());
			Context ctx = new Context();
			ctx.setVariable("email", user.getEmail());
			ctx.setVariable("link", "http://localhost:8080/verification/" + user.getToken());
			String htmlContent = thymeleaf.process("verification", ctx);
			message.setText(htmlContent, true);

		} catch (MessagingException e) {
			e.printStackTrace();
		}

		javaMailSender.send(mimeMessage);

		return "redirect:/login?verification=true";
	}

	@GetMapping("/verification/{token}")
	public String verification(@PathVariable String token, Model model, HttpServletRequest request) {
		User user = userRepository.findByToken(token);
		if (user == null)
			return "redirect:/login";
		user.setEnabled(true);
		userRepository.saveAndFlush(user);
		return "redirect:/login?verificationSuccess=true";
	}

	@GetMapping("/download")
	public void downloadFile(HttpServletResponse response, @RequestParam(value = "hash", required = false) Integer hash,
			HttpSession session) {
		response.setContentType("text/css");
		response.setHeader("Content-Disposition", "attachment; filename=\"generiranje-web-dizajna-" + hash + ".css\"");
		try {
			FileCopyUtils.copy(
					new ByteArrayInputStream(
							((DesignProperties) session.getAttribute("lastDesign")).generateCss().getBytes("UTF-8")),
					response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@GetMapping("/send")
	public void sendFile(HttpServletResponse response, @RequestParam(value = "hash", required = false) Integer hash,
			HttpSession session, Principal principal) {
		User user = userRepository.findByUsername(principal.getName());
		final MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
			message.setTo(user.getEmail());
			message.setSubject("CSS datoteka sa vašim dizajnom - Generiranje web dizajna");
			message.setText("U prilogu se nalazi vaša CSS datoteka.");
			message.addAttachment("generiranje-web-dizajna-" + hash + ".css", new InputStreamSource() {
				@Override
				public InputStream getInputStream() throws IOException {
					return new ByteArrayInputStream(
							((DesignProperties) session.getAttribute("lastDesign")).generateCss().getBytes("UTF-8"));
				}
			});
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		javaMailSender.send(mimeMessage);
	}
	
}
