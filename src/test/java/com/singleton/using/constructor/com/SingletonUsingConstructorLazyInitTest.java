package com.singleton.using.constructor.com;


import static org.junit.Assert.assertSame;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class SingletonUsingConstructorLazyInitTest {

	@Test
	public void testSingleton() {
		SingletonUsingConstructorLazyInit
				singletonUsingConstructor = SingletonUsingConstructorLazyInit.getSingletonInstance();
		SingletonUsingConstructorLazyInit
				secondSingletonUsingConstructor = SingletonUsingConstructorLazyInit.getSingletonInstance();

		assertSame(singletonUsingConstructor, secondSingletonUsingConstructor);
	}

	@Test(expected = InvocationTargetException.class)
	public void testSingletonReflection()
			throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException,
				   InstantiationException {
		SingletonUsingConstructorLazyInit
				singletonUsingConstructor = SingletonUsingConstructorLazyInit.getSingletonInstance();
		Class<?> aClass = Class.forName("com.singleton.using.constructor.com.SingletonUsingConstructorLazyInit");
		Constructor<?> constructor = aClass.getDeclaredConstructor();
		constructor.setAccessible(true);

		SingletonUsingConstructorLazyInit secondSingletonUsingConstructor =
				(SingletonUsingConstructorLazyInit) constructor.newInstance();
		throw new RuntimeException("Shouldn't reach this point as reflection shouldn't create second instance");
	}

	@Test
	public void testSingletonSerialization() throws IOException, ClassNotFoundException {
		SingletonUsingConstructorLazyInit singletonUsingConstructorLazyInit =
				SingletonUsingConstructorLazyInit.getSingletonInstance();
		final String fileName = "serializedInstance.ser";
		File serializedInstance = new File(fileName);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(serializedInstance));
		objectOutputStream.writeObject(singletonUsingConstructorLazyInit);

		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName));
		SingletonUsingConstructorLazyInit deSerializedObject =
				(SingletonUsingConstructorLazyInit) objectInputStream.readObject();

		assertSame(singletonUsingConstructorLazyInit, deSerializedObject);
	}

	@Test(expected = CloneNotSupportedException.class)
	public void testSingletonClone() throws CloneNotSupportedException {
		SingletonUsingConstructorLazyInit singletonUsingConstructorLazyInit = SingletonUsingConstructorLazyInit
				.getSingletonInstance();
		SingletonUsingConstructorLazyInit secondSingletonUsingConstructor =
				(SingletonUsingConstructorLazyInit) singletonUsingConstructorLazyInit.clone();
		throw new RuntimeException("Shouldn't reach this point as cloning shouldn't create second instance  ");
	}
}
