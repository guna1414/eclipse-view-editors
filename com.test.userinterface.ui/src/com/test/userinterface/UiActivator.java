package com.test.userinterface;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class UiActivator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.test.userinterface.ui"; //$NON-NLS-1$

	// The shared instance
	private static UiActivator plugin;

	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	public static UiActivator getDefault() {
		return plugin;
	}

	public Image getImage(String imageKey) {
		return getImageRegistry().get(imageKey);
	}

	protected void initializeImageRegistry(ImageRegistry reg) {
		putImageRegistry(reg, "form_banner.gif");
	}

	private void putImageRegistry(ImageRegistry registry, String relativePath) {
		registry.put(relativePath, getImageDescriptor("icons/" + relativePath));
	}

	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

}
