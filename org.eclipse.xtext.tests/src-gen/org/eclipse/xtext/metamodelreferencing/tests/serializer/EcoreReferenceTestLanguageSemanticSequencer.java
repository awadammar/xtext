/*******************************************************************************
 * Copyright (c) 2010, 2023 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.metamodelreferencing.tests.serializer;

import com.google.inject.Inject;
import java.util.Set;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Parameter;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.metamodelreferencing.tests.ecorePerNsURI.EcorePerNsURIPackage;
import org.eclipse.xtext.metamodelreferencing.tests.ecorePerNsURI.ExtendsNsURIEObject;
import org.eclipse.xtext.metamodelreferencing.tests.ecorePerPlatformPlugin.EcorePerPlatformPluginPackage;
import org.eclipse.xtext.metamodelreferencing.tests.ecorePerPlatformPlugin.ExtendsPluginEObject;
import org.eclipse.xtext.metamodelreferencing.tests.ecorePerPlatformResource.EcorePerPlatformResourcePackage;
import org.eclipse.xtext.metamodelreferencing.tests.ecorePerPlatformResource.ExtendsResourceEObject;
import org.eclipse.xtext.metamodelreferencing.tests.ecoreReference.EcoreReferencePackage;
import org.eclipse.xtext.metamodelreferencing.tests.ecoreReference.Model;
import org.eclipse.xtext.metamodelreferencing.tests.ecoreReference.MyNamedElement_01;
import org.eclipse.xtext.metamodelreferencing.tests.ecoreReference.MyNamedElement_02;
import org.eclipse.xtext.metamodelreferencing.tests.ecoreReference.MyNamedElement_03;
import org.eclipse.xtext.metamodelreferencing.tests.services.EcoreReferenceTestLanguageGrammarAccess;
import org.eclipse.xtext.serializer.ISerializationContext;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;

@SuppressWarnings("all")
public class EcoreReferenceTestLanguageSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private EcoreReferenceTestLanguageGrammarAccess grammarAccess;
	
	@Override
	public void sequence(ISerializationContext context, EObject semanticObject) {
		EPackage epackage = semanticObject.eClass().getEPackage();
		ParserRule rule = context.getParserRule();
		Action action = context.getAssignedAction();
		Set<Parameter> parameters = context.getEnabledBooleanParameters();
		if (epackage == EcorePackage.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case EcorePackage.EATTRIBUTE:
				sequence_EAttribute(context, (EAttribute) semanticObject); 
				return; 
			case EcorePackage.EOBJECT:
				sequence_EObject(context, (EObject) semanticObject); 
				return; 
			}
		else if (epackage == EcorePerNsURIPackage.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case EcorePerNsURIPackage.EXTENDS_NS_URIE_OBJECT:
				sequence_ExtendsNsURIEObject(context, (ExtendsNsURIEObject) semanticObject); 
				return; 
			case EcorePerNsURIPackage.MY_EATTRIBUTE:
				sequence_MyEAttribute(context, (EAttribute) semanticObject); 
				return; 
			}
		else if (epackage == EcorePerPlatformPluginPackage.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case EcorePerPlatformPluginPackage.EXTENDS_PLUGIN_EOBJECT:
				sequence_ExtendsPluginEObject(context, (ExtendsPluginEObject) semanticObject); 
				return; 
			}
		else if (epackage == EcorePerPlatformResourcePackage.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case EcorePerPlatformResourcePackage.EXTENDS_RESOURCE_EOBJECT:
				sequence_ExtendsResourceEObject(context, (ExtendsResourceEObject) semanticObject); 
				return; 
			}
		else if (epackage == EcoreReferencePackage.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case EcoreReferencePackage.MODEL:
				sequence_Model(context, (Model) semanticObject); 
				return; 
			case EcoreReferencePackage.MY_NAMED_ELEMENT_01:
				sequence_Unused_01(context, (MyNamedElement_01) semanticObject); 
				return; 
			case EcoreReferencePackage.MY_NAMED_ELEMENT_02:
				sequence_Unused_02(context, (MyNamedElement_02) semanticObject); 
				return; 
			case EcoreReferencePackage.MY_NAMED_ELEMENT_03:
				sequence_Unused_03(context, (MyNamedElement_03) semanticObject); 
				return; 
			}
		if (errorAcceptor != null)
			errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * <pre>
	 * Contexts:
	 *     EAttribute returns EAttribute
	 *
	 * Constraint:
	 *     {EAttribute}
	 * </pre>
	 */
	protected void sequence_EAttribute(ISerializationContext context, EAttribute semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     EObject returns EObject
	 *
	 * Constraint:
	 *     {EObject}
	 * </pre>
	 */
	protected void sequence_EObject(ISerializationContext context, EObject semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     ExtendsNsURIEObject returns ExtendsNsURIEObject
	 *
	 * Constraint:
	 *     (
	 *         name=ValidID 
	 *         (
	 *             eObjectReference1=[EObject|ID] | 
	 *             eObjectReference1=[ExtendsNsURIEObject|ID] | 
	 *             eObjectReference1=[ExtendsPluginEObject|ID] | 
	 *             eObjectReference1=[ExtendsResourceEObject|ID]
	 *         ) 
	 *         (
	 *             eAttributeReference=[MyEAttribute|ID] 
	 *             eObjectReference2=[ExtendsNsURIEObject|ID] 
	 *             eObjectReference3=[ExtendsPluginEObject|ID] 
	 *             eObjectReference4=[ExtendsResourceEObject|ID] 
	 *             eObjectContainment+=ExtendsNsURIEObject? 
	 *             eObjectContainment+=ExtendsPluginEObject? 
	 *             eObjectContainment+=ExtendsResourceEObject? 
	 *             eObjectContainment+=EObject 
	 *             eObjectContainment+=EAttribute 
	 *             eObjectContainment+=MyEAttribute
	 *         )?
	 *     )
	 * </pre>
	 */
	protected void sequence_ExtendsNsURIEObject(ISerializationContext context, ExtendsNsURIEObject semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     ExtendsPluginEObject returns ExtendsPluginEObject
	 *
	 * Constraint:
	 *     (
	 *         name=ValidID 
	 *         (
	 *             eObjectReference1=[EObject|ID] | 
	 *             eObjectReference1=[ExtendsNsURIEObject|ID] | 
	 *             eObjectReference1=[ExtendsPluginEObject|ID] | 
	 *             eObjectReference1=[ExtendsResourceEObject|ID]
	 *         ) 
	 *         (
	 *             eObjectReference2=[ExtendsNsURIEObject|ID] 
	 *             eObjectReference3=[ExtendsPluginEObject|ID] 
	 *             eObjectReference4=[ExtendsResourceEObject|ID] 
	 *             eObjectContainment+=ExtendsNsURIEObject? 
	 *             eObjectContainment+=ExtendsPluginEObject? 
	 *             eObjectContainment+=ExtendsResourceEObject? 
	 *             eObjectContainment+=EObject 
	 *             eObjectContainment+=EAttribute 
	 *             eObjectContainment+=MyEAttribute
	 *         )?
	 *     )
	 * </pre>
	 */
	protected void sequence_ExtendsPluginEObject(ISerializationContext context, ExtendsPluginEObject semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     ExtendsResourceEObject returns ExtendsResourceEObject
	 *
	 * Constraint:
	 *     (
	 *         name=ValidID 
	 *         (
	 *             eObjectReference1=[EObject|ID] | 
	 *             eObjectReference1=[ExtendsNsURIEObject|ID] | 
	 *             eObjectReference1=[ExtendsPluginEObject|ID] | 
	 *             eObjectReference1=[ExtendsResourceEObject|ID]
	 *         ) 
	 *         (
	 *             eObjectReference2=[ExtendsNsURIEObject|ID] 
	 *             eObjectReference3=[ExtendsPluginEObject|ID] 
	 *             eObjectReference4=[ExtendsResourceEObject|ID] 
	 *             eObjectContainment+=ExtendsNsURIEObject 
	 *             eObjectContainment+=ExtendsPluginEObject 
	 *             eObjectContainment+=ExtendsResourceEObject 
	 *             eObjectContainment+=EObject 
	 *             eObjectContainment+=EAttribute 
	 *             eObjectContainment+=MyEAttribute
	 *         )?
	 *     )
	 * </pre>
	 */
	protected void sequence_ExtendsResourceEObject(ISerializationContext context, ExtendsResourceEObject semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Model returns Model
	 *
	 * Constraint:
	 *     (extends+=ExtendsNsURIEObject extends+=ExtendsPluginEObject extends+=ExtendsResourceEObject)
	 * </pre>
	 */
	protected void sequence_Model(ISerializationContext context, Model semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     MyEAttribute returns MyEAttribute
	 *
	 * Constraint:
	 *     {MyEAttribute}
	 * </pre>
	 */
	protected void sequence_MyEAttribute(ISerializationContext context, EAttribute semanticObject) {
		genericSequencer.createSequence(context, (EObject) semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Unused_01 returns MyNamedElement_01
	 *
	 * Constraint:
	 *     name=ValidID
	 * </pre>
	 */
	protected void sequence_Unused_01(ISerializationContext context, MyNamedElement_01 semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, EcorePerNsURIPackage.Literals.EXTENDS_NS_URIE_OBJECT__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, EcorePerNsURIPackage.Literals.EXTENDS_NS_URIE_OBJECT__NAME));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getUnused_01Access().getNameValidIDParserRuleCall_1_0(), semanticObject.getName());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Unused_02 returns MyNamedElement_02
	 *
	 * Constraint:
	 *     name=ValidID
	 * </pre>
	 */
	protected void sequence_Unused_02(ISerializationContext context, MyNamedElement_02 semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, EcorePerPlatformResourcePackage.Literals.EXTENDS_RESOURCE_EOBJECT__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, EcorePerPlatformResourcePackage.Literals.EXTENDS_RESOURCE_EOBJECT__NAME));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getUnused_02Access().getNameValidIDParserRuleCall_1_0(), semanticObject.getName());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Unused_03 returns MyNamedElement_03
	 *
	 * Constraint:
	 *     name=ValidID
	 * </pre>
	 */
	protected void sequence_Unused_03(ISerializationContext context, MyNamedElement_03 semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, EcorePerPlatformPluginPackage.Literals.EXTENDS_PLUGIN_EOBJECT__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, EcorePerPlatformPluginPackage.Literals.EXTENDS_PLUGIN_EOBJECT__NAME));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getUnused_03Access().getNameValidIDParserRuleCall_1_0(), semanticObject.getName());
		feeder.finish();
	}
	
	
}
