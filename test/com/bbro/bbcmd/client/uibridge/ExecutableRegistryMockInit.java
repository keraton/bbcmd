package com.bbro.bbcmd.client.uibridge;

import com.bbro.bbcmd.client.bridge.ExecutableRegistry;

public class ExecutableRegistryMockInit {

	public static void init() {
		if (ExecutableRegistry.getExecutable() == null)
			ExecutableRegistry.setExecutable(new MockExecutable());
	}
}
