package com.singleton.using.constructor.com;

import java.io.Serializable;

import com.sun.xml.internal.ws.encoding.soap.SerializationException;

/**
 * singleton using private constructor lazy init.
 */
public class SingletonUsingConstructorLazyInit implements Serializable, Cloneable {

	private static SingletonUsingConstructorLazyInit singletonUsingConstructor;

	private SingletonUsingConstructorLazyInit() {
		if (singletonUsingConstructor != null) {
			throw new InstantiationError("Silly boya!");
		}
	}

	public static SingletonUsingConstructorLazyInit getSingletonInstance() {
		if (singletonUsingConstructor == null) {
			singletonUsingConstructor = new SingletonUsingConstructorLazyInit();
		}
		return singletonUsingConstructor;
	}

	private Object readResolve() throws SerializationException {
		return singletonUsingConstructor;
	}

	// This is silly, you shouldn't need to implement cloneable for a singleton.
	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}
