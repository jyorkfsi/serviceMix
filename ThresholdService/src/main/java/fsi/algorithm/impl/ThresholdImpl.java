package fsi.algorithm.impl;


import fsi.service.IAlgorithmInterface;

public class ThresholdImpl implements IAlgorithmInterface{

	
	public ThresholdImpl() {
	}
	private Double threshold = 200.0;
	private PropertyRetriever props = new PropertyRetriever();
	
	public String getVersion() {
		return "Threshold Algorithm version " + props.getVersionId();
	}

	public Double getThreshold() {
		return threshold;
	}

	public Boolean thresholdTest(Double threshold) {
		return this.threshold > threshold;
	}
	
}
