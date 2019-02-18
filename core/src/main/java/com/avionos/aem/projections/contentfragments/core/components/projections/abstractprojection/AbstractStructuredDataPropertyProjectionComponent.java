package com.avionos.aem.projections.contentfragments.core.components.projections.abstractprojection;

import com.avionos.aem.projections.contentfragments.api.models.projections.ContentFragmentProjectionContext;

public abstract class AbstractStructuredDataPropertyProjectionComponent<T> extends AbstractPropertyProjectionComponent<T> {

    @Override
    public T getContent() {
        return getContext()
                .map(ContentFragmentProjectionContext::getContentFragment)
                .map(contentFragment -> contentFragment.getElement(getElementName()))
                .map(element -> element.getValue().getValue(getType()))
                .orElse(null);
    }

    public abstract String getElementName();

    public abstract Class<T> getType();

}
