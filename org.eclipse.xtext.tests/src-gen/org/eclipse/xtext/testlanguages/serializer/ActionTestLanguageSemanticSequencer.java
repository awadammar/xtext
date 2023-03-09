/*******************************************************************************
 * Copyright (c) 2010, 2023 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.testlanguages.serializer;

import com.google.inject.Inject;
import java.util.Set;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Parameter;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.serializer.ISerializationContext;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;
import org.eclipse.xtext.testlanguages.actionLang.ActionLangPackage;
import org.eclipse.xtext.testlanguages.actionLang.Child;
import org.eclipse.xtext.testlanguages.actionLang.Parent;
import org.eclipse.xtext.testlanguages.services.ActionTestLanguageGrammarAccess;

@SuppressWarnings("all")
public class ActionTestLanguageSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private ActionTestLanguageGrammarAccess grammarAccess;
	
	@Override
	public void sequence(ISerializationContext context, EObject semanticObject) {
		EPackage epackage = semanticObject.eClass().getEPackage();
		ParserRule rule = context.getParserRule();
		Action action = context.getAssignedAction();
		Set<Parameter> parameters = context.getEnabledBooleanParameters();
		if (epackage == ActionLangPackage.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case ActionLangPackage.CHILD:
				sequence_Child(context, (Child) semanticObject); 
				return; 
			case ActionLangPackage.PARENT:
				sequence_Model(context, (Parent) semanticObject); 
				return; 
			}
		if (errorAcceptor != null)
			errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * <pre>
	 * Contexts:
	 *     Model returns Child
	 *     Model.Parent_1_0 returns Child
	 *     Child returns Child
	 *
	 * Constraint:
	 *     name=ID
	 * </pre>
	 */
	protected void sequence_Child(ISerializationContext context, Child semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, ActionLangPackage.Literals.CHILD__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ActionLangPackage.Literals.CHILD__NAME));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getChildAccess().getNameIDTerminalRuleCall_0(), semanticObject.getName());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Model returns Parent
	 *
	 * Constraint:
	 *     (left=Model_Parent_1_0 right=Child)
	 * </pre>
	 */
	protected void sequence_Model(ISerializationContext context, Parent semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, ActionLangPackage.Literals.PARENT__LEFT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ActionLangPackage.Literals.PARENT__LEFT));
			if (transientValues.isValueTransient(semanticObject, ActionLangPackage.Literals.PARENT__RIGHT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ActionLangPackage.Literals.PARENT__RIGHT));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getModelAccess().getParentLeftAction_1_0(), semanticObject.getLeft());
		feeder.accept(grammarAccess.getModelAccess().getRightChildParserRuleCall_1_1_0(), semanticObject.getRight());
		feeder.finish();
	}
	
	
}
