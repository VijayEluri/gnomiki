package org.gnomiki.cluster.listener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gnomiki.cluster.Cluster;

public class ServerSocketListener implements Runnable {

	public static final int PORT = 30000;
	private static final Log L = LogFactory.getLog(ServerSocketListener.class);
	private final int timeout;

	boolean shutDown = false;

	public ServerSocketListener(int intervall, Cluster cluster) {
		this.timeout = intervall;

	}

	public void run() {
		L.info("listening at port " + PORT + " with timeout " + timeout + "ms");
		ServerSocket s = null;
		do {
			try {
				s = new ServerSocket(PORT);

				s.setSoTimeout(timeout);
				Socket accept = s.accept();
				spinOffAccept(accept);
			} catch (SocketTimeoutException ste) {
				// dont care
			} catch (IOException e) {
				L.error("couldnt bind to port " + PORT, e);
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
		new Thread(new Runnable() {

			public void run() {

				runAccept(accept);
			}
		}).start();
	}

	private void runAccept(Socket socket) {

		SocketAddress remoteAdress = socket.getRemoteSocketAddress();
		L.info("incomming connect from " + socket.getInetAddress());

		int waitCounter = 0;

		try {
			InputStream in = socket.getInputStream();
			Reader reader = new InputStreamReader(in);
			BufferedReader buf = new BufferedReader(reader);
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(
					socket.getOutputStream()));

			while (!socket.isClosed() && waitCounter < timeout) {
				if (buf.ready()) {
					String line = buf.readLine();
					L.info("remote message was " + line);
					writer.println("OK '" + line + "'");
					writer.flush();
					waitCounter = 0;
				} else {
					try {
						Thread.currentThread().sleep(100);
					} catch (InterruptedException e) {
						L.error("cannot wait for input ?!?", e);
					}
				}
				waitCounter += 100;

			}
			socket.close();
		} catch (IOException e) {
			L.error("could not close incoming socket", e);
		}
	}

	public void shutDown() {
		shutDown = true;
	}
}
