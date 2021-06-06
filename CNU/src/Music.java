import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Music extends Thread {
	private Player player;
	private boolean isLoop; //무한반복인지 아닌지 설정
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	//생성자
	public Music(String name, boolean isLoop) {
		try {
			this.isLoop=isLoop;
			file = new File(name);
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);  //해당 파일을 버퍼에 담아
			player =  new Player(bis);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	//실행되는 음악이 현재 어떤 위치를 실행하고 있는지 알려줌(리듬게임에 필요)
	public int getTime() {
		if (player == null)
			return 0;
		return player.getPosition();
	}
	//곡 종료
	public void close() {
		isLoop = false;
		player.close();
		this.interrupt(); //스레드 멈춤
	}
	
	@Override
	public void run() {
		try {
			do {
				player.play();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);  //해당 파일을 버퍼에 담아
				player =  new Player(bis);
			} while(isLoop);  //isLoop가 true이면 무한반복
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
