package util;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ScriptsHandler {
	public void createBatFile() throws FileNotFoundException, IOException;
	public void createShFile() throws FileNotFoundException, IOException;
	public void executeBat();
	public String readTmpFile();

}
