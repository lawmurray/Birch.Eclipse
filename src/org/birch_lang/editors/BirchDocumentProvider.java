package org.birch_lang.editors;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.ui.editors.text.FileDocumentProvider;

public class BirchDocumentProvider extends FileDocumentProvider {

	protected IDocument createDocument(Object element) throws CoreException {
		IDocument document = super.createDocument(element);
		if (document != null) {
			IDocumentPartitioner partitioner =
				new FastPartitioner(
					new BirchPartitionScanner(),
					new String[] {
						BirchPartitionScanner.BIRCH_SINGLE_LINE_COMMENT,
						BirchPartitionScanner.BIRCH_MULTI_LINE_COMMENT,
						BirchPartitionScanner.BIRCH_DOUBLE_COMMENT,
						BirchPartitionScanner.BIRCH_RAW});
			partitioner.connect(document);
			document.setDocumentPartitioner(partitioner);
		}
		return document;
	}
}