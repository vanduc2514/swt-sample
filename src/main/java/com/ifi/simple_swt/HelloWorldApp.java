package com.ifi.simple_swt;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;

import javax.naming.NamingException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ifi.message.MessageResolver;

public class HelloWorldApp {
	private MessageResolver messageResolver;

	public HelloWorldApp() throws NamingException {
		messageResolver = new MessageResolver();
	}

	protected Shell shell;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Application Start");
		try {
			HelloWorldApp window = new HelloWorldApp();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(300, 150);
		shell.setText("Hello World App");
		shell.setLayout(new GridLayout(3, false));
		Label lblTitle = new Label(shell, SWT.NONE);
		Label lblEnterYourName = new Label(shell, SWT.NONE);
		Text inputText = new Text(shell, SWT.BORDER);
		Button btnClickMe = new Button(shell, SWT.NONE);
		Label lblServerResponse = new Label(shell, SWT.NONE);
		Label lblResponsecontent = new Label(shell, SWT.NONE);

		lblTitle.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.BOLD));
		GridData gd_lblHelloWorldApp = new GridData(SWT.CENTER, SWT.TOP, false, false, 4, 3);
		gd_lblHelloWorldApp.heightHint = 33;
		lblTitle.setLayoutData(gd_lblHelloWorldApp);
		lblTitle.setText("Hello World App");

		lblEnterYourName.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		lblEnterYourName.setText("Enter your name:");

		btnClickMe.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		btnClickMe.setText("Click me");

		GridData gd_inputText = new GridData(SWT.LEFT, SWT.TOP, true, false, 1, 1);
		gd_inputText.widthHint = 108;
		inputText.setLayoutData(gd_inputText);

		GridData gd_lblServerResponse = new GridData(SWT.LEFT, SWT.BOTTOM, false, false, 1, 1);
		gd_lblServerResponse.verticalIndent = 8;
		gd_lblServerResponse.heightHint = 24;
		lblServerResponse.setLayoutData(gd_lblServerResponse);
		lblServerResponse.setText("Server Response:");

		GridData gd_lblResponsecontent = new GridData(SWT.LEFT, SWT.BOTTOM, false, false, 2, 1);
		gd_lblResponsecontent.widthHint = 179;
		gd_lblResponsecontent.verticalIndent = 8;
		gd_lblResponsecontent.heightHint = 24;
		lblResponsecontent.setLayoutData(gd_lblResponsecontent);
		lblResponsecontent.setText("response content");
		lblResponsecontent.setVisible(true);

		btnClickMe.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				String name = inputText.getText();
				String serverResponse = "";
				if (name.equals("")) {
					serverResponse = messageResolver.getMessage();
				} else {
					serverResponse = messageResolver.getMessage(name);
				}
				lblResponsecontent.setText(serverResponse);
				System.out.println(inputText.getText());
			}
		});
	}
}
