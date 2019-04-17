package com.avionos.aem.projections.contentfragments.api.components.projections.projectioncomponent;

import com.avionos.aem.projections.contentfragments.api.models.projections.ContentFragmentProjectionContext;

import java.util.Optional;

public interface ProjectionComponent {

    Optional<ContentFragmentProjectionContext> getContext();

}
