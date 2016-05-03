package com.brunozambiazi.shopping.cart;

import com.brunozambiazi.shopping.cart.dao.ProductDao;
import com.brunozambiazi.shopping.cart.entity.Product;
import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
public class Main extends SpringBootServletInitializer implements CommandLineRunner {

	@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator advisor = new DefaultAdvisorAutoProxyCreator();
		advisor.setProxyTargetClass(true);
		return advisor;
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Main.class);
	}

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Autowired
	private ProductDao dao;

	@Override
	public void run(String... args) throws Exception {
		for (int x = 1; x <= 10; x++) {
			Product p1 = new Product(UUID.randomUUID().toString());
			p1.setName("Product "+x);
			p1.setPrice(BigDecimal.TEN);
			p1.setImageUrl("resources/images/product.jpg");
			dao.save(p1);
		}
	}

}
