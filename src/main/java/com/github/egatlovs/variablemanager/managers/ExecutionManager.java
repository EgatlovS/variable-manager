package com.github.egatlovs.variablemanager.managers;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.enterprise.context.RequestScoped;

import org.camunda.bpm.engine.context.DelegateExecutionContext;
import org.camunda.bpm.engine.delegate.DelegateExecution;

import com.github.egatlovs.variablemanager.processing.FieldNames;
import com.github.egatlovs.variablemanager.processing.VariableProcessor;

@RequestScoped
public class ExecutionManager implements ExecutionVariableManager {

	private DelegateExecution execution;

	public ExecutionManager() {
		this(DelegateExecutionContext.getCurrentDelegationExecution());
	}

	public ExecutionManager(DelegateExecution execution) {
		this.execution = execution;
	}

	@Override
	public void setVariable(Object value) {
		// TODO Bean Validation first
		VariableProcessor processor = new VariableProcessor();
		try {
			Map<String, Object> processedVariables = processor.process(value);
			this.execution.setVariables(processedVariables);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO exception handling
			e.printStackTrace();
		}
	}

	@Override
	public void setVariableLocal(Object value) {
		// TODO Bean Validation first
		VariableProcessor processor = new VariableProcessor();
		try {
			Map<String, Object> processedVariables = processor.process(value);
			this.execution.setVariablesLocal(processedVariables);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO exception handling
			e.printStackTrace();
		}
	}

	@Override
	public <T> T getVariable(Class<T> clazz) {
		Set<String> variableNames = new FieldNames().get(clazz);
		Map<String, Object> variables = new HashMap<>();
		for (String name : variableNames) {
			variables.put(name, this.execution.getVariable(name));
		}
		T obj = null;
		try {
			obj = clazz.getConstructor().newInstance();
			// TODO set variables to obj
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			// TODO exception handling
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public <T> T getVariableLocal(Class<T> clazz) {
		Set<String> variableNames = new FieldNames().get(clazz);
		Map<String, Object> variables = new HashMap<>();
		for (String name : variableNames) {
			variables.put(name, this.execution.getVariableLocal(name));
		}
		T obj = null;
		try {
			obj = clazz.getConstructor().newInstance();
			// TODO set variables to obj
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			// TODO exception handling
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public <T> void removeVariables(Class<T> clazz) {
		Set<String> variableNames = new FieldNames().get(clazz);
		this.execution.removeVariables(variableNames);
	}

	@Override
	public <T> void removeVariablesLocal(Class<T> clazz) {
		Set<String> variableNames = new FieldNames().get(clazz);
		this.execution.removeVariablesLocal(variableNames);
	}

	public DelegateExecution getExecutionService() {
		return execution;
	}

	public void setExecutionService(DelegateExecution execution) {
		this.execution = execution;
	}
}
