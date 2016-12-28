/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.web.example.jetty;

import com.google.inject.Binder;
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor;
import org.eclipse.xtext.web.example.jetty.AbstractStatemachineWebModule;
import org.eclipse.xtext.web.example.jetty.resource.StatemachineContentTypeProvider;
import org.eclipse.xtext.web.example.jetty.resource.StatemachineResourceSetProvider;
import org.eclipse.xtext.web.server.generator.IContentTypeProvider;
import org.eclipse.xtext.web.server.model.IWebResourceSetProvider;
import org.eclipse.xtext.web.server.persistence.FileResourceHandler;
import org.eclipse.xtext.web.server.persistence.IResourceBaseProvider;
import org.eclipse.xtext.web.server.persistence.IServerResourceHandler;

/**
 * Use this class to register additional components to be used within the web application.
 */
@FinalFieldsConstructor
@SuppressWarnings("all")
public class StatemachineWebModule extends AbstractStatemachineWebModule {
  private final IResourceBaseProvider resourceBaseProvider;
  
  @Override
  public Class<? extends IContentTypeProvider> bindIContentTypeProvider() {
    return StatemachineContentTypeProvider.class;
  }
  
  public Class<? extends IWebResourceSetProvider> bindIWebResourceSetProvider() {
    return StatemachineResourceSetProvider.class;
  }
  
  public void configureResourceBaseProvider(final Binder binder) {
    if ((this.resourceBaseProvider != null)) {
      binder.<IResourceBaseProvider>bind(IResourceBaseProvider.class).toInstance(this.resourceBaseProvider);
    }
  }
  
  public Class<? extends IServerResourceHandler> bindIServerResourceHandler() {
    return FileResourceHandler.class;
  }
  
  public StatemachineWebModule(final IResourceBaseProvider resourceBaseProvider) {
    super();
    this.resourceBaseProvider = resourceBaseProvider;
  }
}
