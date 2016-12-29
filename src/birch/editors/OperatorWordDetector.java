package birch.editors;

import org.eclipse.jface.text.rules.IWordDetector;

public class OperatorWordDetector implements IWordDetector {
	public boolean isWordStart(char c) {
		return c == ',' || c == ';' || c == ':' || c == '(' || c == ')' ||
				c == '[' || c == ']' || c == '{' || c == '}';
	}

	public boolean isWordPart(char c) {
		return isWordStart(c);
	}
}
