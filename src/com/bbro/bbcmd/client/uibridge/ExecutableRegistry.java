package com.bbro.bbcmd.client.uibridge;

public class ExecutableRegistry {
	
	private ExecutableRegistry(){}

	private static Executable executable;
	
	public static void setExecutable(Executable executable){
		if(null != ExecutableRegistry.executable) {
			throw new IllegalStateException("Only one executable");
		}
		ExecutableRegistry.executable = executable;
	}
	
	public static Executable getExecutable() {
		if(null == executable) {
			throw new IllegalStateException("Executable cannot be null");
		}
		
		return executable;
	}
}
