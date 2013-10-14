/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * http://glassfish.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package org.glassfish.jersey.examples.velocity.resources;

import org.glassfish.jersey.server.mvc.Viewable;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.util.*;

/**
 * ExampleResource is our Controller for this MVC example.
 * <p/>
 * @author Paul K Moore (paulkmoore at gmail.com)
 * @since 2.3.2
 */
@Path("examples")
public class ExampleResource {

  @GET
  @Produces(MediaType.TEXT_HTML)
  public Viewable getIndex() {
    List<Link> links = new ArrayList<Link>();
    links.add(Link.fromUriBuilder(UriBuilder.fromResource(ExampleResource.class).path(ExampleResource.class, "getIncluded"))
            .type(MediaType.TEXT_HTML)
            .title("Introduction to #include and #parse")
            .build());
    links.add(Link.fromUriBuilder(UriBuilder.fromResource(ExampleResource.class).path(ExampleResource.class, "getModel"))
            .type(MediaType.TEXT_HTML)
            .title("Getting values from the model")
            .build());
    links.add(Link.fromUriBuilder(UriBuilder.fromResource(ExampleResource.class).path(ExampleResource.class, "getTool"))
            .type(MediaType.TEXT_HTML)
            .title("Usage of a custom tool (ExampleTool)")
            .build());
    return new Viewable("index", links);
  }

  @Path("include-and-parse")
  @GET
  @Produces(MediaType.TEXT_HTML)
  public Viewable getIncluded() {
    return new Viewable("include-and-parse");
  }

  @Path("model")
  @GET
  @Produces(MediaType.TEXT_HTML)
  public Viewable getModel() {
    Map<String, Object> data = new HashMap<String, Object>();
    data.put("name", "Paul Moore");
    data.put("email", "paulkmoore at gmail.com");

    List<String> list = new ArrayList<String>();
    list.add("List item 1");
    list.add("List item 2");
    list.add("List item 3");

    data.put("list", list);

    return new Viewable("model", data);
  }

  @Path("custom-tool")
  @GET
  @Produces(MediaType.TEXT_HTML)
  public Viewable getTool() {
    return new Viewable("custom-tool");
  }
}
