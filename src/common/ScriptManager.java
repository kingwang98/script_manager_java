package common;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import util.ScriptsHandler;

public class ScriptManager implements ScriptsHandler {

	private final String TMP1DIR = "G:\\tmp1";
	private final String TMPFILE = TMP1DIR + "\\tmp.txt";
	private final String BATFILE = TMP1DIR + "\\gb.bat";
	private final String SHFILE = TMP1DIR + "\\mos.sh";
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
		String cmd1 = "C:\\\"Program Files (x86)\"\\Git\\bin\\sh.exe --login -i"
				+ BATFILE;
		String cmd2 = "set /p ExitCode=<" + TMPFILE;
		String cmd3 = "echo ExitCode = %ExitCode%";
		String cmd4 = "exit /b %ExitCode%";

		dos.writeBytes("@echo off\n");
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
		String cmd1 = "retval=`ls non_existent_file &> /dev/null  ; echo $?`";

		String cmd2 = "echo \"retval=$retval\"";
		String cmd3 = "tmpfile=" + TMPFILE;
		String cmd4 = "if [ -f $tmpfile ] ; then";
		String cmd5 = "rm -rf $tmpfile";
		String cmd6 = "fi";
		String cmd7 = "touch $tmpfile";
		String cmd8 = "echo \"$retval\" >> $tmpfile";

		dos.writeBytes("#!/bin/bash\n");
		dos.writeBytes(cmd1 + "\n");
		dos.writeBytes(cmd2 + "\n");
		dos.writeBytes(cmd3 + "\n");
		dos.writeBytes(cmd4 + "\n");
		dos.writeBytes(cmd5 + "\n");
		dos.writeBytes(cmd6 + "\n");
		dos.writeBytes(cmd7 + "\n");
		dos.writeBytes(cmd8 + "\n");

	}

	@Override
	public void executeBat() throws IOException, InterruptedException {
		String cmd="cmd /c start /wait " + BATFILE; 
		Runtime r=Runtime.getRuntime(); 
		Process pr=r.exec(cmd); 
		int exitVal= pr.waitFor();
		System.out.println("ExitCode = " +exitVal);
		file = new File(TMPFILE);
		if(file.exists()){
			System.out.println("Passed ExitCode = " + readTmpFile());
		}
		

	}

	@Override
	public String readTmpFile() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
