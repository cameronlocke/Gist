package com.thegist.model;

// Object of Analysis results.

public class AnalysisResult {

	// ConditionaLevels
	final public static Integer ERROR = 1;
	final public static Integer WARNING = 2;
	final public static Integer INFORMATIONAL = 3;
	
	private Integer conditionLevel;   // 1=ERROR, 2=WARNINGS, 3=INFORMATIONAL
	private String condition;  // Travel left lane only here, 10kmp over bridge, etc

	public Integer getConditionLevel() {
		return conditionLevel;
	}

	public void setConditionLevel(Integer conditionLevel) {
		this.conditionLevel = conditionLevel;
	}

	public String getCondition() {
		return condition;
	}
	
	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	//Constructors
	public AnalysisResult(Integer conditionLevel, String condition) {
			super();
			this.conditionLevel = conditionLevel;
			this.condition = condition;
		}
	}