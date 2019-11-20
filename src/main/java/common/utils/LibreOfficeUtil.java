package common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.siit.fc.web.listener.AppListener;

public class LibreOfficeUtil {

	private static final Logger logger = LoggerFactory.getLogger(LibreOfficeUtil.class);

	/**
	 * 文件转换数量
	 */
	private static int FILE_CONVERT_COUNT = 0;

	public static void main(String[] args) {
		wordToPdf("C:/Users/siit/Desktop/doc/1.docx", "C:/Users/siit/Desktop/pdf1");
		wordToPdf("C:/Users/siit/Desktop/doc/2.doc", "C:/Users/siit/Desktop/pdf1");
		wordToPdf("C:/Users/siit/Desktop/doc/3.doc", "C:/Users/siit/Desktop/pdf1");
		wordToPdf("C:/Users/siit/Desktop/doc/4.docx", "C:/Users/siit/Desktop/pdf1");
		wordToPdf("C:/Users/siit/Desktop/doc/5.doc", "C:/Users/siit/Desktop/pdf1");
		wordToPdf("C:/Users/siit/Desktop/doc/6.doc", "C:/Users/siit/Desktop/pdf1");
		wordToPdf("C:/Users/siit/Desktop/doc/7.doc", "C:/Users/siit/Desktop/pdf1");
	}

	/**
	 * Word 转PDF
	 * 
	 * @return
	 */
	public static boolean wordToPdf(String srcPath, String desPath) {
		long start = System.currentTimeMillis();
		String command = "";
		String osName = System.getProperty("os.name");
		if (osName.contains("Windows")) {
			command = "cmd /c \"" + AppListener.getLibreofficeInstallPath() + "soffice\" --headless --convert-to pdf " + srcPath + " --outdir " + desPath;
			exec(command);
		}
		long end = System.currentTimeMillis();
		logger.debug("用时:{} ms", end - start);
		System.out.println("[" + DateSiitUtil.formatDate(new Date()) + "]，[ " + FILE_CONVERT_COUNT + " ]命令耗时：" + (end - start));

		// 检测文件转化启动
		startLibreOffice();

		return true;
	}

	/**
	 * 执行CMD命令
	 * 
	 * @param command
	 * @return
	 */
	public static boolean exec(String command) {
		// Process可以控制该子进程的执行或获取该子进程的信息
		Process process;
		try {
			logger.debug("exec cmd : {}", command);
			// exec()方法指示Java虚拟机创建一个子进程执行指定的可执行程序，并返回与该子进程对应的Process对象实例。
			process = Runtime.getRuntime().exec(command);
			// 下面两个可以获取输入输出流
			LibreOfficeUtil.printMessage(process.getInputStream());
			LibreOfficeUtil.printMessage(process.getErrorStream());
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(" exec {} error", command, e);
			return false;
		}

		int exitStatus = 0;
		try {
			// 等待子进程完成再往下执行，返回值是子线程执行完毕的返回值,返回0表示正常结束
			exitStatus = process.waitFor();
			// 接收执行完毕的返回值
			int i = process.exitValue();
			logger.debug("i----" + i);
		} catch (InterruptedException e) {
			logger.error("InterruptedException  exec {}", command, e);
			return false;
		}

		if (exitStatus != 0) {
			logger.error("exec cmd exitStatus {}", exitStatus);
		} else {
			logger.debug("exec cmd exitStatus {}", exitStatus);
		}

		process.destroy(); // 销毁子进程
		process = null;

		return true;
	}

	/**
	 * 读取流到控制台(防止死锁)
	 * 
	 * @param input
	 */
	private static void printMessage(final InputStream input) {
		new Thread(new Runnable() {
			public void run() {
				Reader reader = new InputStreamReader(input);
				BufferedReader bf = new BufferedReader(reader);
				String line = null;
				try {
					while ((line = bf.readLine()) != null) {
						System.out.println(line);
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (bf != null) {
							bf.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					try {
						if (reader != null) {
							reader.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					try {
						if (input != null) {
							input.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	/**
	 * 启动LibreOffice soffice.exe
	 */
	private static void startLibreOffice() {

		// 文件转换数量加1
		FILE_CONVERT_COUNT++;

		if (FILE_CONVERT_COUNT > 0 && FILE_CONVERT_COUNT % 10000 == 0) {
			String command = "cmd /c taskkill /f /t /im soffice.exe";
			exec(command);
			command = "cmd /c \"" + AppListener.getLibreofficeInstallPath() + "soffice.exe\"";
			exec(command);
		}
	}

}
