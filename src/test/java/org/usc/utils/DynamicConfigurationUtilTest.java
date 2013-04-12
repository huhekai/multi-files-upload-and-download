package org.usc.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;
import org.usc.constant.Constants;

/**
 *
 * @author Shunli
 */
public class DynamicConfigurationUtilTest {
	@Test
	public void testGetProperty() {
		assertEquals("upload/file", UploadConfigurationUtil.getProperty(Constants.UPLOAD_FILE_PATH));
	}

	@Ignore
	@Test
	public void testPerformance() {
		int count = 10000000;
		int i = 0;
		long start = System.currentTimeMillis();
		while (i++ < count) {
			UploadConfigurationUtil.getProperty(Constants.UPLOAD_FILE_PATH);
		}
		System.out.println("1 " + (System.currentTimeMillis() - start));

		start = System.currentTimeMillis();
		i = 0;

		while (i++ < count) {
			UploadConfigurationUtil.getConfig().getString(Constants.UPLOAD_FILE_PATH);
		}
		System.out.println("2 " + (System.currentTimeMillis() - start));
	}
}
