package org.birch_lang.editors;

import org.eclipse.jface.text.rules.*;

public class BirchPartitionScanner extends RuleBasedPartitionScanner {
	public final static String BIRCH_DOUBLE_COMMENT = "__birch_double_comment";
	public final static String BIRCH_MULTI_LINE_COMMENT = "__birch_multi_line_comment";
	public final static String BIRCH_SINGLE_LINE_COMMENT = "__birch_single_line_comment";
	public final static String BIRCH_RAW = "__birch_raw";

	public BirchPartitionScanner() {
		IToken birchDoubleComment = new Token(BIRCH_DOUBLE_COMMENT);
		IToken birchMultiLineComment = new Token(BIRCH_MULTI_LINE_COMMENT);
		IToken birchSingleLineComment = new Token(BIRCH_SINGLE_LINE_COMMENT);
		IToken birchRaw = new Token(BIRCH_RAW);

		setPredicateRules(new IPredicateRule[] {
				new MultiLineRule("/**", "*/", birchDoubleComment),
				new MultiLineRule("/*", "*/", birchMultiLineComment),
				new EndOfLineRule("//", birchSingleLineComment),
        new MultiLineRule("{{", "}}", birchRaw) });
	}
}
