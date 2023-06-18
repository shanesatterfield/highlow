package com.highlow.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.freetype.gwt.FreetypeInjector;
import com.badlogic.gdx.graphics.g2d.freetype.gwt.inject.OnCompletion;
import com.highlow.HighLowApplication;

public class HtmlLauncher extends GwtApplication {

    @Override
    public GwtApplicationConfiguration getConfig() {
        // Resizable application, uses available space in browser
        // return new GwtApplicationConfiguration(true);
        // Fixed size application:
        return new GwtApplicationConfiguration(640, 480);
    }

    @Override
    public ApplicationListener createApplicationListener() {
        return new HighLowApplication();
    }

    @Override
    public void onModuleLoad() {
        FreetypeInjector.inject(new OnCompletion() {
            public void run() {
                // Replace HtmlLauncher with the class name
                // If your class is called FooBar.java than the line should be FooBar.super.onModuleLoad();
                HtmlLauncher.super.onModuleLoad();
            }
        });
    }
}