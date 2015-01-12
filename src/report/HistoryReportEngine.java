package report;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;

import application.Law;
import application.Workspace;

/*
 * The report Controller -> renamed
 */

public class HistoryReportEngine {

    final int lawsNum = 8;

    public void createReport(Path save_file_path, Workspace workspace) {
	// create the report file with default permissions
	try {
	    Files.createFile(save_file_path);
	}
	catch (FileAlreadyExistsException e) {
	    System.err.format("file named %s already exists%n", save_file_path);
	}
	catch (IOException e) {
	    System.err.format("createFile error: %s%n", e);
	}
	// write the report
	try (BufferedWriter writer =
	     Files.newBufferedWriter(save_file_path, StandardCharsets.UTF_8)) {
	    String softwareName = workspace.getTitle();
	    // Law firstLaw = workspace.getLaw(0);
	    String comment;
	    boolean following;
	    String lawNumber;

	    writer.write(softwareName);
	    writer.write("\n======================\n");

	    for (int il = 0; il < lawsNum; il++) {
		Law l = workspace.getLaw(il);
		comment = l.getComment();
		following = l.isAccepted();
		lawNumber = l.getLawName();
		
		writer.write("\n[" + lawNumber + "]\n");
		if (following) {
		    writer.write("'" + softwareName + "' is following " +
				 "Lehman's " + lawNumber + ".\n");
		} else {
		    writer.write("'" + softwareName + "' is *not* following" +
				 " Lehman's " + lawNumber + ".\n");
		}
		if (comment != null) {
		    writer.write(comment + "\n");
		}

	    }
	} catch (IOException e) {
	    System.err.format("IOException: %s%n", e);
	}
    }					 
}
