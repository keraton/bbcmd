package com.bbro.bbcmd.client.core;

import com.bbro.bbcmd.shared.model.Requestable;
import com.bbro.bbcmd.shared.model.Responsable;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("command")
public interface CommandServerService extends RemoteService {

	Responsable execute(String path, Requestable request);

}
