package org.birch_lang.editors;

import org.eclipse.jface.text.rules.*;

public class BirchPartitionScanner extends RuleBasedPartitionScanner {
	public final static String BIRCH_DOUBLE_COMMENT = "__birch_double_comment";
	public final static String BIRCH_COMMENT = "__birch_comment";
	//public final static String BIRCH_BLOCK = "__birch_block";
	public final static String BIRCH_RAW = "__birch_raw";

	public BirchPartitionScanner() {
		IToken birchDoubleComment = new Token(BIRCH_DOUBLE_COMMENT);
		IToken birchComment = new Token(BIRCH_COMMENT);
		//IToken birchBlock = new Token(BIRCH_BLOCK);
		IToken birchRaw = new Token(BIRCH_RAW);

		setPredicateRules(new IPredicateRule[] {
				new MultiLineRule("/**", "*/", birchDoubleComment),
				new MultiLineRule("/*", "*/", birchComment),
				new EndOfLineRule("//", birchComment),
				//new MultiLineRule("{", "}", birchBlock),
                new MultiLineRule("{{", "}}", birchRaw) });
	}
}
