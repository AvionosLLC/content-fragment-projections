package com.avionos.aem.projections.contentfragments.core.components.projections.contexts.parsys;

import com.avionos.aem.projections.contentfragments.api.components.projections.contexts.ProjectionContext;
import com.avionos.aem.projections.contentfragments.api.components.projections.contexts.parsys.ParsysProjectionContext;
import com.avionos.aem.projections.contentfragments.api.models.projections.ContentFragmentProjectionContext;
import com.avionos.aem.projections.contentfragments.api.models.projections.ContentFragmentProjectionContexts;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.component.annotations.editconfig.DropTarget;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;

@Component(value = "Paragraph System Projection Context", name = "parsysprojectioncontext", isContainer = true, dropTargets = @DropTarget(
        accept = { "text/html", "text/plain", "text/x-markdown", "application/vnd.adobe.contentfragment" },
        groups = "media",
        propertyName = ProjectionContext.PROJECTED_CONTENT_FRAGMENT_PROPERTY,
        nodeName = "fragment"
), listeners = {
        @Listener(name = "afterinsert", value = "function (properties) { $(document).trigger('cq-contentfragment-insert'); }"),
        @Listener(name = "afterdelete", value = "function (properties) { $(document).trigger('cq-contentfragment-delete'); }"),
        @Listener(name = "afteredit", value = "function (properties) { $(document).trigger('cq-contentfragment-edit'); }")
})
@Model(adaptables = Resource.class, adapters = {ParsysProjectionContext.class, ProjectionContext.class}, resourceType = DefaultParsysProjectionContext.RESOURCE_TYPE)
public class DefaultParsysProjectionContext implements ParsysProjectionContext {

    public static final String RESOURCE_TYPE = "content-fragment-projections/components/projections/parsysprojectioncontext";

    @Inject @Self
    private Resource resource;

    @DialogField(fieldLabel = "Projected Content Fragment", name = "./" + ProjectionContext.PROJECTED_CONTENT_FRAGMENT_PROPERTY) @PathField(rootPath = "/content/dam")
    @Override
    public ContentFragmentProjectionContext getProjectionContext() {
        ContentFragmentProjectionContexts contexts = resource.adaptTo(ContentFragmentProjectionContexts.class);

        if (contexts != null && !contexts.getProjectionContexts().isEmpty()) {
            return contexts.getProjectionContexts().get(0);
        }

        return null;
    }

    public String getContentFragmentPath() {
        ContentFragmentProjectionContext context = getProjectionContext();

        if (context != null) {
            return context.getContentFragment().adaptTo(Resource.class).getPath();
        }

        return null;
    }

}
