package com.rotabuilder.webapp.footer;

import javax.inject.Singleton;

import org.apache.isis.viewer.wicket.ui.app.registry.ComponentFactoryRegistrar;
import org.apache.isis.viewer.wicket.viewer.registries.components.ComponentFactoryRegistrarDefault;

@Singleton
public class MyComponentFactoryRegistrar extends ComponentFactoryRegistrarDefault {

    @Override
    public void addComponentFactories(ComponentFactoryRegistrar.ComponentFactoryList componentFactories) {
        super.addComponentFactories(componentFactories);
        componentFactories.replace(new CustomFooterPanelFactory());
    }
}


