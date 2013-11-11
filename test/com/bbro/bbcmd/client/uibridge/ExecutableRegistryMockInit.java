package com.bbro.bbcmd.client.uibridge;

public class ExecutableRegistryMockInit {

	public static void init() {
		if (ExecutableRegistry.getExecutable() == null)
			ExecutableRegistry.setExecutable(new MockExecutable());
	}
}
