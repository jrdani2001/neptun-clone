package com.example.neptunclone.model;

import com.example.neptunclone.view.ViewFactory;

public class ViewModel {
    private static ViewModel modell;
    private final ViewFactory viewFactory;

    private ViewModel() {
        this.viewFactory = new ViewFactory();
    }

    public static synchronized ViewModel getInstance() {
        if (modell == null) {
            modell = new ViewModel();
        }
        return modell;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }
}
