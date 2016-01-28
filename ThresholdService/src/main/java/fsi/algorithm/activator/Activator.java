package fsi.algorithm.activator;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;


import fsi.algorithm.impl.ThresholdImpl;
import fsi.service.IAlgorithmInterface;

public class Activator implements BundleActivator{

	
	public void start(BundleContext context) throws Exception {
		Hashtable<String,String> props = new Hashtable<String,String>();
		props.put("version", "1.0");
		context.registerService(IAlgorithmInterface.class.getName(), new ThresholdImpl(), props);
	}

	public void stop(BundleContext context) throws Exception {
	}


}
