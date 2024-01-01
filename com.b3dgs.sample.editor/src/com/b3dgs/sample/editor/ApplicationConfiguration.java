/*
 * Copyright (C) 2013-2024 Byron 3D Games Studio (www.b3dgs.com) Pierre-Alexandre (contact@b3dgs.com)
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package com.b3dgs.sample.editor;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.core.runtime.Platform;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.b3dgs.lionengine.editor.dialog.project.ProjectImportHandler;
import com.b3dgs.lionengine.editor.project.Project;
import com.b3dgs.lionengine.editor.project.ProjectFactory;

/**
 * Configure the editor with the right name.
 */
public class ApplicationConfiguration
{
    /** Plugin name. */
    public static final String PLUGIN_NAME = "Sample Editor";
    /** Import project argument. */
    private static final String ARG_IMPORT = "-import";
    /** Logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfiguration.class);

    /** Application reference. */
    private final MApplication application;

    /**
     * Constructor.
     * 
     * @param application The application reference.
     */
    @Inject
    public ApplicationConfiguration(MApplication application)
    {
        super();

        this.application = application;
    }

    /**
     * Execute the injection.
     * 
     * @param eventBroker The event broker service.
     */
    @PostConstruct
    public void execute(IEventBroker eventBroker)
    {
        final MWindow existingWindow = application.getChildren().get(0);
        existingWindow.setLabel(PLUGIN_NAME);
        eventBroker.subscribe(UIEvents.UILifeCycle.APP_STARTUP_COMPLETE, new AppStartupCompleteEventHandler());
    }

    /**
     * Handler called on startup complete.
     */
    private static final class AppStartupCompleteEventHandler implements EventHandler
    {
        /**
         * Import a project from a path.
         * 
         * @param projectPath The project path.
         */
        private static void importProject(String projectPath)
        {
            final File path = new File(projectPath);
            try
            {
                final Project project = ProjectFactory.create(path.getCanonicalFile());
                ProjectImportHandler.importProject(project);
            }
            catch (final IOException exception)
            {
                LOGGER.error("importProject error", exception);
            }
        }

        /**
         * Check if there is a project to import.
         */
        private static void checkProjectImport()
        {
            final String[] args = Platform.getApplicationArgs();
            for (int i = 0; i < args.length; i++)
            {
                if (ARG_IMPORT.equals(args[i]))
                {
                    i++; // CHECKSTYLE IGNORE LINE: TrailingComment|ModifiedControlVariable
                    if (i < args.length)
                    {
                        importProject(args[i]);
                    }
                }
            }
        }

        /**
         * Constructor.
         */
        AppStartupCompleteEventHandler()
        {
            super();
        }

        @Override
        public void handleEvent(Event event)
        {
            checkProjectImport();
        }
    }
}
