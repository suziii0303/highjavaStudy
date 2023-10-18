package kr.or.ddit.basic.tcp;

import java.awt.Panel;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
    클라이언트는 서버와 접속이 완료되면 'd:/d_other/' 폴더에 있는
   '펭귄.jpg'파일을 서버로 전송한다
   (파일을 읽어서 소켓으로 출력하기)
   파일 복사 하는 거
 */
public class TcpFileClient {
	public static void main(String[] args) throws IOException {
		TcpFileClient client = new TcpFileClient();
//      File file = new File("d:/d_other/펭귄.jpg");
		File file = client.showDialog("OPEN");
		if (file == null) {
			System.out.println("전송할 파일을 선택 하지 않았습니다.");
			return;
		}

		if (!file.exists()) {
			System.out.println(file.getPath() + "파일이 없습니다");
			return;
		}

		// 파일 이름 구하기
		String fileName = file.getName();

		try {
			Socket socket = new Socket("localhost", 7777);

			System.out.println("파일 전송 시작...");

			// 처음에는 파일명을 문자열로 전송한다.
			DataOutputStream dout = new DataOutputStream(socket.getOutputStream());

			dout.writeUTF(fileName);

			// 파일 내용을 읽어서 소켓으로 전송한다.

			// 파일 읽기용 스트림 객체 생성
			BufferedInputStream bin = new BufferedInputStream(new FileInputStream(file));

			// 소켓 출력용 스트림 객체 생성
			BufferedOutputStream bout = new BufferedOutputStream(dout);

			byte[] temp = new byte[1024];
			int length = 0;

			// 파일 읽어서 소켓 전송하기
			while ((length = bin.read(temp)) > 0) {
				bout.write(temp, 0, length);
			}
			bout.flush();

			System.out.println("파일 전송 완료...");

			// 스트림과 소켓 닫기
			bout.close();
			bin.close();
			socket.close();

		} catch (Exception e) {
			System.out.println("파일 전송 실패==>" + e.getMessage());
		}

	}// main 메서드 끝

	// Dialog창을 열고 선택한 파일을 반환하는 메서드
	// option 매개변수에는 "SAVE" 또는 "OPEN" 값이 저장된다.
	private File showDialog(String option) {
		File selectedFile = null;
		// SWING의 파일 열기, 저장 창 연습

		JFileChooser chooser = new JFileChooser();

		// 선택할 파일의 확장자 설정 - 열기같은걸 할 때 보면 파일 종류를 고를 수 있는데 그거처럼 지정하는것
		FileNameExtensionFilter txt = new FileNameExtensionFilter("Text파일(*.txt)", "txt");
		FileNameExtensionFilter pdf = new FileNameExtensionFilter("PDF 파일", "pdf");
		FileNameExtensionFilter img = new FileNameExtensionFilter("이미지 파일", "png", "jpg", "gif");
		FileNameExtensionFilter doc = new FileNameExtensionFilter("MS Word파일", new String[] { "docx", "doc" });

		chooser.addChoosableFileFilter(txt);
		chooser.addChoosableFileFilter(pdf);
		chooser.addChoosableFileFilter(img);
		chooser.addChoosableFileFilter(doc);

		chooser.setFileFilter(img); // 확장자 목록 중 기본적으로 선택될 확장자 지정

		// 모든 파일 목록의 표시 여부를 설정한다.(true : 설정, false : 미설정)
		chooser.setAcceptAllFileFilterUsed(true);

		// Dialog 창에 나타날 기본 경로 설정하기 (예 : "d:/d_other" 폴더로 설정하기)
		chooser.setCurrentDirectory(new File("d:/d_other"));
		int result = -1;
		// 창 열기
		if ("SAVE".equals(option.toUpperCase())) {
			result = chooser.showSaveDialog(new Panel()); // 저장용 창
		} else if ("OPEN".equals(option.toUpperCase())) {
			result = chooser.showOpenDialog(new Panel()); // 열기용 창
		}

		// 창에서 파일을 선택한 후 '저장' 또는 '열기'버튼을 눌렀을 때 처리하기
		if (result == JFileChooser.APPROVE_OPTION) {
			selectedFile = chooser.getSelectedFile();
		}

		return selectedFile;
	}
}