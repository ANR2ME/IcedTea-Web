// Copyright (C) 2001-2003 Jon A. Maxwell (JAM)
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.

package net.sourceforge.jnlp;

import net.sourceforge.jnlp.runtime.ApplicationInstance;

/**
 * This optional interface is used to handle conditions that occur
 * while launching JNLP applications, applets, and installers.
 *
 * @author <a href="mailto:jmaxwell@users.sourceforge.net">Jon A. Maxwell (JAM)</a> - initial author
 * @version $Revision: 1.9 $
 */
public interface LaunchHandler {

    /**
     * Called when the application could not be launched due to a
     * fatal error, such as the inability to find the main class or
     * non-parseable XML.
     * @param exception to be re-thrown as launch error
     */
    void handleLaunchError(LaunchException exception);

    /**
     * Called when launching the application can not be launched due
     * to an error that is not fatal.  For example a JNLP file that
     * is not strictly correct yet does not necessarily prohibit the
     * system from attempting to launch the application.
     *
     * @param warning exception to be consumed as warning
     */
    void handleLaunchWarning(LaunchException warning);

    /**
     * Called when a security validation error occurs while
     * launching the application.
     *
     * @param error to be rethrown as launch error
     */
    void validationError(LaunchException error);

    // this method will probably be replaced when real security
    // controller is in place.

    /**
     * Called when an application, applet or installer has been determined.
     * We have some very basic information about the application at this point,
     * but do not have everything required. This is a nice point to show the
     * splash screen.
     *
     * @param file the JNLP file of the instance that is starting
     */
    void launchInitialized(JNLPFile file);

    /**
     * Called when an application, applet or installer is ready to start.
     * Good point to hide the splash screen.
     *
     * @param application the application instance that is ready
     */
    void launchStarting(ApplicationInstance application);

    /**
     * Called when an application, applet, or installer has been
     * launched successfully (the main method or applet start method
     * returned normally).
     *
     * @param application the launched application instance
     */
    void launchCompleted(ApplicationInstance application);

}
