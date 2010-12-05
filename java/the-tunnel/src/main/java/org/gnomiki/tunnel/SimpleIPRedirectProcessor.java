package org.gnomiki.tunnel;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.SocketAddress;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SimpleIPRedirectProcessor implements IProcessor, IProcessorFactory {

	private static final Log L = LogFactory
			.getLog(SimpleIPRedirectProcessor.class);

	public void process(final Socket socket) {

		new Thread(new Runnable() {

			public void run() {

				runAccept(socket);
			}
		}).start();
	}

	public IProcessor createProcessor() {
		// simply reuse this instance
		return this;
	}

	private void runAccept(Socket socket) {

		SocketAddress remoteAdress = socket.getRemoteSocketAddress();
		L.info("incomming connect from " + socket.getInetAddress());

		try {
			InputStream in = socket.getInputStream();
			socket.close();
		} catch (IOException e) {
			L.error("could not close incoming socket", e);
		}
	}

}
