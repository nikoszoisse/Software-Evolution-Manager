package Parser;

import application.Workspace;

public interface Parser {
	Workspace requestWorkspace();
	void openFile(String file_path);
	String getErrors();
	void setError(String error_message);
}
