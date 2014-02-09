package common;

import interfaces.ScriptsHandler;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;


public class ScriptManager implements ScriptsHandler {

	private final String TMP1DIR = "G:\\tmp1";
	private final String TMPFILE = TMP1DIR + "\\tmp.txt";
	private final String BATFILE = TMP1DIR + "\\gb.bat";
	private final String SHFILE = TMP1DIR + "\\mos.sh";
	private final String SHFILE1 = TMP1DIR + "\\example.sh";
	private File file = new File(TMP1DIR);
	private FileOutputStream fos;
	private DataOutputStream dos;

	public ScriptManager() {
		if (!file.exists()) {
			file.mkdir();
		}
	}

	@Override
	public void createBatFile() throws IOException {
		file = new File(BATFILE);
		fos = new FileOutputStream(file);
		dos = new DataOutputStream(fos);
		String cmd = "@ECHO OFF";
		String cmd1 = "C:\\\"Program Files (x86)\"\\Git\\bin\\sh.exe --login -i "
				+ SHFILE;
		// String cmd2 = "set /p ExitCode=<" +"\""+ TMPFILE +"\"";
		String cmd2 = "set  ExitCode=%ERRORLEVEL%";
		String cmd3 = "echo \"ExitCode = %ExitCode%\"";
		// String cmd4 = "cmd /c exit /b %ExitCode%";
		String cmd4 = "exit /b %ExitCode%";

		dos.writeBytes(cmd + "\n");
		dos.writeBytes(cmd1 + "\n");
		dos.writeBytes(cmd2 + "\n");
		dos.writeBytes(cmd3 + "\n");
		dos.writeBytes(cmd4 + "\n");

	}

	@Override
	public void createShFile() throws IOException {
		file = new File(SHFILE);
		fos = new FileOutputStream(file);
		dos = new DataOutputStream(fos);
		// String cmd1 =
		// "retval=`ls non_existent_file &> /dev/null  ; echo $?`";
		String cmd1 = "exit $(G:\\\\tmp1\\\\example.sh)";
		// String cmd2 = "exit $?";
		// String cmd3 = "tmpfile=" + "\""+ TMPFILE + "\"";
		// String cmd4 = "if [ -f $tmpfile ] ; then";
		// String cmd5 = "rm -rf $tmpfile";
		// String cmd6 = "fi";
		// String cmd7 = "touch $tmpfile";
		// String cmd8 = "echo \"$retval\" >> $tmpfile";

		dos.writeBytes("#!/bin/bash\n");
		dos.writeBytes(cmd1 + "\n");
		// Create an example shell script to return exit 42
		file = new File(SHFILE1);
		fos = new FileOutputStream(file);
		dos = new DataOutputStream(fos);

		dos.writeBytes("#!/bin/bash\n");
		cmd1 = "retval=42";
		String cmd2 = "echo $retval";
		String cmd3 = "exit $retval";
		dos.writeBytes(cmd1 + "\n");
		dos.writeBytes(cmd2 + "\n");
		dos.writeBytes(cmd3 + "\n");

	}

	@Override
	public void executeBat() throws IOException, InterruptedException {
		// This will enable a window show the bat execution
		// String cmd="cmd /c start /wait " + BATFILE;
		// This will enable java get the exit code
		String cmd = "cmd /c " + BATFILE;
		Runtime r = Runtime.getRuntime();
		Process pr = r.exec(cmd);
		int exitVal = pr.waitFor();
		System.out.println("ExitCode passing from child process = " + exitVal);
	}

	@Override
	public String readTmpFile() throws FileNotFoundException {
		file = new File(TMPFILE);
		String content = extracted().useDelimiter("\\Z").next();
		return content;
	}

	private Scanner extracted() throws FileNotFoundException {
		return new Scanner(file);
	}

	/**
	 * @param args
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws IOException,
			InterruptedException {
		ScriptManager scm = new ScriptManager();
		scm.createBatFile();
		scm.createShFile();
		scm.executeBat();

	}

}
