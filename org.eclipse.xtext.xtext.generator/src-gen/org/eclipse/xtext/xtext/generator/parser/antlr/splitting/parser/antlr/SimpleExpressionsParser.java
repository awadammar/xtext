/*
 * generated by Xtext
 */
package org.eclipse.xtext.xtext.generator.parser.antlr.splitting.parser.antlr;

import com.google.inject.Inject;

import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.xtext.generator.parser.antlr.splitting.services.SimpleExpressionsGrammarAccess;

public class SimpleExpressionsParser extends org.eclipse.xtext.parser.antlr.AbstractAntlrParser {
	
	@Inject
	private SimpleExpressionsGrammarAccess grammarAccess;
	
	@Override
	protected void setInitialHiddenTokens(XtextTokenStream tokenStream) {
		tokenStream.setInitialHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
	}
	
	@Override
	protected org.eclipse.xtext.xtext.generator.parser.antlr.splitting.parser.antlr.internal.InternalSimpleExpressionsParser createParser(XtextTokenStream stream) {
		return new org.eclipse.xtext.xtext.generator.parser.antlr.splitting.parser.antlr.internal.InternalSimpleExpressionsParser(stream, getGrammarAccess());
	}
	
	@Override 
	protected String getDefaultRuleName() {
		return "IfCondition";
	}
	
	public SimpleExpressionsGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(SimpleExpressionsGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
	
}
