package com.avionos.aem.projections.contentfragments.core.components.projections.title;

import com.avionos.aem.projections.contentfragments.api.components.projections.property.PropertyProjection;
import com.avionos.aem.projections.contentfragments.api.models.projections.ContentFragmentProjectionContexts;
import com.avionos.aem.projections.contentfragments.core.components.projections.abstractprojection.AbstractStringPropertyProjectionComponent;
import com.citytechinc.cq.component.annotations.Component;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;

@Component(value = "Title Projection", name = "titleprojection")
@Model(adaptables = Resource.class, adapters = PropertyProjection.class, resourceType = DefaultTitleProjection.RESOURCE_TYPE)
public class DefaultTitleProjection extends AbstractStringPropertyProjectionComponent {

    public static final String RESOURCE_TYPE = "content-fragment-projections/components/projections/titleprojection";

    @Inject @Self
    private ContentFragmentProjectionContexts contexts;

    @Override
    public String getContent() {
        return getContext().map(context -> context.getContentFragment().getTitle()).orElse(null);
    }

}
