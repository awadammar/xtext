/*******************************************************************************
 * Copyright (c) 2010, 2023 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.parser.assignments.serializer;

import com.google.inject.Inject;
import java.util.Set;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Parameter;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.parser.assignments.bug288432Test.Body;
import org.eclipse.xtext.parser.assignments.bug288432Test.Bug288432TestPackage;
import org.eclipse.xtext.parser.assignments.bug288432Test.Foo;
import org.eclipse.xtext.parser.assignments.bug288432Test.MyElement;
import org.eclipse.xtext.parser.assignments.bug288432Test.MyInt;
import org.eclipse.xtext.parser.assignments.bug288432Test.ParameterRef;
import org.eclipse.xtext.parser.assignments.services.Bug288432TestLanguageGrammarAccess;
import org.eclipse.xtext.serializer.ISerializationContext;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;

@SuppressWarnings("all")
public class Bug288432TestLanguageSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private Bug288432TestLanguageGrammarAccess grammarAccess;
	
	@Override
	public void sequence(ISerializationContext context, EObject semanticObject) {
		EPackage epackage = semanticObject.eClass().getEPackage();
		ParserRule rule = context.getParserRule();
		Action action = context.getAssignedAction();
		Set<Parameter> parameters = context.getEnabledBooleanParameters();
		if (epackage == Bug288432TestPackage.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case Bug288432TestPackage.BODY:
				sequence_Body(context, (Body) semanticObject); 
				return; 
			case Bug288432TestPackage.FOO:
				sequence_Foo(context, (Foo) semanticObject); 
				return; 
			case Bug288432TestPackage.MY_ELEMENT:
				sequence_MyElement(context, (MyElement) semanticObject); 
				return; 
			case Bug288432TestPackage.MY_INT:
				sequence_MyInt(context, (MyInt) semanticObject); 
				return; 
			case Bug288432TestPackage.PARAMETER:
				sequence_Parameter(context, (org.eclipse.xtext.parser.assignments.bug288432Test.Parameter) semanticObject); 
				return; 
			case Bug288432TestPackage.PARAMETER_REF:
				sequence_ParameterRef(context, (ParameterRef) semanticObject); 
				return; 
			}
		if (errorAcceptor != null)
			errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * <pre>
	 * Contexts:
	 *     Body returns Body
	 *
	 * Constraint:
	 *     ((parameter+=Parameter parameter+=Parameter*)? (content=Content | content=ParameterRef) foo+=Foo+)
	 * </pre>
	 */
	protected void sequence_Body(ISerializationContext context, Body semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Foo returns Foo
	 *
	 * Constraint:
	 *     fooValue=STRING
	 * </pre>
	 */
	protected void sequence_Foo(ISerializationContext context, Foo semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, Bug288432TestPackage.Literals.FOO__FOO_VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Bug288432TestPackage.Literals.FOO__FOO_VALUE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getFooAccess().getFooValueSTRINGTerminalRuleCall_0(), semanticObject.getFooValue());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Content returns MyElement
	 *     MyElement returns MyElement
	 *     ParameterObject returns MyElement
	 *
	 * Constraint:
	 *     (bar=MyInt | bar=ParameterRef)
	 * </pre>
	 */
	protected void sequence_MyElement(ISerializationContext context, MyElement semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     ParameterObject returns MyInt
	 *     MyInt returns MyInt
	 *
	 * Constraint:
	 *     int=INT
	 * </pre>
	 */
	protected void sequence_MyInt(ISerializationContext context, MyInt semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, Bug288432TestPackage.Literals.MY_INT__INT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Bug288432TestPackage.Literals.MY_INT__INT));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getMyIntAccess().getIntINTTerminalRuleCall_0(), semanticObject.getInt());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     ParameterObject returns ParameterRef
	 *     ParameterRef returns ParameterRef
	 *
	 * Constraint:
	 *     parameter=[Parameter|ID]
	 * </pre>
	 */
	protected void sequence_ParameterRef(ISerializationContext context, ParameterRef semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, Bug288432TestPackage.Literals.PARAMETER_REF__PARAMETER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Bug288432TestPackage.Literals.PARAMETER_REF__PARAMETER));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getParameterRefAccess().getParameterParameterIDTerminalRuleCall_0_1(), semanticObject.eGet(Bug288432TestPackage.Literals.PARAMETER_REF__PARAMETER, false));
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Parameter returns Parameter
	 *
	 * Constraint:
	 *     (name=ID value=[ParameterObject|ID]?)
	 * </pre>
	 */
	protected void sequence_Parameter(ISerializationContext context, org.eclipse.xtext.parser.assignments.bug288432Test.Parameter semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
}
