package eleven.lc.gigi;

import com.github.pagehelper.PageHelper;
import eleven.lc.gigi.myutil.CustomMultipartResolver;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.multipart.MultipartResolver;

import java.util.Properties;

@SpringBootApplication
@EnableAsync
@EnableScheduling
@EnableWebSecurity
@MapperScan(basePackages = "eleven.lc.gigi.dao") //mybatis的dao层扫描
public class GigiApplication {
	public static void main(String[] args) {
		SpringApplication.run(GigiApplication.class, args);
	}
}