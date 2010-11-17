package org.gnomiki;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.gnomiki.cluster.ClusterListener;

public class SocketListenerTester {

	/**
	 * @param args
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws UnknownHostException
	 */
	public static void main(String[] args) throws InterruptedException {

		int counter = 0;

		while (true) {
			counter++;
			String guid = makeGUID(counter);
			System.out.println("trying conect as " + guid);
			Socket s;
			try {
				s = new Socket("localhost", ClusterListener.PORT);

				OutputStream outputStream = s.getOutputStream();
				PrintWriter writer = new PrintWriter(outputStream);

				writer.print("CONNECT " + guid + " " + (30000 + counter + 1));
				writer.flush();
				s.close();
				System.out.println("CONNECT as " + guid);
			} catch (Exception e) {
				System.out.println("nope");
			}
			Thread.currentThread().sleep(5000);

		}
	}

	private static String makeGUID(int counter) {
		String guid = "00000000000000000000000000000000" + counter;
		guid = guid.substring(guid.length() - 32);
		return guid;
	}

}
