/*
 * generated by Xtext
 */
package org.eclipse.xtext.valueconverter.parser.antlr;

import java.io.InputStream;
import org.eclipse.xtext.parser.antlr.IAntlrTokenFileProvider;

public class QualifiedNameTestLanguageAntlrTokenFileProvider implements IAntlrTokenFileProvider {

	@Override
	public InputStream getAntlrTokenFile() {
		ClassLoader classLoader = getClass().getClassLoader();
		return classLoader.getResourceAsStream("org/eclipse/xtext/valueconverter/parser/antlr/internal/InternalQualifiedNameTestLanguage.tokens");
	}
}
