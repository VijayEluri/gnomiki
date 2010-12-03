package org.gnomiki.droidGnom;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.net.SocketFactory;

import android.app.Activity;
import android.os.Bundle;

public class DroidGnomActivity extends Activity {
	private static final int DEFAULT_CLUSTER_PORT = 30000;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		try {
			System.out.println("opening socket");

			Socket s = SocketFactory.getDefault().createSocket("localhost",
					DEFAULT_CLUSTER_PORT);

			PrintWriter pw = new PrintWriter(s.getOutputStream());
			pw.print(getResources().getString(R.string.app_name));
			pw.flush();
			s.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("the end");
	}
}