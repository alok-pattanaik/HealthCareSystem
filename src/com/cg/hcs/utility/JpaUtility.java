package com.cg.hcs.utility;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtility 
{
	private final static EntityManagerFactory factory;
	static
	{
		factory = Persistence.createEntityManagerFactory("HealthCareSystem");
	}
	public static EntityManagerFactory getFactory()
	{
		return factory;
	}
}
