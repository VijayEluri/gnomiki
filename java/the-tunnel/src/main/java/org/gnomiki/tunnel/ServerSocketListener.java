package org.gnomiki.tunnel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ServerSocketListener implements Runnable {

	private final int port;
	private static final Log L = LogFactory.getLog(ServerSocketListener.class);
	private static final int TIMEOUT = 30000;

	boolean shutDown = false;
	private final IProcessorFactory processorFactory;

	public ServerSocketListener(int port, IProcessorFactory processorFactory) {
		this.port = port;
		this.processorFactory = processorFactory;
	}

	public void run() {
		L.info("listening at port " + port + " with timeout " + TIMEOUT + "ms");
		ServerSocket s = null;
		do {
			try {
				s = new ServerSocket(port);

				s.setSoTimeout(TIMEOUT);
				Socket accept = s.accept();
				spinOffAccept(accept);
			} catch (SocketTimeoutException ste) {
				// dont care
			} catch (IOException e) {
				L.error("couldnt bind to port " + port, e);
				shutDown = true;
			}

			if (s != null)
				try {
					s.close();
				} catch (IOException e) {
					L.error("cannot close socket", e);
				}
		} while (!shutDown);

		L.info("shutdown");
	}

	/**
	 * Spins off the accept into another thread.
	 * 
	 * @param accept
	 */
	private void spinOffAccept(final Socket accept) {

		IProcessor processor = processorFactory.createProcessor();
		processor.process(accept);

	}

	public void shutDown() {
		shutDown = true;
	}
}
