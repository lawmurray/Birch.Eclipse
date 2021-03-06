package org.birch_lang.editors;

import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.jface.text.*;

public class BirchEditor extends TextEditor {

	protected void initializeEditor() {
		super.initializeEditor();
		setSourceViewerConfiguration(new BirchConfiguration());
		setDocumentProvider(new BirchDocumentProvider());
		installTabsToSpacesConverter();
	}

	protected void installTabsToSpacesConverter() {
		TabsToSpacesConverter tabToSpacesConverter = new TabsToSpacesConverter();
		tabToSpacesConverter.setNumberOfSpacesPerTab(2);
		//tabToSpacesConverter.setDeleteSpacesAsTab(true);
		tabToSpacesConverter.setLineTracker(new DefaultLineTracker());

		ITextViewerExtension7 sv = (ITextViewerExtension7)getSourceViewer();
		if (sv != null) {
      sv.setTabsToSpacesConverter(tabToSpacesConverter);
  		updateIndentPrefixes();
  		updatePropertyDependentActions();
		}
	}
	
	protected boolean isTabsToSpacesConversionEnabled() {
		return true;
	}

	public void dispose() {
		super.dispose();
	}
}
