/*
 * DO NOT EDIT.  THIS FILE IS GENERATED FROM
 * /builds/slave/mozilla-1.9.2-linux-xulrunner/build/widget/public/nsIAppShell.idl
 */

package org.mozilla.interfaces;

/**
 * Interface for the native event system layer.  This interface is designed
 * to be used on the main application thread only.
 */
public interface nsIAppShell extends nsISupports {

  String NS_IAPPSHELL_IID =
    "{501403e9-a091-4780-ba55-cfd1e21287a1}";

  /**
   * Enter an event loop.  Don't leave until exit() is called.
   */
  void run();

  /**
   * Exit the handle event loop
   */
  void exit();

  /**
   * Give hint to native event queue notification mechanism. If the native
   * platform needs to tradeoff performance vs. native event starvation this
   * hint tells the native dispatch code which to favor.  The default is to
   * prevent native event starvation.
   *
   * Calls to this function may be nested. When the number of calls that pass
   * PR_TRUE is subtracted from the number of calls that pass PR_FALSE is
   * greater than 0, performance is given precedence over preventing event
   * starvation.
   *
   * The starvationDelay arg is only used when favorPerfOverStarvation is
   * PR_FALSE. It is the amount of time in milliseconds to wait before the
   * PR_FALSE actually takes effect.
   */
  void favorPerformanceHint(boolean favorPerfOverStarvation, long starvationDelay);

  /**
   * Suspends the use of additional platform-specific methods (besides the
   * nsIAppShell->run() event loop) to run Gecko events on the main
   * application thread.  Under some circumstances these "additional methods"
   * can cause Gecko event handlers to be re-entered, sometimes leading to
   * hangs and crashes.  Calls to suspendNative() and resumeNative() may be
   * nested.  On some platforms (those that don't use any "additional
   * methods") this will be a no-op.  Does not (in itself) stop Gecko events
   * from being processed on the main application thread.  But if the
   * nsIAppShell->run() event loop is blocked when this call is made, Gecko
   * events will stop being processed until resumeNative() is called (even
   * if a plugin or library is temporarily processing events on a nested
   * event loop).
   */
  void suspendNative();

  /**
   * Resumes the use of additional platform-specific methods to run Gecko
   * events on the main application thread.  Calls to suspendNative() and
   * resumeNative() may be nested.  On some platforms this will be a no-op.
   */
  void resumeNative();

  /**
   * The current event loop nesting level.
   */
  long getEventloopNestingLevel();

}