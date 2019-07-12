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
package au.gov.asd.tac.constellation.graph.visual.linestyle;

import au.gov.asd.tac.constellation.graph.attribute.AbstractObjectAttributeDescription;
import au.gov.asd.tac.constellation.graph.attribute.AttributeDescription;
import au.gov.asd.tac.constellation.visual.linestyle.LineStyle;
import org.openide.util.lookup.ServiceProvider;

/**
 * This describes a type of attribute whose values are {@link LineStyle}
 * constants.
 * <p>
 *
 * When setting these attribute values from numeric types, the value is the
 * {@link LineStyle} constant located at the index resulting from casting the
 * number to an int. The {@link #setString setString()} method will utilise
 * {@link LineStyle#valueOf}.
 * <p>
 * When retrieving these attribute values as numeric types the values the
 * {@link LineStyle#ordinal()} is yielded. The {@link #getString getString()}
 * method will utilise {@link String#valueOf}.
 *
 * @author sirius
 */
@ServiceProvider(service = AttributeDescription.class)
public final class LineStyleAttributeDescription extends AbstractObjectAttributeDescription<LineStyle> {

    public static final String ATTRIBUTE_NAME = "line_style";
    public static final Class<LineStyle> NATIVE_CLASS = LineStyle.class;
    public static final LineStyle DEFAULT_VALUE = LineStyle.SOLID;

    public LineStyleAttributeDescription() {
        super(ATTRIBUTE_NAME, NATIVE_CLASS, DEFAULT_VALUE);
    }

    @Override
    @SuppressWarnings("unchecked") // Casts are checked manually
    protected LineStyle convertFromObject(Object object) {
        if (object == null) {
            return DEFAULT_VALUE;
        } else if (object instanceof LineStyle) {
            return (LineStyle) object;
        } else if (object instanceof String) {
            return convertFromString((String) object);
        } else {
            throw new IllegalArgumentException(String.format("Error converting '%s' to RawValue", object.getClass()));
        }
    }

    @Override
    protected LineStyle convertFromString(final String value) {
        return LineStyle.valueOf(value);
    }

    @Override
    public void setInt(final int id, final int value) {
        data[id] = LineStyle.values()[value];
    }
}
