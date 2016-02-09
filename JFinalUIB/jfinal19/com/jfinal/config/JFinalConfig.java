/**
 * Copyright (c) 2011-2015, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jfinal.config;

import java.io.File;
import java.util.Properties;
import com.jfinal.core.Const;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;

/**
 * JFinalConfig.
 * <p>
 * Config order: configConstant(), configRoute(), configPlugin(), configInterceptor(), configHandler()
 */
public abstract class JFinalConfig {
	
	/**
	 * Config constant
	 */
	public abstract void configConstant(Constants me);
	
	/**
	 * Config route
	 */
	public abstract void configRoute(Routes me);
	
	/**
	 * Config plugin
	 */
	public abstract void configPlugin(Plugins me);
	
	/**
	 * Config interceptor applied to all actions.
	 */
	public abstract void configInterceptor(Interceptors me);
	
	/**
	 * Config handler
	 */
	public abstract void configHandler(Handlers me);
	
	/**
	 * Call back after JFinal start
	 */
	public void afterJFinalStart(){};
	
	/**
	 * Call back before JFinal stop
	 */
	public void beforeJFinalStop(){};
	
	protected Prop prop = null;
	
	/**
	 * 加载properties使用默认编码并保存在prop
	 * Load property file.
	 * @see #loadPropertyFile(String, String)
	 */
	public Properties loadPropertyFile(String fileName) {
		return loadPropertyFile(fileName, Const.DEFAULT_ENCODING);
	}
	
	/**
	 * 加载properties并保存在prop
	 * Load property file.
	 * Example:<br>
	 * loadPropertyFile("db_username_pass.txt", "UTF-8");
	 * 
	 * @param fileName the file in CLASSPATH or the sub directory of the CLASSPATH
	 * @param encoding the encoding
	 */
	public Properties loadPropertyFile(String fileName, String encoding) {
		prop = PropKit.use(fileName, encoding);
		return prop.getProperties();
	}
	
	/**
	 * Load property file.
	 * @see #loadPropertyFile(File, String)
	 */
	public Properties loadPropertyFile(File file) {
		return loadPropertyFile(file, Const.DEFAULT_ENCODING);
	}
	
	/**
	 * Load property file
	 * Example:<br>
	 * loadPropertyFile(new File("/var/config/my_config.txt"), "UTF-8");
	 * 
	 * @param file the properties File object
	 * @param encoding the encoding
	 */
	public Properties loadPropertyFile(File file, String encoding) {
		prop = PropKit.use(file, encoding);
		return prop.getProperties();
	}
	
	public void unloadPropertyFile(String fileName) {
		Prop uselessProp = PropKit.useless(fileName);
		if (this.prop == uselessProp)
			this.prop = null;
	}
	
	public void unloadAllPropertyFiles() {
		PropKit.clear();
		prop = null;
	}
	
	private Prop getProp() {
		if (prop == null)
			throw new IllegalStateException("Load propties file by invoking loadPropertyFile(String fileName) method first.");
		return prop;
	}
	
	public String getProperty(String key) {
		return getProp().get(key);
	}
	
	public String getProperty(String key, String defaultValue) {
		return getProp().get(key, defaultValue);
	}
	
	public Integer getPropertyToInt(String key) {
		return getProp().getInt(key);
	}
	
	public Integer getPropertyToInt(String key, Integer defaultValue) {
		return getProp().getInt(key, defaultValue);
	}
	
	public Long getPropertyToLong(String key) {
		return getProp().getLong(key);
	}
	
	public Long getPropertyToLong(String key, Long defaultValue) {
		return getProp().getLong(key, defaultValue);
	}
	
	public Boolean getPropertyToBoolean(String key) {
		return getProp().getBoolean(key);
	}
	
	public Boolean getPropertyToBoolean(String key, Boolean defaultValue) {
		return getProp().getBoolean(key, defaultValue);
	}
}