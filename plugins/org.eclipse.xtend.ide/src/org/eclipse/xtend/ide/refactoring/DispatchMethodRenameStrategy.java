/*******************************************************************************
 * Copyright (c) 2012 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtend.ide.refactoring;

import static com.google.common.collect.Lists.*;
import static org.eclipse.xtext.util.Strings.*;

import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.xtend.core.dispatch.DispatchingSupport;
import org.eclipse.xtend.core.jvmmodel.IXtendJvmAssociations;
import org.eclipse.xtend.core.xtend.XtendFunction;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.ui.refactoring.IRefactoringUpdateAcceptor;
import org.eclipse.xtext.ui.refactoring.IRenameStrategy;
import org.eclipse.xtext.ui.refactoring.impl.DefaultRenameStrategyProvider.IInitializable;
import org.eclipse.xtext.ui.refactoring.ui.IRenameElementContext;
import org.eclipse.xtext.xbase.ui.jvmmodel.refactoring.DefaultJvmModelRenameStrategy;

import com.google.inject.Inject;

/**
 * @author Jan Koehnlein - Initial contribution and API
 */
public class DispatchMethodRenameStrategy implements IInitializable {

	@Inject
	private IXtendJvmAssociations associations;
	
	@Inject
	private com.google.inject.Provider<DispatchMethodChildStrategy> childStrategyProvider; 
	
	@Inject
	private DispatchingSupport support;
	
	private List<IRenameStrategy> children = newArrayList(); 
	
	private List<JvmOperation> dispatchers = newArrayList();
	
	public boolean initialize(EObject xtendMethod, IRenameElementContext context) {
		Assert.isLegal(xtendMethod instanceof XtendFunction);
		Assert.isLegal(((XtendFunction) xtendMethod).isDispatch());
		Assert.isLegal(context instanceof DispatchMethodRenameContext);

		System.out.println();
		ResourceSet resourceSet = xtendMethod.eResource().getResourceSet();
		Map<URI, IJavaElement> jvm2JavaElements = ((DispatchMethodRenameContext)context).getJvm2JavaElements();
		for(URI dispatchOperationURI: jvm2JavaElements.keySet()) {
			JvmOperation dispatchOperation = (JvmOperation) resourceSet.getEObject(dispatchOperationURI, true);
			XtendFunction xtendDispatchMethod = associations.getXtendFunction(dispatchOperation);
			if(xtendDispatchMethod != null) {
				if(equal(xtendDispatchMethod.getName(),dispatchOperation.getSimpleName())) {
					dispatchers.add(dispatchOperation);
				} else {
					DispatchMethodChildStrategy childStrategy = childStrategyProvider.get();
					childStrategy.initialize(xtendDispatchMethod, context);
					children.add(childStrategy);
				}
			}
		}
		return !children.isEmpty();
	}

	public static class DispatchMethodChildStrategy extends DefaultJvmModelRenameStrategy {
		@Override
		protected void setInferredJvmElementName(String name, EObject renamedElement) {
			super.setInferredJvmElementName("_" + name, renamedElement);
		}
	}

	public String getOriginalName() {
		return children.get(0).getOriginalName();
	}

	public RefactoringStatus validateNewName(String newName) {
		RefactoringStatus status = new RefactoringStatus();
		for(IRenameStrategy child: children)
			status.merge(child.validateNewName(newName));
		return status;
	}

	public void applyDeclarationChange(String newName, ResourceSet resourceSet) {
		for(IRenameStrategy child: children)
			child.applyDeclarationChange(newName, resourceSet);
		for(JvmOperation dispatcher: dispatchers) {
			dispatcher.setSimpleName(newName);
		}
	}

	public void revertDeclarationChange(ResourceSet resourceSet) {
		for(IRenameStrategy child: children)
			child.revertDeclarationChange(resourceSet);
		for(JvmOperation dispatcher: dispatchers) {
			dispatcher.setSimpleName(getOriginalName());
		}
	}

	public void createDeclarationUpdates(String newName, ResourceSet resourceSet,
			IRefactoringUpdateAcceptor updateAcceptor) {
		for(IRenameStrategy child: children)
			child.createDeclarationUpdates(newName, resourceSet, updateAcceptor);
	}
	
}
