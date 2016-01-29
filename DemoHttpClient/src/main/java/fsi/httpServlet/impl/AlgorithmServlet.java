package fsi.httpServlet.impl;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.util.tracker.ServiceTracker;

import fsi.service.IAlgorithmInterface;

public class AlgorithmServlet extends HttpServlet {
	private static final long serialVersionUID = 1110490906466282279L;
	private ServiceTracker serviceTracker;

	public AlgorithmServlet(ServiceTracker serviceTracker) {
		this.serviceTracker = serviceTracker;
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) {
		try {
			res.getOutputStream().println("<html><body>");
			res.getOutputStream()
					.println("<form method=\"post\" target=\"_blank\"> <p>Enter Threshold value:</p> "
							+ "<input type=\"number\" name=\"place\"></div> <input id=\"submit\" type=\"submit\"  "
							+ "value=\"Submit\"></form>");
			
			//OSGI serviceTracker retrieving first available implementation of IAlgorithmInterface
			IAlgorithmInterface broker = (IAlgorithmInterface) serviceTracker.getService();
			//broker set to null if no implementations are available.
			if (broker != null) {
				res.getOutputStream().println("Maximum threshold: " + broker.getThreshold() +"<br>");
				res.getOutputStream().println(broker.getVersion());
			} else {
				res.getOutputStream().println("No algorithm services available.");
			}
			
			
			res.getOutputStream().println("</body></html>");
		} catch (IOException e) {
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) {
		Double userInput = 0.0;
		if (!req.getParameter("place").isEmpty()) {
			userInput = Double.parseDouble(req.getParameter("place"));
		}
		try {
			res.getOutputStream().println("<html><body>");
			res.getOutputStream()
					.println("<form method=\"post\"> <p>Enter Threshold value:</p> "
							+ "<input type=\"number\" name=\"place\"></div> <input id=\"submit\" type=\"submit\"  "
							+ "value=\"Submit\"></form>");
			
			//OSGI service tracker retrieving first available implementation of IAlgorithmInterface
			IAlgorithmInterface broker = (IAlgorithmInterface) serviceTracker.getService();
			//broker set to null if none available
			if (broker != null) {
				if (broker.thresholdTest(userInput)) {
					res.getOutputStream().println(userInput + " is safely under the threshold. <br>");
				} else {
					res.getOutputStream().println("WARNING! " + userInput + " is over the threshold! <br>");
				}
				res.getOutputStream().println("Maximum threshold: " + broker.getThreshold() +"<br>");
				res.getOutputStream().println(broker.getVersion());
			} else {
				res.getOutputStream().println("No algorithm services available.");
			}
			res.getOutputStream().println("</body></html>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
