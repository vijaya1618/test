package com.pennantExamination.webui.util;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zk.ui.util.ConventionWires;
import org.zkoss.zul.Window;

/**
 * Base controller for creating the controllers of the zul files with the <b>use</b> tag.
 */
public abstract class WindowBaseCtrl extends Window implements AfterCompose, Serializable {
	private static final long				serialVersionUID	= -2179229704315045689L;

	protected transient Map<String, Object>	args;

	public WindowBaseCtrl() {
		super();
		this.setStyle("body {padding 0 0;}");
	}

	public void afterCompose() {
		processRecursive(this, this);
	}

	/**
	 * Go recursive through all founded components and wires all vars and added all forwarders for ALL window
	 * components. <br>
	 * 
	 * @param main
	 * @param child
	 */
	private void processRecursive(Window main, Window child) {
		ConventionWires.wireVariables(main, child);
		ConventionWires.addForwards(main, this);
		List<Component> winList = child.getChildren();
		for (Component window : winList) {
			if (window instanceof Window) {
				processRecursive(main, (Window) window);
			}
		}
	}
}
