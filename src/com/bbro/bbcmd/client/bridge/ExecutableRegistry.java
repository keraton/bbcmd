package com.bbro.bbcmd.client.bridge;

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
		return executable;
	}
}
