package com.test.userinterface.views;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.internal.util.BundleUtility;

/**
 * @author hcl186(Gunasekar.A)
 *
 */
public class ImageShare {

	private static Map descriptors = new HashMap();

	private static ImageRegistry imageRegistry = null;

	private final static String ICONS_PATH = "icons/";

	private final static String PLUGIN_ID = "com.test.userinterface.ui";

	public static String REFRESH_ICON = "refresh.gif";

	public static String CLASS_ICON = "class_obj.gif";

	public static String CLASS_ICON_UNREG = "class_obj_unreg.GIF";

	public static String ANNOTATION_ICON = "annotation_obj.gif";

	public static String MIN_ICON= "min_view.gif";

	public static String BINDING_ICON="debugt_obj.gif";

	public static String INTERNAL_OBJ_ICON="targetinternal_obj.gif";

	public static String PUBLIC_OBJ_ICON="targetpublic_obj.gif";

	public static String USER_FIELD = "userfield.GIF";

	public static String SAMPLE_IMAGE = "sample.gif";

	private ImageShare() {
	}

	static {
		initializeImageRegistry();
	}

	public static Image getImage(String key) {
		Image image = imageRegistry.get(key);
		if(image == null) {
			declareImage(key, ICONS_PATH + key, true);
			image = imageRegistry.get(key);
		}
		return image;
	}

	public static ImageDescriptor getImageDescriptor(String key) {
		ImageDescriptor descriptor = (ImageDescriptor) descriptors.get(key);
		if(descriptor == null) {
			declareImage(key, ICONS_PATH + key, true);
			descriptor = (ImageDescriptor) descriptors.get(key);
		}

		return descriptor;
	}

	private final static void declareImage(String key, String path, boolean shared) {
		URL url = BundleUtility.find(PLUGIN_ID, path);
		ImageDescriptor descriptor = ImageDescriptor.createFromURL(url);

		descriptors.put(key, descriptor);
		if(shared) {
			imageRegistry.put(key, descriptor);
		}
	}

	private static ImageRegistry initializeImageRegistry() {
		imageRegistry = new ImageRegistry();
		return imageRegistry;
	}
}