// This is free and unencumbered software released into the public domain.
// Authors: christianniehaus, NotAlexNoyle.
package me.barny1094875.utilitiesog.internal;

// Declare a custom exception for the case where a WorldGuard Flag fails to register for internal use by Utilities-OG.
public class FlagRegistrationException extends Exception { 

	// This ID is used when the exception is serialized (converted to a byte stream), for example when it's sent over a network.
	private static final long serialVersionUID = 1L;

	// Constructor to handle sending an error message.
	public FlagRegistrationException(String message) {

		// Pass the basic message to the base Exception class.
		super(message);

	}

	// Constructor to handle sending an error message and a cause.
	public FlagRegistrationException(String message, Throwable cause) {

		// Pass the more informative message to the base Exception class.
		super(message, cause);

	}

}