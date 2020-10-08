package com.ifi.simple_swt;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class App {
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
}
