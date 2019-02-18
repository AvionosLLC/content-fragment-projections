package com.avionos.aem.projections.contentfragments.core.models.projections;

import com.avionos.aem.projections.contentfragments.api.models.projections.ContentFragmentProjectionContext;
import com.avionos.aem.projections.contentfragments.api.models.projections.ContentFragmentProjectionContexts;
import com.google.common.collect.Lists;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Model(adaptables = Resource.class, adapters = ContentFragmentProjectionContexts.class)
public class DefaultContentFragmentProjectionContexts implements ContentFragmentProjectionContexts {

    @Inject @Self
    private Resource resource;

    private List<ContentFragmentProjectionContext> contexts;

    @Override
    public List<ContentFragmentProjectionContext> getProjectionContexts() {
        if (contexts != null) {
            return contexts;
        }

        contexts = Lists.newArrayList();

        Resource currentResource = resource;

        while (currentResource != null && !currentResource.getValueMap().get("jcr:primaryType", "nt:unstructured").equals("cq:Page")) {
            Optional.ofNullable(currentResource.adaptTo(ContentFragmentProjectionContext.class))
                    .filter(contentFragmentProjectionContext -> contentFragmentProjectionContext.getContentFragment() != null)
                    .ifPresent(contentFragmentProjectionContext -> contexts.add(contentFragmentProjectionContext));

            /*
            Optional.ofNullable(currentResource.getValueMap().get(ProjectionContext.PROJECTED_CONTENT_FRAGMENT_PROPERTY, String.class))
                    .map(projectedContentFragmentPath -> theCurrentResource.getResourceResolver().getResource(projectedContentFragmentPath))
                    .map(projectedContentFragmentResource -> projectedContentFragmentResource.adaptTo(ContentFragmentProjectionContext.class))
                    .filter(contentFragmentProjectionContext -> contentFragmentProjectionContext.getContentFragment() != null)
                    .ifPresent(contentFragmentProjectionContext -> contexts.add(contentFragmentProjectionContext));
                    */

            currentResource = currentResource.getParent();
        }

        return contexts;
    }
}
