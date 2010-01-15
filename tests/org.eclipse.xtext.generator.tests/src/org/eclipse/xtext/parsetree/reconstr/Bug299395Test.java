/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.parsetree.reconstr;

import org.eclipse.xtext.junit.AbstractXtextTests;
import org.eclipse.xtext.parsetree.reconstr.bug299395.Bug299395Factory;
import org.eclipse.xtext.parsetree.reconstr.bug299395.Model;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class Bug299395Test extends AbstractXtextTests {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		with(Bug299395TestLanguageStandaloneSetup.class);
	}
	
	public void testSerialization() {
		Model model = Bug299395Factory.eINSTANCE.createModel();
		String serialized = getSerializer().serialize(model);
		assertEquals("", serialized);
	}
	
	public void testSerialization_02() {
		Model model = Bug299395Factory.eINSTANCE.createModel();
		model.getUnused().add("Foo");
		String serialized = getSerializer().serialize(model);
		assertEquals("{ \"Foo\" }", serialized);
	}
	
	public void testSerialization_03() {
		Model model = Bug299395Factory.eINSTANCE.createModel();
		model.getUnused().add("Foo");
		model.getUnused().add("Bar");
		model.getUnused().add("Zonk");
		String serialized = getSerializer().serialize(model);
		assertEquals("{ \"Foo\" } { \"Bar\" } { \"Zonk\" }", serialized);
	}
	
	public void testSerialization_04() {
		Model model = Bug299395Factory.eINSTANCE.createModel();
		model.getKey().add("Key1");
		model.getValue().add("Value1");
		model.getKey().add("Key2");
		model.getValue().add("Value2");
		String serialized = getSerializer().serialize(model);
		assertEquals("[ \"Key1\" \"Value1\" \"Key2\" \"Value2\" ]", serialized);
	}
	
	public void testSerialization_05() {
		Model model = Bug299395Factory.eINSTANCE.createModel();
		model.getUnused().add("Unused");
		model.getKey().add("Key1");
		model.getValue().add("Value1");
		model.getKey().add("Key2");
		model.getValue().add("Value2");
		String serialized = getSerializer().serialize(model);
		assertEquals("{ \"Unused\" } [ \"Key1\" \"Value1\" \"Key2\" \"Value2\" ]", serialized);
	}
}
