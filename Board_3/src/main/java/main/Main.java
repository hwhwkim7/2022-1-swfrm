package main;

import aspect.Calculator;
import config.AppCtx;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	private static ApplicationContext ctx = null;

	public static void main(String[] args) throws IOException {
		ctx = new AnnotationConfigApplicationContext(AppCtx.class);

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
	
			System.out.println("\n=============================");
			System.out.println("명령어를 입력하세요 :");
			String command = reader.readLine();
			if (command.equalsIgnoreCase("exit")) {
				System.out.println("종료합니다.");
				break;
			} else if (command.startsWith("write ")) {
				processWriteCommand(command.split(" "));
				continue;
			} else if (command.startsWith("list")) {
				processListCommand();
				continue;
			} else if (command.startsWith("read ")) {
				processReadCommand(command.split(" "));
				continue;
			} else if (command.startsWith("modify ")) {
				processModifyCommand(command.split(" "));
				continue;
			} else if (command.startsWith("delete")) {
				processDelete(command.split(" "));
				continue;
			}
			printHelp();
		}
	}

	public static void processWriteCommand(String[] args) {
		if (args.length != 5) {
			printHelp();
			return;
		}

		BoardWriteService writeSvc = ctx.getBean("boardWriteSvc", BoardWriteService.class);
		WriteRequest req = new WriteRequest();
		req.setTitle(args[1]);
		req.setContent(args[2]);
		req.setAuthor(args[3]);
		req.setPassword(args[4]);

		Calculator cal = ctx.getBean("calculator", Calculator.class);
		writeSvc.write(req);
	}
	
	public static void processListCommand() {
		BoardListPrinter listPrinter = ctx.getBean("listPrinter", BoardListPrinter.class);
		Calculator cal = ctx.getBean("calculator", Calculator.class);
		listPrinter.printAllTitles();
	}
	
	public static void processReadCommand(String[] args) {
		if (args.length != 3) {
			printHelp();
			return;
		}
		
		BoardDetailPrinter detailPrinter = ctx.getBean("detailPrinter", BoardDetailPrinter.class);
		Calculator cal = ctx.getBean("calculator", Calculator.class);
		detailPrinter.printBoardDetail(args[1], args[2]);
	}
	
	public static void processModifyCommand(String[] args) {
		if (args.length != 4) {
			printHelp();
			return;
		}
		
		BoardModifyService modifySvc = ctx.getBean("boardModifySvc", BoardModifyService.class);
		Calculator cal = ctx.getBean("calculator", Calculator.class);
		modifySvc.modify(args[1], args[2], args[3]);
	}

	public static void processDelete(String[] arg) {
		if (arg.length != 3) {
			printHelp();
			return;
		}
		BoardDeleteService deleteSvc = ctx.getBean("boardDeleteSvc", BoardDeleteService.class);
		Calculator cal = ctx.getBean("calculator", Calculator.class);
		deleteSvc.delete(arg[1], arg[2]);
	}

	public static void printHelp() {
		System.out.println();
		System.out.println("잘못된 명령입니다. 아래 명령어 사용법을 확인하세요.");
		System.out.println("명령어 사용법:");
		System.out.println("write 제목 내용 작성자 비밀번호");
		System.out.println("list");
		System.out.println("read 제목 비밀번호");
		System.out.println("modify 제목 수정내용 비밀번호");
		System.out.println("delete 제목 비밀번호");
	}
}
