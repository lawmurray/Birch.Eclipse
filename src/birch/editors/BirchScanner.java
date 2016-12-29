package birch.editors;

import org.eclipse.jface.text.*;
import org.eclipse.jface.text.rules.*;
import org.eclipse.swt.*;

public class BirchScanner extends RuleBasedScanner {

	public BirchScanner(ColorManager manager) {
		Token comment = new Token(new TextAttribute(
				manager.getColor(IBirchColorConstants.COMMENT)));
		Token doubleComment = new Token(new TextAttribute(
				manager.getColor(IBirchColorConstants.DOUBLE_COMMENT)));
		//Token block = new Token(new TextAttribute(
		//		manager.getColor(IBirchColorConstants.BLOCK)));
		Token raw = new Token(new TextAttribute(
				manager.getColor(IBirchColorConstants.RAW)));
		Token rawKeyword = new Token(new TextAttribute(
				manager.getColor(IBirchColorConstants.RAW)));
		Token keyword = new Token(new TextAttribute(
				manager.getColor(IBirchColorConstants.KEYWORD), null, SWT.BOLD));
		Token name = new Token(new TextAttribute(
				manager.getColor(IBirchColorConstants.DEFAULT)));
		Token type = new Token(new TextAttribute(
				manager.getColor(IBirchColorConstants.TYPE)));
		Token literal = new Token(new TextAttribute(
				manager.getColor(IBirchColorConstants.LITERAL)));
		Token weakOperator = new Token(new TextAttribute(
				manager.getColor(IBirchColorConstants.RAW)));
		Token string = new Token(new TextAttribute(
				manager.getColor(IBirchColorConstants.LITERAL)));
		Token expression = new Token(new TextAttribute(
				manager.getColor(IBirchColorConstants.EXPRESSION)));

		WordRule rawKeywordRule = new WordRule(new IWordDetector() {
			public boolean isWordStart(char c) {
				return Character.isJavaIdentifierStart(c);
			}

			public boolean isWordPart(char c) {
				return Character.isJavaIdentifierPart(c);
			}
		});
		WordRule keywordRule = new WordRule(new IWordDetector() {
			public boolean isWordStart(char c) {
				return Character.isJavaIdentifierStart(c);
			}

			public boolean isWordPart(char c) {
				return Character.isJavaIdentifierPart(c);
			}
		});
		WordRule nameRule = new WordRule(new IWordDetector() {
			public boolean isWordStart(char c) {
				return Character.isJavaIdentifierStart(c) && Character.isLowerCase(c);
			}

			public boolean isWordPart(char c) {
				return Character.isJavaIdentifierPart(c);
			}
		}, name);
		WordRule typeRule = new WordRule(new IWordDetector() {
			public boolean isWordStart(char c) {
				return Character.isJavaIdentifierStart(c) && Character.isUpperCase(c);
			}

			public boolean isWordPart(char c) {
				return Character.isJavaIdentifierPart(c);
			}
		}, type);
		WordRule literalRule = new WordRule(new IWordDetector() {
			public boolean isWordStart(char c) {
				return Character.isJavaIdentifierStart(c);
			}

			public boolean isWordPart(char c) {
				return Character.isJavaIdentifierPart(c);
			}
		});
		
		WordRule expressionRule = new WordRule(new IWordDetector() {
			public boolean isWordStart(char c) {
				return c == '@' || c == '%';
			}

			public boolean isWordPart(char c) {
				return Character.isJavaIdentifierPart(c);
			}
		}, expression);

		rawKeywordRule.addWord("cpp", rawKeyword);
		rawKeywordRule.addWord("hpp", rawKeyword);

		keywordRule.addWord("import", keyword);
		keywordRule.addWord("function", keyword);
		keywordRule.addWord("model", keyword);
		keywordRule.addWord("program", keyword);
		keywordRule.addWord("if", keyword);
		keywordRule.addWord("else", keyword);
		keywordRule.addWord("while", keyword);
		keywordRule.addWord("this", keyword);

		literalRule.addWord("true", literal);
		literalRule.addWord("false", literal);
		
		setRules(new IRule[] {
				new WhitespaceRule(new IWhitespaceDetector() {
					public boolean isWhitespace(char c) {
						return Character.isWhitespace(c);
					}
				}),
				new NumberRule(literal),
				expressionRule,
				keywordRule,
				rawKeywordRule,
				literalRule,
				nameRule,
				typeRule,
				new WordPatternRule(new OperatorWordDetector(), ",", "", weakOperator),
				new WordPatternRule(new OperatorWordDetector(), ";", "", weakOperator),
				new WordPatternRule(new OperatorWordDetector(), ":", "", weakOperator),
				new WordPatternRule(new OperatorWordDetector(), "(", "", weakOperator),
				new WordPatternRule(new OperatorWordDetector(), ")", "", weakOperator),
				new WordPatternRule(new OperatorWordDetector(), "[", "", weakOperator),
				new WordPatternRule(new OperatorWordDetector(), "]", "", weakOperator),
				new WordPatternRule(new OperatorWordDetector(), "{", "", weakOperator),
				new WordPatternRule(new OperatorWordDetector(), "}", "", weakOperator),
				new MultiLineRule("/**", "*/", doubleComment),
				new MultiLineRule("/*", "*/", comment),
				new EndOfLineRule("//", comment),
				new MultiLineRule("{{", "}}", raw),
				//new MultiLineRule("{", "}", block),
				new SingleLineRule("\"", "\"", string, '\\'),
				new SingleLineRule("'", "'", string, '\\'),
				});
	}
}
