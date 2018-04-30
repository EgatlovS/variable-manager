package com.github.egatlovs.mock;

import java.math.BigDecimal;

import com.github.egatlovs.variablemanager.annotations.Execution;
import com.github.egatlovs.variablemanager.annotations.ExecutionField;
import com.github.egatlovs.variablemanager.annotations.Ignore;
import com.github.egatlovs.variablemanager.annotations.StoreStrategies;

@Execution(storeStrategy = StoreStrategies.JSON)
public class VariableProcessorMockJsonAnnotated {

	private String someString = "string";
	@ExecutionField(name = "fieldName", prefix = "fieldPrefix")
	private String annotated = "annotatedString";
	@ExecutionField(name = "myDecimal")
	private BigDecimal decimal = BigDecimal.ONE;
	@Ignore
	private Object ignoredField;

}
