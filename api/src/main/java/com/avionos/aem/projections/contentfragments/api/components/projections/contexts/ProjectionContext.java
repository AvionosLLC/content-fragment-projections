package com.avionos.aem.projections.contentfragments.api.components.projections.contexts;

import com.avionos.aem.projections.contentfragments.api.models.projections.ContentFragmentProjectionContext;

public interface ProjectionContext {

    String PROJECTED_CONTENT_FRAGMENT_PROPERTY = "basedOn";

    ContentFragmentProjectionContext getProjectionContext();

}
