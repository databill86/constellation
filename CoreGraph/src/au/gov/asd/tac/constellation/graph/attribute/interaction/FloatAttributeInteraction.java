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
package au.gov.asd.tac.constellation.graph.attribute.interaction;

import au.gov.asd.tac.constellation.graph.attribute.FloatAttributeDescription;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author twilight_sparkle
 */
@ServiceProvider(service = AbstractAttributeInteraction.class)
public class FloatAttributeInteraction extends AbstractAttributeInteraction<Float> {

    @Override
    public String getDataType() {
        return FloatAttributeDescription.ATTRIBUTE_NAME;
    }

    @Override
    public String getDisplayText(Object attrVal) {
        if (attrVal == null) {
            return null;
        }
        return attrVal.toString();
    }

    @Override
    protected Class<Float> getValueType() {
        return Float.class;
    }
}
