package org.gnomiki.cluster;

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

public class ClusterListener implements Runnable {

	public static final int PORT = 30000;
	private static final Log L = LogFactory.getLog(ClusterListener.class);
	private final int timeout;

	boolean shutDown = false;

	public ClusterListener(int intervall) {
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
				handleConnect(accept);
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
	private void handleConnect(final Socket accept) {
		new Thread(new Runnable() {

			public void run() {

				accept(accept);
			}
		}).start();
	}

	private void accept(Socket socket) {

		SocketAddress remoteAdress = socket.getRemoteSocketAddress();
		L.info("incomming connect from " + socket.getInetAddress());

		try {
			InputStream in = socket.getInputStream();

			Reader reader = new InputStreamReader(in);
			while (reader.ready()) {
				BufferedReader buf = new BufferedReader(reader);
				L.info("remote message was " + buf.readLine());
			}
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(
					socket.getOutputStream()));
			writer.println("OK");
			writer.flush();
			socket.close();

		} catch (IOException e) {
			L.error("could not close incoming socket", e);
		}
	}

	public void shutDown() {
		shutDown = true;
	}
}
