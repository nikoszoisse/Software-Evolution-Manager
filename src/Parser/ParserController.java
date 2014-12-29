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
		int dot = file_path.lastIndexOf(".");
		String file_extension = file_path.substring(dot + 1);
		
		Parser used_parser = chooseParser(file_extension );
		if(used_parser == null){
			app.setError("This file Extension does not supported in this version");
			return null;
		}
		else{
			Workspace ret_workspace = used_parser.requestWorkspace();
			
			if(used_parser.getErrors() != null){
				app.setError(used_parser.getErrors());
				return null;
			}
			
			return ret_workspace;
		}
	}
	
	private Parser chooseParser(String file_extension){
		switch(file_extension){
		case "txt": return new HistoryParser();
		}

		return null;
		
	}
}
