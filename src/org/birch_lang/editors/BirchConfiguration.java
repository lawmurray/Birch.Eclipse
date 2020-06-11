package org.birch_lang.editors;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

public class BirchConfiguration extends SourceViewerConfiguration {
	private BirchDoubleClickStrategy doubleClickStrategy;
	private BirchScanner scanner;
	private ColorManager colorManager;

	public BirchConfiguration() {
		this.colorManager = new ColorManager();
	}

	public int getTabWidth​(ISourceViewer sourceViewer) {
		return 2;
	}
	
	public String[] getDefaultPrefixes​(ISourceViewer sourceViewer, String contentType) {
		return new String[] { "  " };
	}
	
	protected String[] getIndentPrefixesForTab​(int tabWidth) {
		String tab = "";
		for (int i = 0; i < tabWidth; ++i) {
			tab += "  ";
		}
		return new String[] { tab };
	}

	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return new String[] {
			IDocument.DEFAULT_CONTENT_TYPE,
			BirchPartitionScanner.BIRCH_SINGLE_LINE_COMMENT,
			BirchPartitionScanner.BIRCH_MULTI_LINE_COMMENT,
			BirchPartitionScanner.BIRCH_DOUBLE_COMMENT,
			BirchPartitionScanner.BIRCH_RAW };
	}
	
	public ITextDoubleClickStrategy getDoubleClickStrategy(
		ISourceViewer sourceViewer,
		String contentType) {
		if (doubleClickStrategy == null)
			doubleClickStrategy = new BirchDoubleClickStrategy();
		return doubleClickStrategy;
	}

	protected BirchScanner getBirchScanner() {
		if (scanner == null) {
			scanner = new BirchScanner(colorManager);
			scanner.setDefaultReturnToken(new Token(new TextAttribute(null)));
		}
		return scanner;
	}

	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();

		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(getBirchScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

		NonRuleBasedDamagerRepairer ndr1 =
				new NonRuleBasedDamagerRepairer(
					new TextAttribute(colorManager.singleLineCommentColor));
			reconciler.setDamager(ndr1, BirchPartitionScanner.BIRCH_SINGLE_LINE_COMMENT);
			reconciler.setRepairer(ndr1, BirchPartitionScanner.BIRCH_SINGLE_LINE_COMMENT);

		NonRuleBasedDamagerRepairer ndr2 =
			new NonRuleBasedDamagerRepairer(
				new TextAttribute(colorManager.multiLineCommentColor));
		reconciler.setDamager(ndr2, BirchPartitionScanner.BIRCH_MULTI_LINE_COMMENT);
		reconciler.setRepairer(ndr2, BirchPartitionScanner.BIRCH_MULTI_LINE_COMMENT);

		NonRuleBasedDamagerRepairer ndr3 =
				new NonRuleBasedDamagerRepairer(
					new TextAttribute(colorManager.doubleCommentColor));
			reconciler.setDamager(ndr3, BirchPartitionScanner.BIRCH_DOUBLE_COMMENT);
			reconciler.setRepairer(ndr3, BirchPartitionScanner.BIRCH_DOUBLE_COMMENT);

		NonRuleBasedDamagerRepairer ndr4 =
				new NonRuleBasedDamagerRepairer(
					new TextAttribute(colorManager.rawColor));
			reconciler.setDamager(ndr4, BirchPartitionScanner.BIRCH_RAW);
			reconciler.setRepairer(ndr4, BirchPartitionScanner.BIRCH_RAW);

		return reconciler;
	}

}