package com.avionos.aem.projections.contentfragments.api.models.projections;

import com.adobe.cq.dam.cfm.ContentFragment;
import org.apache.sling.api.resource.Resource;

import java.util.Optional;

public interface ContentFragmentProjectionContext {

    Resource getContext();

    ContentFragment getContentFragment();

}
