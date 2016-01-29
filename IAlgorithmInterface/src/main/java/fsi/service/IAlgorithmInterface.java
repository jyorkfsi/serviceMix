package fsi.service;

/*
 * Algorithm Interface that will be hosted on Felix.
 */
public interface IAlgorithmInterface {
	String getVersion();
	
	Double getThreshold();
	
	Boolean thresholdTest(Double threshold);
}
