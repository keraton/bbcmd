package com.bbro.bbcmd.client.command.env;

import java.util.Set;

import com.bbro.bbcmd.client.bridge.ExecutableRegistry;
import com.bbro.bbcmd.client.core.Commandable;
import com.bbro.bbcmd.client.core.IllegalOptionCommandException;

public class EnvCommand implements Commandable {
	
	public static String KEY = "env";

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public void doCommand(String textInput) throws IllegalOptionCommandException {
		Environment environment = Environment.getInstance();
		String[] args = textInput.split(" ");
		
		if (textInput.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			Set<String> envs = environment.getKeyEnv();
			
			for (String key : envs) {
				sb.append(key);
				sb.append("=");
				sb.append(environment.getEnv(key));
				sb.append("<br/>");
			}
			ExecutableRegistry.getExecutable().print(sb.toString());
		}
		else if (args.length == 1){
			String text = args[0];
			if (text.indexOf("=") <1) {
				throw new IllegalOptionCommandException();
			}
			String[] valueKey = text.split("=");
			if (valueKey.length != 2) {
				throw new IllegalOptionCommandException();
			}
			
			String key = valueKey[0];
			String value = valueKey[1];
			environment.putEnv(key, value);
			
		}
		else {
			throw new IllegalOptionCommandException();
		}
	}

}
