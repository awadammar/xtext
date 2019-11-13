/**
 * Copyright (c) 2013, 2019 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.builder.standalone.compiler;

/**
 * @author Dennis Huebner - Initial contribution and API
 */
public class CompilerConfiguration {
	private String sourceLevel = "1.5";

	private String targetLevel = "1.5";

	private boolean verbose;

	private boolean skipAnnotationProcessing;

	private boolean preserveInformationAboutFormalParameters;

	public String getSourceLevel() {
		return sourceLevel;
	}

	public void setSourceLevel(String sourceLevel) {
		this.sourceLevel = sourceLevel;
	}

	public String getTargetLevel() {
		return targetLevel;
	}

	public void setTargetLevel(String targetLevel) {
		this.targetLevel = targetLevel;
	}

	public boolean isVerbose() {
		return verbose;
	}

	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}

	public boolean isSkipAnnotationProcessing() {
		return skipAnnotationProcessing;
	}

	public void setSkipAnnotationProcessing(boolean skipAnnotationProcessing) {
		this.skipAnnotationProcessing = skipAnnotationProcessing;
	}

	public boolean isPreserveInformationAboutFormalParameters() {
		return preserveInformationAboutFormalParameters;
	}

	public void setPreserveInformationAboutFormalParameters(boolean preserveInformationAboutFormalParameters) {
		this.preserveInformationAboutFormalParameters = preserveInformationAboutFormalParameters;
	}
}
