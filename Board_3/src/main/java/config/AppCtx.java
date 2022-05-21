package config;

import aspect.Calculator;
import aspect.ExeTimeAspect;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import spring.*;

@Configuration
@EnableAspectJAutoProxy
public class AppCtx {

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/spring5fs?charcterEncoding=utf8");
		ds.setUsername("spring");
		ds.setPassword("spring");
		ds.setInitialSize(2);
		ds.setMaxActive(10);
		ds.setMaxIdle(10);

		ds.setTestWhileIdle(true);
		ds.setMinEvictableIdleTimeMillis(60000 * 3);
		ds.setTimeBetweenEvictionRunsMillis(10 * 1000);

		return ds;
	}

	@Bean
	public ExeTimeAspect exeTimeAspect() {
		return new ExeTimeAspect();
	}

	@Bean
	public Calculator calculator() {
		return new Calculator();
	}
	
	@Bean
	public BoardDao boardDao() {
		return new BoardDao(dataSource());
	}
	
	@Bean BoardPrinter boardPrinter() {
		return new BoardPrinter();
	}
	
	@Bean
	public BoardListPrinter listPrinter() {
		return new BoardListPrinter();
	}

	@Bean
	public BoardWriteService boardWriteSvc() {
		return new BoardWriteService();
	}
	
	@Bean
	public BoardDetailPrinter detailPrinter() {
		return new BoardDetailPrinter();
	}
	
	@Bean
	public BoardModifyService boardModifySvc() {
		return new BoardModifyService();
	}

	@Bean
	public BoardDeleteService boardDeleteSvc() {
		return new BoardDeleteService();
	}
}
