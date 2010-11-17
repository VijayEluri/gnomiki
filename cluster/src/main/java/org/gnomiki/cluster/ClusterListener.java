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

public class ClusterListener implements Runnable {

	public static final int PORT = 30000;
	Log l;
	private final int timeout;

	boolean shutDown = false;

	public ClusterListener(Log myNewLogger, int intervall) {
		l = myNewLogger;
		this.timeout = intervall;

	}

	public void run() {
		l.info("listening at port " + PORT + " with timeout " + timeout + "ms");
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
				l.error("couldnt bind to port " + PORT, e);
				shutDown = true;
			}

			if (s != null)
				try {
					s.close();
				} catch (IOException e) {
					l.error("cannot close socket", e);
				}
		} while (!shutDown);

		l.info("shutdown");
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
		l.info("incomming connect from " + socket.getInetAddress());

		try {
			InputStream in = socket.getInputStream();

			Reader reader = new InputStreamReader(in);
			while (reader.ready()) {
				BufferedReader buf = new BufferedReader(reader);
				l.info("remote message was " + buf.readLine());
			}
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(
					socket.getOutputStream()));
			writer.println("OK");
			writer.flush();
			socket.close();

		} catch (IOException e) {
			l.error("could not close incoming socket", e);
		}
	}

	public void shutDown() {
		shutDown = true;
	}
}
