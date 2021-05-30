package com.cognizant.cp.factoryservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.cognizant.cp.dao.ProcessService;
import com.cognizant.cp.exception.ComponentTypeNotFoundException;
import com.cognizant.cp.model.ComponentType;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ComponentFactory 
{
	
	@Autowired
	ApplicationContext context;

	public ProcessService getComponentFactory(ComponentType componentType)
	{
		log.info("ComponentFactory method getComponentFactory is invoked");		
		
		if(componentType==ComponentType.ACCESSORY)
		{
			return context.getBean(AccessorPartService.class);
		}
		else if(componentType==ComponentType.INTEGRAL)
		{
			return context.getBean(IntegralPartService.class);
		}
		else
		{
			throw new ComponentTypeNotFoundException("Component not found "+componentType);
		}
	}
	
}
