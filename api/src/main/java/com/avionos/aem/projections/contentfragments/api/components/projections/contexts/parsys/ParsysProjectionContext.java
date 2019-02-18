package com.avionos.aem.projections.contentfragments.api.components.projections.contexts.parsys;

import com.avionos.aem.projections.contentfragments.api.components.projections.contexts.ProjectionContext;

public interface ParsysProjectionContext extends ProjectionContext {

    default String getParsysType() {
        return "wcm/foundation/components/parsys";
    }

}
