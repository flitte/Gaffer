/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package gaffer.gafferpop;

import static org.junit.Assert.assertEquals;

import gaffer.commonutil.StreamUtil;
import gaffer.graph.Graph;
import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.tinkerpop.gremlin.structure.T;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.util.iterator.IteratorUtils;
import org.junit.Test;
import java.util.Arrays;

public class GafferPopGraphTest {
    private static final Configuration TEST_CONFIGURATION = new BaseConfiguration() {{
        this.setProperty(GafferPopGraph.GRAPH, GafferPopGraph.class.getName());
    }};
    public static final int VERTEX_1 = 1;
    public static final int VERTEX_2 = 2;

    @Test
    public void shouldAddAndGetVertex() {
        // Given
        final Graph gafferGraph = getGafferGraph();
        final GafferPopGraph graph = GafferPopGraph.open(TEST_CONFIGURATION, gafferGraph);

        // When
        graph.addVertex(T.label, "software", T.id, VERTEX_1, "name", "GafferPop");

        // Then - there is 1 vertex and no edges
        assertEquals(1, IteratorUtils.count(graph.vertices(Arrays.asList(VERTEX_1, VERTEX_2), "software")));
        assertEquals(0, IteratorUtils.count(graph.edges(new EdgeId(VERTEX_1, VERTEX_2))));
    }

    @Test
    public void shouldAddAndGetEdge() {
        // Given
        final Graph gafferGraph = getGafferGraph();
        final GafferPopGraph graph = GafferPopGraph.open(TEST_CONFIGURATION, gafferGraph);

        // When
        final Vertex gafferPop = graph.addVertex(T.label, "software", T.id, VERTEX_1, "name", "GafferPop");
        final Vertex gaffer = graph.addVertex(T.label, "software", T.id, VERTEX_2, "name", "Gaffer");
        gafferPop.addEdge("dependsOn", gaffer);

        // Then - there are two software vertices and one edge
        assertEquals(2, IteratorUtils.count(graph.vertices(Arrays.asList(VERTEX_1, VERTEX_2), "software")));
        assertEquals(1, IteratorUtils.count(graph.edges(new EdgeId(VERTEX_1, VERTEX_2))));
    }

    private Graph getGafferGraph() {
        return new Graph.Builder()
                .storeProperties(StreamUtil.openStream(this.getClass(), "/gaffer/store.properties", true))
                .addSchema(StreamUtil.openStream(this.getClass(), "/gaffer/schema/dataSchema.json", true))
                .addSchema(StreamUtil.openStream(this.getClass(), "/gaffer/schema/dataTypes.json", true))
                .addSchema(StreamUtil.openStream(this.getClass(), "/gaffer/schema/storeTypes.json", true))
                .build();
    }

}