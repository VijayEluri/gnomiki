package org.gnomiki.tunnel;

import java.net.Socket;

/**
 * A processor gets a serverSocket and processes data coming in there. That is,
 * reads data and sends it to the correponding outgoing adress.
 * 
 * @author MicWin
 * 
 */
public interface IProcessor {

	/**
	 * Receives Dats from an open socket and processes it.
	 * 
	 * @param socket
	 */
	void process(Socket socket);
}
