package com.bbro.bbcmd.client.core;

public interface CommandDispatcher {

	void dispatch(String command, String... args);
}
