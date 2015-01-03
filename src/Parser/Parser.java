package Parser;

import java.nio.file.Path;

import application.Workspace;

public interface Parser {
	Workspace requestWorkspace();
	void openFile(Path file_path);
	String getErrors();
	void setError(String error_message);
}
