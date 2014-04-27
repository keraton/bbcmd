package com.bbro.bbcmd.client.uibridge;

import com.bbro.bbcmd.client.command2ui.ExecutableRegistry;

public class ExecutableRegistryMockInit {

	public static void init() {
		if (ExecutableRegistry.getExecutable() == null)
			ExecutableRegistry.setExecutable(new MockExecutable());
	}
}
