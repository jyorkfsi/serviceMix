package fsi.algorithm.impl;

import java.io.IOException;
import java.util.Properties;

/*
 * Used to retrieve properties from POM.xml
 */
public class PropertyRetriever {
	private String versionId = "";
	private String artifactId = "";
	
	public PropertyRetriever() {
		getProperties();
	}

	public void getProperties() {
		Properties prop = new Properties();
		try {
			prop.load(this.getClass().getResourceAsStream("/project.properties"));
			System.out.println("retrieving version number & equations.");
		} catch (IOException e) {
			System.out.println("IOException!");
			e.printStackTrace();
		}
		versionId = prop.getProperty("version");
		artifactId = prop.getProperty("artifactId");
	}

	public String getVersionId() {
		return versionId;
	}

	public String getArtifactId() {
		return artifactId;
	}
}
