/********************************************************************egg***m******a**************n************


 * 
 * File: BlogConfig.java
 * Course materials (19W) CST 8277
 * @author Ravanpreet Singh
 * @author Amanpreet Singh
 * 
 *
*/

package com.algonquincollege.cst8277.controller;

import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationScoped
@ApplicationPath("/rest")
public class BlogConfig extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new HashSet<>();
		resources.add(BlogResource.class);
		resources.add(UserResource.class);
		return resources;
	}
}
