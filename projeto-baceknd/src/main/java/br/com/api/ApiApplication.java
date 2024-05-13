package br.com.api;

import br.com.api.model.Car;
import br.com.api.model.User;
import br.com.api.service.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableCaching
@EnableSwagger2
@EnableDiscoveryClient
public class ApiApplication {
	public static void main(String[] args) throws Exception {

		UserServiceImpl userService = new UserServiceImpl();

		Car car = new Car();
		List<Car> cars = new ArrayList<>();
		cars.add(car);

		car.setId(1L);
		car.setLicensePlate("MEW-12345");
		car.setYear(2014);

		User user = new User();
		user.setBirthday("2024-02-02");
		user.setCars(cars);
		user.setEmail("admin@gmail.com");
		user.setFirstName("admin");
		user.setId(1L);
		user.setLastName("admin");
		user.setLogin("admin");
		user.setPassword("password");
		user.setPhone("8398657498");


        SpringApplication.run(ApiApplication.class, args);
	}


	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/auth/").allowedOrigins("*");
			}
		};
	}


}
