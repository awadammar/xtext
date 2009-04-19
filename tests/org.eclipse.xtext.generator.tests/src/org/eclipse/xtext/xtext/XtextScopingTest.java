/*******************************************************************************
 * Copyright (c) 2009 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xtext;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.Group;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.XtextPackage;
import org.eclipse.xtext.XtextStandaloneSetup;
import org.eclipse.xtext.junit.AbstractXtextTests;
import org.eclipse.xtext.resource.XtextResourceSet;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class XtextScopingTest extends AbstractXtextTests {
	
	private Grammar grammar;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		with(XtextStandaloneSetup.class);
		XtextResourceSet resourceSet = get(XtextResourceSet.class);
		resourceSet.setClasspathURIContext(getClass().getClassLoader());
		Resource resource = resourceSet.getResource(
				URI.createURI("classpath:/org/eclipse/xtext/grammarinheritance/ConcreteTestLanguage.xtext"), true);
		grammar = (Grammar) resource.getContents().get(0);
	}
	
	@Override
	protected void tearDown() throws Exception {
		grammar = null;
		super.tearDown();
	}
	
	public void testSetup() {
		assertNotNull(grammar);
	}

	public void testScope_01() {
		ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(grammar, "AbstractCallOverridenParserRule");
		assertNotNull(rule);
		Group group = (Group) rule.getAlternatives();
		Assignment ass = (Assignment) group.getTokens().get(1);
		assertNotNull(ass);
		RuleCall call = (RuleCall) ass.getTerminal();
		AbstractRule calledRule = call.getRule();
		assertNotNull(calledRule);
		String text = getLinkingService().getLinkText(grammar, calledRule, XtextPackage.Literals.RULE_CALL__RULE, call);
		assertNotNull(text);
		assertEquals(calledRule.getName(), text);
	}
}
