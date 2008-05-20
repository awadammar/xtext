package org.eclipse.xtext.parsetree;

import java.util.Iterator;
import java.util.regex.Pattern;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.LexerRule;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.core.parser.ITokenTypes;
import org.eclipse.xtext.core.parsetree.AbstractNode;
import org.eclipse.xtext.core.parsetree.CompositeNode;
import org.eclipse.xtext.core.parsetree.LeafNode;
import org.eclipse.xtext.core.parsetree.NodeAdapter;
import org.eclipse.xtext.generator.tests.AbstractGeneratorTest;
import org.eclipse.xtext.test.TestLanguage;
import org.eclipse.xtext.test.parser.TestLanguageASTFactory;

public class NodeModelTest extends AbstractGeneratorTest {

	private static final String MODEL = "reducible 'x' choice optional y choice z reducible 'x' 'y'";

	public void testNavigabilityAst2Node() throws Exception {
		EObject object = getRootAstElement(MODEL);
		checkNavigabilityAst2Node(object);
		for (Iterator<EObject> i = object.eAllContents(); i.hasNext();) {
			checkNavigabilityAst2Node(i.next());
		}
	}

	public void testNavigabilityNode2Ast() throws Exception {
		EObject object = getRootAstElement(MODEL);
		EList<Adapter> adapters = object.eAdapters();
		assert (adapters.size() == 1);
		NodeAdapter adapter = (NodeAdapter) adapters.get(0);
		CompositeNode rootNode = adapter.getParserNode();
		assertTrue(rootNode.eContainer() == null);
		checkNavigabilityNode2Ast(rootNode);
		for (Iterator<EObject> i = rootNode.eAllContents(); i.hasNext();) {
			checkNavigabilityNode2Ast((AbstractNode) i.next());
		}
	}

	public void testGrammarElement() throws Exception {
		AbstractNode rootNode = getRootNode(MODEL);
		EObject rootGrammarElement = rootNode.getGrammarElement();
		assertTrue(rootGrammarElement instanceof ParserRule);
		for (Iterator<EObject> i = rootNode.eAllContents(); i.hasNext();) {
			EObject next = i.next();
			if (next instanceof CompositeNode) {
				CompositeNode compositeNode = (CompositeNode) next;
				EObject grammarElement = compositeNode.getGrammarElement();
				assertTrue(GrammarUtil.isParserRuleCall(grammarElement)
						|| grammarElement instanceof Action);
			} else if (next instanceof LeafNode) {
				LeafNode leafNode = (LeafNode) next;
				EObject grammarElement = leafNode.getGrammarElement();
				String tokenType = leafNode.tokenType();
				if (grammarElement instanceof Keyword) {
					assertEquals(ITokenTypes.KEYWORD, tokenType);
				} else if (GrammarUtil.isWhitespaceLexerRule(grammarElement)) {
					assertEquals(ITokenTypes.WHITESPACE, tokenType);
				} else if (GrammarUtil.isLexerRuleCall(grammarElement)) {
					assertEquals(((LexerRule) GrammarUtil
							.calledRule((RuleCall) grammarElement))
							.getTokenType(), tokenType);
				} else {
					fail("LeafNodes must correspond to keywords, whitespaces or lexerRules");
				}
			}
		}
	}

	private void checkNavigabilityAst2Node(EObject object) {
		EList<Adapter> adapters = object.eAdapters();
		assertEquals(1, adapters.size());
		NodeAdapter adapter = (NodeAdapter) adapters.get(0);
		AbstractNode parsetreeNode = adapter.getParserNode();
		assertEquals(object, parsetreeNode.getElement());
	}

	private void checkNavigabilityNode2Ast(AbstractNode node) {
		EObject astElement = node.getElement();
		if (astElement != null) {
			EList<Adapter> adapters = astElement.eAdapters();
			assertEquals(1, adapters.size());
			NodeAdapter adapter = (NodeAdapter) adapters.get(0);
			assertEquals(node, adapter.getParserNode());
		} else {
			assert (node.getGrammarElement() == null
					|| node.getGrammarElement() instanceof Keyword || GrammarUtil
					.isLexerRuleCall(node.getGrammarElement()));
		}
	}

	public void testTokenTexts() throws Exception {
		Pattern whitespacePattern = Pattern.compile("\\s*");
		String[] tokenTexts = MODEL.split(" ");
		int tokenIndex = 0;
		AbstractNode rootNode = getRootNode(MODEL);
		for (Iterator<EObject> i = rootNode.eAllContents(); i.hasNext();) {
			EObject next = i.next();
			if (next instanceof LeafNode) {
				LeafNode leafNode = (LeafNode) next;
				String tokenText = leafNode.getText();
				if (!whitespacePattern.matcher(tokenText).matches()) {
					assertEquals(tokenTexts[tokenIndex++], tokenText);
				}
			}
		}
	}

	private EObject getRootAstElement(String model) throws Exception {
		EObject object = (EObject) parse(model, new TestLanguageASTFactory());
		return object;
	}

	private AbstractNode getRootNode(String model) throws Exception {
		EObject object = getRootAstElement(model);
		NodeAdapter adapter = (NodeAdapter) object.eAdapters().get(0);
		AbstractNode node = adapter.getParserNode();
		return node;
	}

	@Override
	protected Class<?> getTheClass() {
		return TestLanguage.class;
	}

}
