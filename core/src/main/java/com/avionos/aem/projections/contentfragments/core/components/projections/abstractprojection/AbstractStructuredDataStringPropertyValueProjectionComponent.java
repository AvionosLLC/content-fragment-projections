package com.avionos.aem.projections.contentfragments.core.components.projections.abstractprojection;

public abstract class AbstractStructuredDataStringPropertyValueProjectionComponent extends AbstractStructuredDataPropertyProjectionComponent<String> {

    @Override
    public Class<String> getType() {
        return String.class;
    }

}
