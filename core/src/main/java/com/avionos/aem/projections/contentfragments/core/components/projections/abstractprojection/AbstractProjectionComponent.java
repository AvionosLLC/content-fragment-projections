package com.avionos.aem.projections.contentfragments.core.components.projections.abstractprojection;

import com.avionos.aem.projections.contentfragments.api.components.projections.projectioncomponent.ProjectionComponent;
import com.avionos.aem.projections.contentfragments.api.models.projections.ContentFragmentProjectionContext;
import com.avionos.aem.projections.contentfragments.api.models.projections.ContentFragmentProjectionContexts;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.NumberField;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;
import java.util.Optional;

public abstract class AbstractProjectionComponent implements ProjectionComponent {

    public static final String CONTEXT_PROJECTED = "contextProjected";

    private ContentFragmentProjectionContexts contexts;

    @Inject @org.apache.sling.models.annotations.Optional
    private Page currentPage;

    @Inject @Self @org.apache.sling.models.annotations.Optional
    private Resource resource;

    @Inject @Self @org.apache.sling.models.annotations.Optional
    private SlingHttpServletRequest request;

    //@DialogField(fieldLabel = "Context", name = "./" + CONTEXT_PROJECTED, fieldDescription = "Allows for context selection when you are within nested contexts.  Defaults to the closest context.")
    //@NumberField(allowDecimals = false, allowNegative = false)
    public Optional<ContentFragmentProjectionContext> getContext() {
        if (contexts == null) {
            contexts = getResource().adaptTo(ContentFragmentProjectionContexts.class);
        }

        if (contexts != null) {
            if (contexts.getProjectionContexts().isEmpty()) {
                return Optional.empty();
            }

            return Optional.of(contexts.getProjectionContexts().get(getResource().getValueMap().get(CONTEXT_PROJECTED, 0)));
        }

        return Optional.empty();
    }

    protected Resource getResource() {
        if (request == null) {
            return resource;
        }

        if (!request.getResource().getPath().startsWith("/conf")) {
            return request.getResource();
        }

        if (currentPage == null) {
            throw new RuntimeException("I have a Resource within /conf but no currentPage"); // TODO: Bespoke exception
        }

        /*
         * The following supports cases where projection components are included as baked in components
         * in an editable template.
         */
        Page dynamicTemplatePage = currentPage.getPageManager().getContainingPage(request.getResource());

        return request.getResourceResolver().resolve(currentPage.getPath() + request.getResource().getPath().substring(dynamicTemplatePage.getPath().length()));
    }

}
