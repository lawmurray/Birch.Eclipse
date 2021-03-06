package org.birch_lang.editors;

import org.eclipse.jface.text.*;
import org.eclipse.jface.text.rules.*;
import org.eclipse.swt.*;

public class BirchScanner extends RuleBasedScanner {	
  public BirchScanner(ColorManager manager) {
		Token singleLineComment = new Token(new TextAttribute(manager.singleLineCommentColor));
		Token multiLineComment = new Token(new TextAttribute(manager.multiLineCommentColor));
		Token doubleComment = new Token(new TextAttribute(manager.doubleCommentColor));
		Token raw = new Token(new TextAttribute(manager.rawColor));
		Token rawKeyword = new Token(new TextAttribute(manager.rawColor));
		Token keyword = new Token(new TextAttribute(manager.keywordColor, null, SWT.BOLD));
		Token name = new Token(new TextAttribute(null));
		Token type = new Token(new TextAttribute(manager.typeColor));
		Token literal = new Token(new TextAttribute(manager.literalColor));
		Token weakOperator = new Token(new TextAttribute(manager.rawColor));
		Token string = new Token(new TextAttribute(manager.literalColor));

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
		
		rawKeywordRule.addWord("cpp", rawKeyword);
		rawKeywordRule.addWord("hpp", rawKeyword);

		keywordRule.addWord("function", keyword);
		keywordRule.addWord("fiber", keyword);
		keywordRule.addWord("program", keyword);
		keywordRule.addWord("operator", keyword);
		keywordRule.addWord("class", keyword);
		keywordRule.addWord("type", keyword);
		keywordRule.addWord("auto", keyword);
		keywordRule.addWord("if", keyword);
		keywordRule.addWord("else", keyword);
		keywordRule.addWord("for", keyword);
		keywordRule.addWord("in", keyword);
		keywordRule.addWord("while", keyword);
		keywordRule.addWord("do", keyword);
		keywordRule.addWord("assert", keyword);
		keywordRule.addWord("return", keyword);
		keywordRule.addWord("yield", keyword);
		keywordRule.addWord("global", keyword);
		keywordRule.addWord("super", keyword);
		keywordRule.addWord("this", keyword);
		keywordRule.addWord("parallel", keyword);
		keywordRule.addWord("dynamic", keyword);
		keywordRule.addWord("abstract", keyword);
		keywordRule.addWord("override", keyword);
		keywordRule.addWord("final", keyword);

		literalRule.addWord("nil", literal);
		literalRule.addWord("true", literal);
		literalRule.addWord("false", literal);
		
		/* exclude single uppercase letters from type rule, as these are
		 * often used for e.g. matrices */
		for (char c = 'A'; c <= 'Z'; ++c) {
			typeRule.addWord(Character.toString(c), name);
		}
		for (char c = 'Α'; c <= 'Ω'; ++c) {
			typeRule.addWord(Character.toString(c), name);
		}
		
		setRules(new IRule[] {
				new WhitespaceRule(new IWhitespaceDetector() {
					public boolean isWhitespace(char c) {
						return Character.isWhitespace(c);
					}
				}),
				new NumberRule(literal),
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
				new WordPatternRule(new OperatorWordDetector(), "\\", "", weakOperator),
				new WordPatternRule(new OperatorWordDetector(), "@", "", weakOperator),
				new MultiLineRule("/**", "*/", doubleComment),
				new MultiLineRule("/*", "*/", multiLineComment),
				new EndOfLineRule("//", singleLineComment),
				new MultiLineRule("{{", "}}", raw),
				new SingleLineRule("\"", "\"", string, '\\'),
				});
	}
}
