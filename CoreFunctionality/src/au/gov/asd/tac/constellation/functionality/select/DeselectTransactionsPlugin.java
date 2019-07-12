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
package au.gov.asd.tac.constellation.functionality.select;

import au.gov.asd.tac.constellation.graph.Graph;
import au.gov.asd.tac.constellation.graph.GraphElementType;
import au.gov.asd.tac.constellation.graph.GraphWriteMethods;
import au.gov.asd.tac.constellation.graph.visual.concept.VisualConcept;
import au.gov.asd.tac.constellation.pluginframework.Plugin;
import au.gov.asd.tac.constellation.pluginframework.PluginInteraction;
import au.gov.asd.tac.constellation.pluginframework.parameters.PluginParameters;
import au.gov.asd.tac.constellation.pluginframework.templates.SimpleEditPlugin;
import org.openide.util.NbBundle;
import org.openide.util.lookup.ServiceProvider;

/**
 * plugin framework supporting the de-select all transactions in the graph
 *
 * @author algol
 */
@ServiceProvider(service = Plugin.class)
@NbBundle.Messages("DeselectTransactionsPlugin=Remove from Selection: Transactions")
public class DeselectTransactionsPlugin extends SimpleEditPlugin {

    @Override
    protected void edit(final GraphWriteMethods wg, final PluginInteraction interaction, final PluginParameters parameters) throws InterruptedException {
        final int txSelectedAttrId = wg.getAttribute(GraphElementType.TRANSACTION, VisualConcept.TransactionAttribute.SELECTED.getName());
        if (txSelectedAttrId != Graph.NOT_FOUND) {
            final int txCount = wg.getTransactionCount();
            for (int position = 0; position < txCount; position++) {
                final int txId = wg.getTransaction(position);

                if (wg.getBooleanValue(txSelectedAttrId, txId)) {
                    wg.setBooleanValue(txSelectedAttrId, txId, false);
                }
            }
        }
    }
}
