package birch.editors;

import org.eclipse.ui.editors.text.TextEditor;

public class BirchEditor extends TextEditor {

	private ColorManager colorManager;

	public BirchEditor() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new BirchConfiguration(colorManager));
		setDocumentProvider(new BirchDocumentProvider());
	}
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}

}
