package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.*;

@Configuration
public class AppCtx {

	@Bean
	public BoardDao boardDao() {
		return new BoardDao();
	}
	
	@Bean
	public BoardRegisterService boardRegSvc() {
		return new BoardRegisterService(boardDao());
	}
	
	@Bean
	public ModifyContentService changeCntSvc() {
		ModifyContentService cntSvc = new ModifyContentService();
		cntSvc.setBoardDao(boardDao());
		return cntSvc;
	}
	
	@Bean
	public BoardPrinter boardPrinter() {
		return new BoardPrinter();
	}
	
	@Bean
	public BoardListPrinter listPrinter() {
		return new BoardListPrinter(boardDao(), boardPrinter());
	}
	
	@Bean
	public BoardInfoPrinter infoPrinter() {
		BoardInfoPrinter infoPrinter = new BoardInfoPrinter();
		infoPrinter.setBoardDao(boardDao());
		infoPrinter.setPrinter(boardPrinter());
		return infoPrinter;
	}

	@Bean
	public BoardDelete deleteBd() {
		return new BoardDelete(boardDao());
	}
}
