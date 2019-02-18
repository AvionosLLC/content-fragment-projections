package com.avionos.aem.projections.contentfragments.core.components.projections.title;

import com.avionos.aem.projections.contentfragments.api.components.projections.property.PropertyProjection;
import com.avionos.aem.projections.contentfragments.core.components.projections.abstractprojection.AbstractStructuredDataStringPropertyValueProjectionComponent;
import com.citytechinc.cq.component.annotations.Component;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;

@Component(value = "Structured Title Projection", name = "structuredtitleprojection")
@Model(adaptables = SlingHttpServletRequest.class, adapters = PropertyProjection.class, resourceType = StructuredTitleProjection.RESOURCE_TYPE)
public class StructuredTitleProjection extends AbstractStructuredDataStringPropertyValueProjectionComponent {

    public static final String RESOURCE_TYPE = "content-fragment-projections/components/projections/structuredtitleprojection";

    @Override
    public String getElementName() {
        return "title";
    }

}
