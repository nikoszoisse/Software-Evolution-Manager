package Parser;

import application.AppManager;
import application.Workspace;

public class ParserController {
	
	private AppManager app;
	
	/**
	 *  Used y appManager in order to parse a file
	 * @param file_path
	 * @return Workspace if generated succesfully
	 */
	public Workspace generateWorkspaceFromFile(String file_path){
		String file_extension = file_path;
		Parser used_parser = chooseParser(file_extension );
		if(used_parser == null){
			//TODO THROW ERROR for not support this type of file
			app.setError("This file Extension does not supported in this version");
			return null;
		}else{
			//Return the Workspace
			//TODO Handle here the errors of used_parser e.x wrong syntax of file
			//app.setError(used_parser.getErrors());
			return used_parser.requestWorkspace();
		}
	}
	
	private Parser chooseParser(String file_extension){
		switch(file_extension){
		case "txt": return new HistoryParser();
		}

		return null;
		
	}
}
