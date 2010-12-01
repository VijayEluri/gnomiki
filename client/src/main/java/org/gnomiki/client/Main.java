package org.gnomiki.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 * 
 */
public class Main {

	public static void main(String[] args) throws Exception {

		// MacOSXMenuAdjuster.adjust();

		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"app-context.xml");

	}
}
