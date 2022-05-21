package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import spring.*;

public class MainForSpring {

	private static ApplicationContext ctx = null;

	public static void main(String[] args) throws IOException {
		ctx = new AnnotationConfigApplicationContext(AppCtx.class);

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.println("명령어를 입력하세요:");
			String command = reader.readLine();
			if (command.equalsIgnoreCase("exit")) {
				System.out.println("종료합니다.");
				break;
			}
			if (command.startsWith("write")) {
				processNewCommand(command.split("/"));		//게시글 내용에 띄어쓰기를 담을 수 있게 하기 위해 split기준 변경
				continue;
			} else if (command.startsWith("modify")) {
				processModifyCommand(command.split("/"));
				continue;
			} else if (command.startsWith("list")) {
				processListCommand();
				continue;
			} else if (command.startsWith("read")) {
				processInfoCommand(command.split("/"));
				continue;
			} else if (command.startsWith("delete")) {
				processDelete(command.split("/"));
				continue;
			}
			printHelp();
		}
	}


	private static void processNewCommand(String[] arg) {
		if (arg.length != 6) {
			printHelp();
			return;
		}
		BoardRegisterService regSvc = ctx.getBean("boardRegSvc", BoardRegisterService.class);
		RegisterRequest req = new RegisterRequest();
		req.setTitle(arg[1]);
		req.setContent(arg[2]);
		req.setAuthor(arg[3]);
		req.setPassword(arg[4]);
		req.setConfirmPassword(arg[5]);

		if (!req.isPasswordEqualToConfirmPassword()) {
			System.out.println("비밀번호와 확인이 일치하지 않습니다.\n");
			return;
		}
		try {
			regSvc.regist(req);
			System.out.println("등록했습니다.\n");
		} catch (DuplicateBoardException e) {
			System.out.println("이미 존재하는 게시글입니다.\n");
		}
	}

	private static void processListCommand(){
		BoardListPrinter listPrinter =
				ctx.getBean("listPrinter", BoardListPrinter.class);
		listPrinter.printAll();
		System.out.println("");
	}

	private static void processInfoCommand(String[] arg) {
		if (arg.length != 3) {
			printHelp();
			return;
		}
		BoardInfoPrinter infoPrinter = ctx.getBean("infoPrinter", BoardInfoPrinter.class);

		try {
			infoPrinter.printBoardInfo(arg[1], arg[2]);
		} catch (BoardNotFoundException e) {
			System.out.println("존재하지 않는 게시글입니다.\n");
		} catch (WrongIdPasswordException e) {
			System.out.println("비밀번호와 확인이 일치하지 않습니다.\n");
		}
	}

	private static void processModifyCommand(String[] arg) {
		if (arg.length != 4) {
			printHelp();
			return;
		}
		ModifyContentService changeCntSvc = ctx.getBean("changeCntSvc", ModifyContentService.class);
		try {
			changeCntSvc.changeContent(arg[1], arg[2], arg[3]);
			System.out.println("게시글을 변경했습니다.\n");
		} catch (BoardNotFoundException e) {
			System.out.println("존재하지 않는 게시글입니다.\n");
		} catch (WrongIdPasswordException e) {
			System.out.println("비밀번호와 확인이 일치하지 않습니다.\n");
		}
	}

	private static void processDelete(String[] arg) {
		if (arg.length != 3) {
			printHelp();
			return;
		}
		BoardDelete deleteBd = ctx.getBean("deleteBd", BoardDelete.class);
		try {
			deleteBd.deleteContent(arg[1], arg[2]);
			System.out.println("게시글을 삭제했습니다.\n");
		} catch (BoardNotFoundException e) {
			System.out.println("존재하지 않는 게시글입니다.\n");
		} catch (WrongIdPasswordException e) {
			System.out.println("비밀번호와 확인이 일치하지 않습니다.\n");
		}
	}

	private static void printHelp() {
		System.out.println();
		System.out.println("잘못된 명령입니다. 아래 명령어 사용법을 확인하세요.");
		System.out.println("명령어 사용법:");
		System.out.println("1. write/제목/내용/작성자/비밀번호/비밀번호확인");
		System.out.println("2. list");
		System.out.println("3. read/제목/비밀번호");
		System.out.println("4. modify/제목/수정내용/비밀번호");
		System.out.println("5. delete/제목/비밀번호");
		System.out.println();
	}
}