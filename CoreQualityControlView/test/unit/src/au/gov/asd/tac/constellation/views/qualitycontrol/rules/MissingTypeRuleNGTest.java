/*
 * Copyright 2010-2019 Australian Signals Directorate
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package au.gov.asd.tac.constellation.views.qualitycontrol.rules;

import au.gov.asd.tac.constellation.graph.StoreGraph;
import au.gov.asd.tac.constellation.graph.schema.SchemaFactoryUtilities;
import au.gov.asd.tac.constellation.schema.analyticschema.AnalyticSchemaFactory;
import au.gov.asd.tac.constellation.schema.analyticschema.concept.AnalyticConcept;
import au.gov.asd.tac.constellation.schema.visualschema.VisualSchemaFactory;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Missing Type Rule Test.
 *
 * @author arcturus
 */
public class MissingTypeRuleNGTest {

    public MissingTypeRuleNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

//    /**
//     * Test of getName method, of class MissingTypeRule.
//     */
//    @Test
//    public void testGetName() {
//        System.out.println("getName");
//        MissingTypeRule instance = new MissingTypeRule();
//        String expResult = "";
//        String result = instance.getName();
//        assertEquals(result, expResult);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getDescription method, of class MissingTypeRule.
//     */
//    @Test
//    public void testGetDescription() {
//        System.out.println("getDescription");
//        MissingTypeRule instance = new MissingTypeRule();
//        String expResult = "";
//        String result = instance.getDescription();
//        assertEquals(result, expResult);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getQuality method, of class MissingTypeRule.
//     */
//    @Test
//    public void testGetQuality() {
//        System.out.println("getQuality");
//        int vertex = 0;
//        MissingTypeRule instance = new MissingTypeRule();
//        int expResult = 0;
//        int result = instance.getQuality(vertex);
//        assertEquals(result, expResult);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    /**
     * Test of executeRule method, of class MissingTypeRule.
     *
     * @throws java.lang.InterruptedException
     */
    @Test
    public void testExecuteRule() throws InterruptedException {
        final StoreGraph graph = new StoreGraph(SchemaFactoryUtilities.getSchemaFactory(VisualSchemaFactory.VISUAL_SCHEMA_ID).createSchema());
        graph.addVertex();

        final int vertex = 0;
        final MissingTypeRule instance = new MissingTypeRule();
        final boolean expResult = true;
        final boolean result = instance.executeRule(graph, vertex);
        assertEquals(result, expResult);
    }

    /**
     * Test of executeRule method, of class MissingTypeRule.
     *
     * @throws java.lang.InterruptedException
     */
    @Test
    public void testExecuteRuleWithType() throws InterruptedException {
        final StoreGraph graph = new StoreGraph(SchemaFactoryUtilities.getSchemaFactory(AnalyticSchemaFactory.ANALYTIC_SCHEMA_ID).createSchema());

        final int typeAttr = AnalyticConcept.VertexAttribute.TYPE.ensure(graph);

        final int vx0 = graph.addVertex();
        graph.setObjectValue(typeAttr, vx0, AnalyticConcept.VertexType.COUNTRY);

        final int vertex = 0;
        final MissingTypeRule instance = new MissingTypeRule();
        final boolean expResult = false;
        final boolean result = instance.executeRule(graph, vertex);
        assertEquals(result, expResult);
    }
}
