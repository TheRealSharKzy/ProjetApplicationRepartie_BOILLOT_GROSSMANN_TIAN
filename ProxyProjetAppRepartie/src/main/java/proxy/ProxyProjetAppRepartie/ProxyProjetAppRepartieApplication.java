package proxy.ProxyProjetAppRepartie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class ProxyProjetAppRepartieApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyProjetAppRepartieApplication.class, args);
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.addAllowedOrigin("*"); // Autoriser toutes les origines
		config.addAllowedMethod("*"); // Autoriser toutes les méthodes HTTP (GET, POST, etc.)
		config.addAllowedHeader("*"); // Autoriser tous les en-têtes
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
}
