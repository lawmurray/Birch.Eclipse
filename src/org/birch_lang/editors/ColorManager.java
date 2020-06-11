package org.birch_lang.editors;

import org.eclipse.swt.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.Display;
import org.eclipse.jface.resource.*;
import org.eclipse.jface.util.*;
import org.eclipse.ui.themes.*;
import org.eclipse.ui.*;

public class ColorManager implements IPropertyChangeListener {
	public Color singleLineCommentColor;
	public Color multiLineCommentColor;
	public Color doubleCommentColor;
	public Color rawColor;
	public Color keywordColor;
	public Color typeColor;
	public Color literalColor;
	
  public ColorManager() {
		updateColors();
  }

	public void propertyChange(PropertyChangeEvent arg0) {
		updateColors();
	}
	
	private void updateColors() {
		Display display = Display.getCurrent();
		
		IThemeManager themeManager = PlatformUI.getWorkbench().getThemeManager();
		ITheme theme = themeManager.getCurrentTheme();
		
    themeManager.addPropertyChangeListener(this);
    theme.addPropertyChangeListener(this);
		
		ColorRegistry colorRegistry = theme.getColorRegistry();
		
		if (colorRegistry.hasValueFor("org.eclipse.cdt.ui.c_single_line_comment")) {
      singleLineCommentColor = colorRegistry.get("org.eclipse.cdt.ui.c_single_line_comment");
		} else {
      singleLineCommentColor = display.getSystemColor(SWT.COLOR_GREEN);
		}
		if (colorRegistry.hasValueFor("org.eclipse.cdt.ui.c_multi_line_comment")) {
      multiLineCommentColor = colorRegistry.get("org.eclipse.cdt.ui.c_multi_line_comment");
		} else {
			multiLineCommentColor = display.getSystemColor(SWT.COLOR_GREEN);
		}
		if (colorRegistry.hasValueFor("org.eclipse.cdt.internal.ui.text.doctools.doxygen.multi")) {
	    doubleCommentColor = colorRegistry.get("org.eclipse.cdt.internal.ui.text.doctools.doxygen.multi");
		} else {
			doubleCommentColor = display.getSystemColor(SWT.COLOR_GREEN);
		}
		if (colorRegistry.hasValueFor("org.eclipse.cdt.ui.c_keyword")) {
		  keywordColor = colorRegistry.get("org.eclipse.cdt.ui.c_keyword");
		} else {
			keywordColor = display.getSystemColor(SWT.COLOR_DARK_MAGENTA);
		}
		if (colorRegistry.hasValueFor("org.eclipse.cdt.ui.classHighlighting")) {
		  typeColor = colorRegistry.get("org.eclipse.cdt.ui.classHighlighting");
		} else {
			typeColor = display.getSystemColor(SWT.COLOR_DARK_GREEN);
		}
		if (colorRegistry.hasValueFor("org.eclipse.cdt.ui.c_string")) {
		  literalColor = colorRegistry.get("org.eclipse.cdt.ui.c_string");
		} else {
			literalColor = display.getSystemColor(SWT.COLOR_BLUE);
		}		
	  rawColor = display.getSystemColor(SWT.COLOR_DARK_GRAY);
	}
}
