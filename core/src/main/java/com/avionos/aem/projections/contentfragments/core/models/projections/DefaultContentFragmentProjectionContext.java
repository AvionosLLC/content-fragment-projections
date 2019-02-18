package com.avionos.aem.projections.contentfragments.core.models.projections;

import com.adobe.cq.dam.cfm.ContentFragment;
import com.avionos.aem.projections.contentfragments.api.components.projections.contexts.ProjectionContext;
import com.avionos.aem.projections.contentfragments.api.models.projections.ContentFragmentProjectionContext;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;
import java.util.Optional;

@Model(adaptables = Resource.class, adapters = ContentFragmentProjectionContext.class)
public class DefaultContentFragmentProjectionContext implements ContentFragmentProjectionContext {

    @Inject @Self
    private Resource resource;

    private ContentFragment contentFragment;

    @Override
    public Resource getContext() {
        return resource;
    }

    @Override
    public ContentFragment getContentFragment() {
        if (contentFragment != null) {
            return contentFragment;
        }

        contentFragment = Optional.ofNullable(resource.getValueMap().get(ProjectionContext.PROJECTED_CONTENT_FRAGMENT_PROPERTY, String.class))
                .map(contentFragmentReference -> resource.getResourceResolver().getResource(contentFragmentReference))
                .map(contentFragmentResource -> contentFragmentResource.adaptTo(ContentFragment.class))
                .orElse(null);

        return contentFragment;
    }

}
