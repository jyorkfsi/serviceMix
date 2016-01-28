package fsi.httpServlet.impl;

import javax.servlet.ServletException;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;
import org.osgi.util.tracker.ServiceTracker;

import com.forwardslope.felixlogger.Logger;

import fsi.service.IAlgorithmInterface;

public class Activator implements BundleActivator {
	private ServiceTracker httpSrvTracker;
	private ServiceTracker thresholdTracker;
	private Logger log;

	public void start(BundleContext context) throws Exception {
		log = new Logger(context, this.getClass().getCanonicalName());
		thresholdTracker = new ServiceTracker(context, IAlgorithmInterface.class.getName(), null);
		httpSrvTracker = new ServiceTracker(context, HttpService.class.getName(), null) {
			public Object addingService(ServiceReference reference) {
				HttpService httpService = (HttpService) super.addingService(reference);
				try {
					httpService.registerServlet("/", new AlgorithmServlet(thresholdTracker), null, null);
				} catch (ServletException e) {
				} catch (NamespaceException e) {
				}
				return httpService;
			}

			public void removedService(ServiceReference reference, Object service) {
				((HttpService) service).unregister("/");
				super.removedService(reference, service);
			}
		};

		thresholdTracker.open();
		httpSrvTracker.open();
		System.out.println("Started algorithm servlet.");
		log.logInfo("http servlet has been started.");
	}

	public void stop(BundleContext ctx) throws Exception {
		thresholdTracker.close();
		httpSrvTracker.close();
		log.logInfo("http servlet has been stopped.");
		System.out.println("Stopped algorithm servlet.");
	}
}
